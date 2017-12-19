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
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.domstorage.StorageId;

import java.util.List;

/**
 * Query and modify DOM storage
 */
@Experimental
@Domain("DOMStorage")
public interface DOMStorage {
    /**
     * Enables storage tracking, storage events will now be delivered to the client.
     */
    void enable();

    /**
     * Disables storage tracking, prevents storage events from being sent to the client.
     */
    void disable();

    void clear(StorageId storageId);

    @Returns("entries")
    List<String> getDOMStorageItems(StorageId storageId);

    void setDOMStorageItem(StorageId storageId, String key, String value);

    void removeDOMStorageItem(StorageId storageId, String key);
}
