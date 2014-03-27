/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.dialer.calllog;

import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import com.android.contacts.common.CallUtil;

/**
 * Helper class operating on call log notifications.
 */
public class CallLogNotificationsHelper {
    /** Removes the missed call notifications. */
    public static void removeMissedCallNotifications(Context context) {
        TelecomManager telecomManager = (TelecomManager)
                context.getSystemService(Context.TELECOM_SERVICE);
        telecomManager.cancelMissedCallsNotification();
    }

    /** Update the voice mail notifications. */
    public static void updateVoicemailNotifications(Context context) {
        Intent serviceIntent = new Intent(context, CallLogNotificationsService.class);
        serviceIntent.setAction(CallLogNotificationsService.ACTION_UPDATE_NOTIFICATIONS);
        context.startService(serviceIntent);
    }

    /** Send broadcast to let VideoCall app cancel the missed vtcall notifications. */
    public static void removeMissedCSVTCallNotifications(Context context) {
        if (CallUtil.isCSVTEnabled()) {
            Intent intent = new Intent(CallUtil.INTENT_ACTION_CLEAR_MISSED_CSVTCALL);
            intent.putExtra(CallUtil.CLEAR_MISSED_CSVTCALL_UPDATE_CALLLOG, false);
            context.sendBroadcast(intent);
        }
    }
}
