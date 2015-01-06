/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.johnny.wearmessagebringfront;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Listens to DataItems and Messages from the local node.
 */
public class WearDataLayerListenerService extends WearableListenerService {

    private static final String TAG = "WearDataLayerListenerService";

    public static final String START_ACTIVITY_PHONE_PATH = "/start-activity-phone";
    public static final String START_ACTIVITY_WEAR_PATH = "/start-activity-wear";

//    GoogleApiClient mGoogleApiClient;

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Wearable.API)
//                .build();
//        mGoogleApiClient.connect();
//    }

//    @Override
//    public void onDataChanged(DataEventBuffer dataEvents) {
//        Log.d(TAG, "onDataChanged: " + dataEvents);
//        final List<DataEvent> events = FreezableUtils.freezeIterable(dataEvents);
//        dataEvents.close();
////        if (!mGoogleApiClient.isConnected()) {
////            ConnectionResult connectionResult = mGoogleApiClient
////                    .blockingConnect(30, TimeUnit.SECONDS);
//            if (!connectionResult.isSuccess()) {
//                Log.e(TAG, "DataLayerListenerService failed to connect to GoogleApiClient.");
//                return;
//            } else {
//                Log.e(TAG, "WearDataLayerListenerService connected to GoogleApiClient.");
//            }
////        }
//    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "onMessageReceived: " + messageEvent);

        // Check to see if the message is to start an activity
        if (messageEvent.getPath().equals(START_ACTIVITY_WEAR_PATH)) {
            Intent startIntent = new Intent(this, WearMainActivity.class);
//            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startIntent);
        }
    }

}
