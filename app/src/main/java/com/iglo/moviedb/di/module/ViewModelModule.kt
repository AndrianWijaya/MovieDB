package com.iglo.moviedb.di.module

import com.iglo.api_service.use_case.*
import com.iglo.moviedb.view_model.DiscoverMovieViewModel
import com.iglo.moviedb.view_model.GenreMovieViewModel
import com.iglo.moviedb.view_model.MovieDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication

@Module(includes = [UseCaseModule::class])
class ViewModelModule {

    @Provides
    fun provideGenreViewModel(
        getGenreUseCase: GetGenreUseCase,
        application: DaggerApplication
    ) = GenreMovieViewModel(getGenreUseCase, application)

    @Provides
    fun provideDiscoverMovieViewModel(
        getDiscoverMovieUseCase: GetDiscoverMovieUseCase,
        application: DaggerApplication
    ) = DiscoverMovieViewModel(getDiscoverMovieUseCase, application)

    @Provides
    fun provideMovieDetailVm(
        getMovieDetailUseCase: GetMovieDetailUseCase,
        getMovieReviewUseCase: GetMovieReviewUseCase,
        getMovieVideoUseCase: GetMovieVideoUseCase,
        application: DaggerApplication
    ) = MovieDetailViewModel(
        getMovieDetailUseCase,
        getMovieReviewUseCase,
        getMovieVideoUseCase,
        application
    )
}