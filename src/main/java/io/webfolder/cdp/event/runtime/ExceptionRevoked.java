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
package io.webfolder.cdp.event.runtime;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

/**
 * Issued when unhandled exception was revoked
 */
@Domain("Runtime")
@EventName("exceptionRevoked")
public class ExceptionRevoked {
    private String reason;

    private Integer exceptionId;

    /**
     * Reason describing why exception was revoked.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Reason describing why exception was revoked.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * The id of revoked exception, as reported in <code>exceptionThrown</code>.
     */
    public Integer getExceptionId() {
        return exceptionId;
    }

    /**
     * The id of revoked exception, as reported in <code>exceptionThrown</code>.
     */
    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }
}
