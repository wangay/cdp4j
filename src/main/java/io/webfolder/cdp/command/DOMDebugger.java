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
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.domdebugger.DOMBreakpointType;
import io.webfolder.cdp.type.domdebugger.EventListener;

import java.util.List;

/**
 * DOM debugging allows setting breakpoints on particular DOM operations and events
 * JavaScript execution will stop on these operations as if there was a regular breakpoint set
 */
@Domain("DOMDebugger")
public interface DOMDebugger {
    /**
     * Sets breakpoint on particular operation with DOM.
     *
     * @param nodeId Identifier of the node to set breakpoint on.
     * @param type   Type of the operation to stop upon.
     */
    void setDOMBreakpoint(Integer nodeId, DOMBreakpointType type);

    /**
     * Removes DOM breakpoint that was set using <tt>setDOMBreakpoint</tt>.
     *
     * @param nodeId Identifier of the node to remove breakpoint from.
     * @param type   Type of the breakpoint to remove.
     */
    void removeDOMBreakpoint(Integer nodeId, DOMBreakpointType type);

    /**
     * Sets breakpoint on particular DOM event.
     *
     * @param eventName  DOM Event name to stop on (any DOM event will do).
     * @param targetName EventTarget interface name to stop on. If equal to <code>"*"</code> or not provided, will stop on any EventTarget.
     */
    void setEventListenerBreakpoint(String eventName, @Experimental @Optional String targetName);

    /**
     * Removes breakpoint on particular DOM event.
     *
     * @param eventName  Event name.
     * @param targetName EventTarget interface name.
     */
    void removeEventListenerBreakpoint(String eventName, @Experimental @Optional String targetName);

    /**
     * Sets breakpoint on particular native event.
     *
     * @param eventName Instrumentation name to stop on.
     */
    @Experimental
    void setInstrumentationBreakpoint(String eventName);

    /**
     * Removes breakpoint on particular native event.
     *
     * @param eventName Instrumentation name to stop on.
     */
    @Experimental
    void removeInstrumentationBreakpoint(String eventName);

    /**
     * Sets breakpoint on XMLHttpRequest.
     *
     * @param url Resource URL substring. All XHRs having this substring in the URL will get stopped upon.
     */
    void setXHRBreakpoint(String url);

    /**
     * Removes breakpoint from XMLHttpRequest.
     *
     * @param url Resource URL substring.
     */
    void removeXHRBreakpoint(String url);

    /**
     * Returns event listeners of the given object.
     *
     * @param objectId Identifier of the object to return listeners for.
     * @param depth    The maximum depth at which Node children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
     * @param pierce   Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false). Reports listeners for all contexts if pierce is enabled.
     * @return Array of relevant listeners.
     */
    @Experimental
    @Returns("listeners")
    List<EventListener> getEventListeners(String objectId, @Experimental @Optional Integer depth,
                                          @Experimental @Optional Boolean pierce);

    /**
     * Sets breakpoint on particular DOM event.
     *
     * @param eventName DOM Event name to stop on (any DOM event will do).
     */
    void setEventListenerBreakpoint(String eventName);

    /**
     * Removes breakpoint on particular DOM event.
     *
     * @param eventName Event name.
     */
    void removeEventListenerBreakpoint(String eventName);

    /**
     * Returns event listeners of the given object.
     *
     * @param objectId Identifier of the object to return listeners for.
     * @return Array of relevant listeners.
     */
    @Experimental
    @Returns("listeners")
    List<EventListener> getEventListeners(String objectId);
}
