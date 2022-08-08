package com.iglo.moviedb.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iglo.common.helper.ViewModelKey
import com.iglo.common.helper.ViewModelProviderFactory
import com.iglo.moviedb.activity.discover_movie.DiscoverMovieActivity
import com.iglo.moviedb.activity.genre.GenreActivity
import com.iglo.moviedb.activity.movie_details.MovieDetailActivity
import com.iglo.moviedb.view_model.DiscoverMovieViewModel
import com.iglo.moviedb.view_model.GenreMovieViewModel
import com.iglo.moviedb.view_model.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.DaggerApplication
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class ViewModelProviderFactoryModule {

    @Binds
    abstract fun provideContext(context : DaggerApplication) : Context

    @Binds
    abstract fun provideApp(context: DaggerApplication) : Application

    @Binds
    abstract fun provideVmFactory(vmFactory: ViewModelProviderFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GenreMovieViewModel::class)
    abstract fun provideGenreViewModuleMapKey(vm : GenreMovieViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverMovieViewModel::class)
    abstract fun provideDiscoverViewModuleMapKey(Vm : DiscoverMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun provideMovieDetailViewModuleMapKey(Vm : MovieDetailViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeGenreActivity() : GenreActivity

    @ContributesAndroidInjector
    abstract fun contributeDiscoverMovieActivity() : DiscoverMovieActivity

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailActivity() : MovieDetailActivity
}