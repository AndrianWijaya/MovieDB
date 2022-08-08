package com.iglo.moviedb

import com.iglo.moviedb.di.module.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MovieDB: DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().app(this).build()
}