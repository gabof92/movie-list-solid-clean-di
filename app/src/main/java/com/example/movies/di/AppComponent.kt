package com.example.movies.di

import android.app.Application
import com.example.movies.ui.detail.DetailFragment
import com.example.movies.ui.list.ListFragment
import dagger.BindsInstance
import javax.inject.Singleton
import dagger.Component

@Singleton
@Component(modules = [AppModule::class, AppDataModule::class])
interface AppComponent {

    fun inject(fragment: ListFragment)
    fun inject(fragment: DetailFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application): AppComponent
    }
}
