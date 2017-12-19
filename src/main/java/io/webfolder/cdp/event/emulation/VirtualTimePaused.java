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
package io.webfolder.cdp.event.emulation;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.annotation.Experimental;

/**
 * Notification sent after the virtual time has paused
 */
@Experimental
@Domain("Emulation")
@EventName("virtualTimePaused")
public class VirtualTimePaused {
    private Integer virtualTimeElapsed;

    /**
     * The amount of virtual time that has elapsed in milliseconds since virtual time was first enabled.
     */
    public Integer getVirtualTimeElapsed() {
        return virtualTimeElapsed;
    }

    /**
     * The amount of virtual time that has elapsed in milliseconds since virtual time was first enabled.
     */
    public void setVirtualTimeElapsed(Integer virtualTimeElapsed) {
        this.virtualTimeElapsed = virtualTimeElapsed;
    }
}
