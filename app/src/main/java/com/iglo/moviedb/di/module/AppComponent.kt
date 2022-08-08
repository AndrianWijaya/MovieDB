package com.iglo.moviedb.di.module


import com.iglo.moviedb.MovieDB
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component (modules = [AndroidInjectionModule::class, ViewModelProviderFactoryModule::class])
abstract class AppComponent : AndroidInjector<MovieDB> {

    @Component.Builder
    abstract class Builder{

        @BindsInstance
        abstract fun app(application: DaggerApplication) : Builder
        abstract fun build() : AppComponent
    }
}