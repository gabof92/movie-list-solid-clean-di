package com.example.movies.di

import android.app.Application
import com.example.movies.ui.detail.DetailFragmentComponent
import com.example.movies.ui.detail.DetailFragmentModule
import com.example.movies.ui.list.ListFragmentComponent
import com.example.movies.ui.list.ListFragmentModule
import dagger.BindsInstance
import javax.inject.Singleton
import dagger.Component

@Singleton
@Component(modules = [AppModule::class, AppDataModule::class])
interface AppComponent {

    //plus functions will add the subcomponents to the graph of dependencies
    fun plus(listFragmentModule: ListFragmentModule): ListFragmentComponent
    fun plus(detailFragmentModule: DetailFragmentModule): DetailFragmentComponent

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application): AppComponent
    }
}
