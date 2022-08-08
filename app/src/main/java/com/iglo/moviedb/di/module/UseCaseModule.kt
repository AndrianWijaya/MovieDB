package com.iglo.moviedb.di.module

import com.iglo.api_service.service.genre.GenreService
import com.iglo.api_service.service.movie_detail.MovieDetailService
import com.iglo.api_service.service.movie_discover.DiscoverMovieService
import com.iglo.api_service.service.review.MovieReviewService
import com.iglo.api_service.service.video.MovieVideoService
import com.iglo.api_service.use_case.*
import dagger.Module
import dagger.Provides

@Module(includes = [ApiServiceModule::class])
class UseCaseModule {

    @Provides
    fun getGenreUseCase(genreService: GenreService) = GetGenreUseCase(genreService)

    @Provides
    fun getDiscoverMovieUseCase(discoverMovieService: DiscoverMovieService) =
        GetDiscoverMovieUseCase(discoverMovieService)

    @Provides
    fun getMovieDetail(movieService: MovieDetailService) =
        GetMovieDetailUseCase(movieService)

    @Provides
    fun getMovieReview(movieReviewService: MovieReviewService) =
        GetMovieReviewUseCase(movieReviewService)

    @Provides
    fun getMovieVideo(movieVideoService: MovieVideoService) =
        GetMovieVideoUseCase(movieVideoService)
}