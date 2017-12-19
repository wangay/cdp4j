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
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.profiler.Profile;
import io.webfolder.cdp.type.profiler.ScriptCoverage;
import io.webfolder.cdp.type.profiler.ScriptTypeProfile;

import java.util.List;

@Domain("Profiler")
public interface Profiler {
    void enable();

    void disable();

    /**
     * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
     *
     * @param interval New sampling interval in microseconds.
     */
    void setSamplingInterval(Integer interval);

    void start();

    @Returns("profile")
    Profile stop();

    /**
     * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
     *
     * @param callCount Collect accurate call counts beyond simple 'covered' or 'not covered'.
     * @param detailed  Collect block-based coverage.
     */
    @Experimental
    void startPreciseCoverage(@Optional Boolean callCount, @Optional Boolean detailed);

    /**
     * Disable precise code coverage. Disabling releases unnecessary execution count records and allows executing optimized code.
     */
    @Experimental
    void stopPreciseCoverage();

    /**
     * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
     *
     * @return Coverage data for the current isolate.
     */
    @Experimental
    @Returns("result")
    List<ScriptCoverage> takePreciseCoverage();

    /**
     * Collect coverage data for the current isolate. The coverage data may be incomplete due to garbage collection.
     *
     * @return Coverage data for the current isolate.
     */
    @Experimental
    @Returns("result")
    List<ScriptCoverage> getBestEffortCoverage();

    /**
     * Enable type profile.
     */
    @Experimental
    void startTypeProfile();

    /**
     * Disable type profile. Disabling releases type profile data collected so far.
     */
    @Experimental
    void stopTypeProfile();

    /**
     * Collect type profile.
     *
     * @return Type profile for all scripts since startTypeProfile() was turned on.
     */
    @Experimental
    @Returns("result")
    List<ScriptTypeProfile> takeTypeProfile();

    /**
     * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
     */
    @Experimental
    void startPreciseCoverage();
}
