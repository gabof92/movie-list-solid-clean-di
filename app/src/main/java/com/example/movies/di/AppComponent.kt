package com.example.movies.di;

import android.app.Application
import com.example.movies.ui.detail.DetailViewModelFactory
import com.example.movies.ui.list.ListViewModelFactory
import dagger.BindsInstance
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = [UseCaseModule::class, DataModule::class,
    AppModule::class, ViewModelsModule::class])
interface AppComponent {

    //Instead of using @Inject in the Fragment we expose some properties here

    val listViewModelFactory: ListViewModelFactory
    val detailViewModelFactory: DetailViewModelFactory


    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application): AppComponent
    }

}
