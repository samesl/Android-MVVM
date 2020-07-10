package com.android.saman.sample.androidmvvm.utils

import android.app.Activity
import com.android.saman.sample.androidmvvm.MainApplication

val Activity.app: MainApplication
    get() = application as MainApplication
