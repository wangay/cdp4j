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
package io.webfolder.cdp.test;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.Option;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAll {

    private static CdpAppender appender;

    private static SessionFactory factory;

    private static Session session;

    private static LoggerContext loggerContext;

    @BeforeClass
    @SuppressWarnings("unchecked")
    public static void init() {
        loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        appender = new CdpAppender();
        appender.setContext(loggerContext);
        appender.start();
        appender.setName(CdpAppender.class.getName());

        Logger logger = loggerContext.getLogger("cdp4j.flow");
        logger.addAppender((Appender<ILoggingEvent>) appender);

        factory = new Launcher().launch();

        session = factory.create();

        session.enableConsoleLog();

        URL url = TestAll.class.getResource("/session-test.html");
        session.navigate(url.toString());
    }

    @AfterClass
    public static void dispose() {
        appender.stop();
        loggerContext.stop();
        factory.close();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void t01_test() {
        session.activate();
        session.getTitle();

        session.getValue("[type=text]");
        session.setValue("[type=text]", "bar");
        session.getValue("[type=text]");

        session.setAttribute("[type=text]", "my-attr", "my-value");
        session.getAttribute("[type=text]", "my-attr");

        session.isChecked("#myradio");
        session.setChecked("#myradio", false);
        session.isChecked("#myradio");

        session.click("#mybutton");

        session.focus("[type=text]");

        session.selectInputText("[type=text]");
        session.sendBackspace();
        session.sendKeys("hello");
        session.getProperty("[type=text]", "value");

        session.stop();
        session.reload();
        session.waitDocumentReady();

        assertTrue(session.isDomReady());

        session.evaluate("2 + 2");

        session.getSelectedIndex("#single-select");
        session.setSelectedIndex("#single-select", 1);
        session.getSelectedIndex("#single-select");
        session.clearOptions("#single-%s", "select");
        session.getSelectedIndex("#single-select");

        List<Option> options = session.getOptions("#multi-select");
        assertEquals(2, options.size());

        session.setSelectedOptions("#multi-%s", Arrays.asList(1), "select");

        options = session.getOptions("#multi-%s", "select");

        assertEquals(2, options.size());
        assertFalse(options.get(0).isSelected());
        assertTrue(options.get(1).isSelected());

        List<String> expecteds = emptyList();
        try {
            expecteds = readAllLines(get("src/test/resources/TestSession-console.txt"));
        } catch (IOException e) {
            throw new CdpException(e);
        }

        session.installSizzle();
        assertTrue(session.useSizzle());

        session.getProperty("p:contains('%s')", "innerHTML", "hello");
        session.setProperty("//p", "innerHTML", "hi");
        session.getProperty("p:contains('hi')", "innerHTML");

        session.setDisabled("[type=text]", true);
        session.isDisabled("[type=text]");

        try {
            session.focus("[type=text]");
        } catch (CdpException e) {
            assertEquals("Element is not focusable", e.getMessage());
        }

        session.setUserAgent("my browser");
        session.evaluate("navigator.userAgent");

        assertTrue(session.getQueryString().isEmpty());

        session.callFunction("myfunc");

        session.evaluate("var foo = { }; foo.name = 'bar';");

        String name = session.getVariable("foo.name", String.class);

        assertEquals("bar", name);

        session.setVariable("foo.age", 1);

        assertEquals(1, session.getVariable("foo.age", Integer.class).intValue());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("key1", "value1");
        session.setVariable("xyz", map);

        assertEquals("value1", session.getVariable("xyz", Map.class).get("key1"));

        session.evaluate("window.myFunc2 = function(myMap) { return myMap; }");

        map.clear();
        map.put("foo", "bar");
        map = session.callFunction("myFunc2", Map.class, map);

        assertEquals(1, map.size());
        assertEquals("bar", map.get("foo"));

        session.close();

        assertFalse(session.isConnected());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> actuals = appender.getLogEntries();

        List<String> failedExpecteds = new ArrayList<>();
        List<String> failedActuals = new ArrayList<>();

        for (int i = 0; i < actuals.size(); i++) {
            String expected = i < expecteds.size() ? expecteds.get(i) : null;
            String actual = actuals.get(i);
            String methodName = "";
            if (expected != null && expected.startsWith("invoked(")) {
                methodName = expected.substring("invoked(".length(), expected.length() - 1);
            }

            boolean ok = expected != null && expected.equals(actual) || actual.startsWith(methodName);
            if (ok) {
                System.out.println("[    OK    ] " + actual);
            } else {
                System.out.println("[  FAILED  ] " + actual);
                System.out.println("[ EXPECTED ] " + expected);
            }
            if (!ok) {
                failedExpecteds.add(expected);
                failedActuals.add(actual);
            }
        }

        assertArrayEquals(failedExpecteds.toArray(new String[]{}), failedActuals.toArray(new String[]{}));

        assertEquals(expecteds.size(), actuals.size());
    }
}
