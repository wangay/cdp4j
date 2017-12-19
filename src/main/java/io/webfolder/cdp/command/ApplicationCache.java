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
import io.webfolder.cdp.type.applicationcache.FrameWithManifest;

import java.util.List;

@Experimental
@Domain("ApplicationCache")
public interface ApplicationCache {
    /**
     * Returns array of frame identifiers with manifest urls for each frame containing a document associated with some application cache.
     *
     * @return Array of frame identifiers with manifest urls for each frame containing a document associated with some application cache.
     */
    @Returns("frameIds")
    List<FrameWithManifest> getFramesWithManifests();

    /**
     * Enables application cache domain notifications.
     */
    void enable();

    /**
     * Returns manifest URL for document in the given frame.
     *
     * @param frameId Identifier of the frame containing document whose manifest is retrieved.
     * @return Manifest URL for document in the given frame.
     */
    @Returns("manifestURL")
    String getManifestForFrame(String frameId);

    /**
     * Returns relevant application cache data for the document in given frame.
     *
     * @param frameId Identifier of the frame containing document whose application cache is retrieved.
     * @return Relevant application cache data for the document in given frame.
     */
    @Returns("applicationCache")
    io.webfolder.cdp.type.applicationcache.ApplicationCache getApplicationCacheForFrame(String frameId);
}
