package com.example.amplifyproject

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.example.amplifyproject.BuildConfig.*
import com.example.amplifyproject.other.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AmplifyProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "Class:%s: Line: %s, Method: %s",
                        super.createStackElementTag(element),
                        element.lineNumber,
                        element.methodName
                    )
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }

        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.addPlugin(AWSDataStorePlugin())
        Amplify.configure(applicationContext)
        try {
            Timber.i("MyAmplifyApp Initialized Amplify")
        } catch (error: AmplifyException) {
            Timber.e("MyAmplifyApp Could not initialize Amplify $error")
        }
    }

}