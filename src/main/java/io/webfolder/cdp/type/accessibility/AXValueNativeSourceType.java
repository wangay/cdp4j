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
package io.webfolder.cdp.type.accessibility;

import com.google.gson.annotations.SerializedName;

/**
 * Enum of possible native property sources (as a subtype of a particular AXValueSourceType)
 */
public enum AXValueNativeSourceType {
    @SerializedName("figcaption")
    Figcaption("figcaption"),

    @SerializedName("label")
    Label("label"),

    @SerializedName("labelfor")
    Labelfor("labelfor"),

    @SerializedName("labelwrapped")
    Labelwrapped("labelwrapped"),

    @SerializedName("legend")
    Legend("legend"),

    @SerializedName("tablecaption")
    Tablecaption("tablecaption"),

    @SerializedName("title")
    Title("title"),

    @SerializedName("other")
    Other("other");

    public final String value;

    AXValueNativeSourceType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
