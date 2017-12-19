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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;

@Experimental
@Domain("ServiceWorker")
public interface ServiceWorker {
    void enable();

    void disable();

    void unregister(String scopeURL);

    void updateRegistration(String scopeURL);

    void startWorker(String scopeURL);

    void skipWaiting(String scopeURL);

    void stopWorker(String versionId);

    void stopAllWorkers();

    void inspectWorker(String versionId);

    void setForceUpdateOnPageLoad(Boolean forceUpdateOnPageLoad);

    void deliverPushMessage(String origin, String registrationId, String data);

    void dispatchSyncEvent(String origin, String registrationId, String tag, Boolean lastChance);
}
