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
 * Enum of possible property sources
 */
public enum AXValueSourceType {
    @SerializedName("attribute")
    Attribute("attribute"),

    @SerializedName("implicit")
    Implicit("implicit"),

    @SerializedName("style")
    Style("style"),

    @SerializedName("contents")
    Contents("contents"),

    @SerializedName("placeholder")
    Placeholder("placeholder"),

    @SerializedName("relatedElement")
    RelatedElement("relatedElement");

    public final String value;

    AXValueSourceType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
