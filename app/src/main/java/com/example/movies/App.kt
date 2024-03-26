package com.example.movies

import android.app.Application
import com.example.movies.di.AppComponent
import com.example.movies.di.DaggerAppComponent

class App : Application() {

    /*
    This line removed because Dagger provides the database dependency
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    */

    /** Dagger component setup **/
    lateinit var component: AppComponent
        //this is so that it can't be modified from outside
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
            .factory()
            .create(this)
    }
}