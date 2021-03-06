/*
 * Copyright (C) 2017 Kinemic GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.kinemic.toolbox.event.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.json.JSONException;

import de.kinemic.toolbox.event.PublisherEvent;

/**
 * A {@link BroadcastReceiver} for events of the kinemic publisher.
 * Use {@link #ACTION} to register the BroadcastReceiver.
 */
public abstract class PublisherEventReceiver extends BroadcastReceiver {

    /**
     * The action to used in the broadcast intent.
     * Also used in the {@code IntentFilter} for this {@link BroadcastReceiver}.
     */
    public static final String ACTION = PublisherEvent.ACTION_EVENT;

    @Override
    public final void onReceive(Context context, Intent intent) {
        if (PublisherEvent.ACTION_EVENT.equals(intent.getAction())) {
            final String json = intent.getStringExtra(PublisherEvent.BROADCAST_JSON);
            try {
                handleEvent(PublisherEvent.fromJson(json));
            } catch (JSONException e) {
                // this should not happen, since we send the event
            }
        }
    }

    /**
     * Called for every {@link PublisherEvent} received.
     * @param event a {@link PublisherEvent}
     */
    protected abstract void handleEvent(PublisherEvent event);
}
