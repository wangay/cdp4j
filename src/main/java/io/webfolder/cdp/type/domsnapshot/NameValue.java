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
package io.webfolder.cdp.type.domsnapshot;

/**
 * A name/value pair
 */
public class NameValue {
    private String name;

    private String value;

    /**
     * Attribute/property name.
     */
    public String getName() {
        return name;
    }

    /**
     * Attribute/property name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Attribute/property value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Attribute/property value.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
