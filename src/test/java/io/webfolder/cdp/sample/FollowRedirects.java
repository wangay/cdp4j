/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017 WebFolder OÜ (support@webfolder.io)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.sample;

import static io.webfolder.cdp.event.Events.NetworkRequestIntercepted;
import static io.webfolder.cdp.type.page.ResourceType.Document;
import static java.util.Arrays.asList;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.event.network.RequestIntercepted;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.RequestPattern;

public class FollowRedirects {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
             Session session = factory.create()) {

            Network network = session.getCommand().getNetwork();
            network.enable();
            // Disable newtork caching when intercepting
            // https://github.com/GoogleChrome/puppeteer/pull/1154
            network.setCacheDisabled(Boolean.TRUE);

            RequestPattern pattern = new RequestPattern();
            pattern.setUrlPattern("*");
            pattern.setResourceType(Document);
            network.setRequestInterception(asList(pattern));

            boolean followRedirect = true;

            session.addEventListener((e, d) -> {
                if (NetworkRequestIntercepted.equals(e)) {
                    RequestIntercepted ri = (RequestIntercepted) d;
                    boolean isRedirect = ri.getRedirectStatusCode() != null;

                    if (isRedirect) {

                        System.out.println("");
                        System.out.println("Redirect URL         : " + ri.getRedirectUrl());
                        System.out.println("Redirect Status Code : " + ri.getRedirectStatusCode());
                        System.out.println("Redirect Header      : " + ri.getRedirectHeaders());
                        System.out.println("");

                        if (!followRedirect) {
                            return;
                        }
                    }

                    network.continueInterceptedRequest(ri.getInterceptionId());
                }
            });

            session.navigate("https://httpbin.org/redirect-to?url=https://webfolder.io?cdp4j");
            session.waitDocumentReady();
        }
    }
}
