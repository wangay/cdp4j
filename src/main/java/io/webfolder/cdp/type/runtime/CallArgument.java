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
package io.webfolder.cdp.type.runtime;

/**
 * Represents function call argument
 * Either remote object id <code>objectId</code>, primitive <code>value</code>, unserializable primitive value or neither of (for undefined) them should be specified
 */
public class CallArgument {
    private Object value;

    private UnserializableValue unserializableValue;

    private String objectId;

    /**
     * Primitive value or serializable javascript object.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Primitive value or serializable javascript object.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Primitive value which can not be JSON-stringified.
     */
    public UnserializableValue getUnserializableValue() {
        return unserializableValue;
    }

    /**
     * Primitive value which can not be JSON-stringified.
     */
    public void setUnserializableValue(UnserializableValue unserializableValue) {
        this.unserializableValue = unserializableValue;
    }

    /**
     * Remote object handle.
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Remote object handle.
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
