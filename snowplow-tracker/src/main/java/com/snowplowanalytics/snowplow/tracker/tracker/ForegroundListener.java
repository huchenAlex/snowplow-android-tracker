/*
 * Copyright (c) 2015 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */

package com.snowplowanalytics.snowplow.tracker.tracker;

import android.annotation.TargetApi;
import android.os.Build;

import com.sjl.foreground.Foreground;
import com.snowplowanalytics.snowplow.tracker.Tracker;
import com.snowplowanalytics.snowplow.tracker.utils.Logger;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ForegroundListener implements Foreground.Listener {

    private static final String TAG = ForegroundListener.class.getSimpleName();

    /**
     * Application came to foreground.
     */
    public void onBecameForeground() {
        Logger.d(TAG, "Application is in the foreground");
        Tracker tracker = Tracker.instance();

        // Update Session
        if (tracker.getSession() != null) {
            tracker.getSession().setIsBackground(false);
        }

        // Send Foreground Event
        if (tracker.getLifecycleEvents()) {

        }
    }

    /**
     * Application went to background.
     */
    public void onBecameBackground() {
        Logger.d(TAG, "Application is in the background");
        Tracker tracker = Tracker.instance();

        // Update Session
        if (tracker.getSession() != null) {
            tracker.getSession().setIsBackground(true);
        }

        // Send Background Event
        if (tracker.getLifecycleEvents()) {

        }
    }
}
