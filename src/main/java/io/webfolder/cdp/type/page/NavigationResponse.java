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
package io.webfolder.cdp.type.page;

import com.google.gson.annotations.SerializedName;

/**
 * Proceed: allow the navigation; Cancel: cancel the navigation; CancelAndIgnore: cancels the navigation and makes the requester of the navigation acts like the request was never made
 */
public enum NavigationResponse {
    @SerializedName("Proceed")
    Proceed("Proceed"),

    @SerializedName("Cancel")
    Cancel("Cancel"),

    @SerializedName("CancelAndIgnore")
    CancelAndIgnore("CancelAndIgnore");

    public final String value;

    NavigationResponse(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
