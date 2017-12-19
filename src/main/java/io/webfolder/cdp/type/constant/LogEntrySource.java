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
package io.webfolder.cdp.type.constant;

import com.google.gson.annotations.SerializedName;

public enum LogEntrySource {
    @SerializedName("xml")
    Xml("xml"),

    @SerializedName("javascript")
    Javascript("javascript"),

    @SerializedName("network")
    Network("network"),

    @SerializedName("storage")
    Storage("storage"),

    @SerializedName("appcache")
    Appcache("appcache"),

    @SerializedName("rendering")
    Rendering("rendering"),

    @SerializedName("security")
    Security("security"),

    @SerializedName("deprecation")
    Deprecation("deprecation"),

    @SerializedName("worker")
    Worker("worker"),

    @SerializedName("violation")
    Violation("violation"),

    @SerializedName("intervention")
    Intervention("intervention"),

    @SerializedName("recommendation")
    Recommendation("recommendation"),

    @SerializedName("other")
    Other("other");

    public final String value;

    LogEntrySource(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
