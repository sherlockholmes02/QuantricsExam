package com.quantrics.guidomia

import android.app.Application
import com.quantrics.guidomia.data.GuidomiaDatabase

/**
 * Created by Darryl Dave P. de Castro on 18/01/2022.
 */
class GuidomiaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        GuidomiaDatabase.init(this)
    }
}