package com.iglo.api_service.use_case


import com.iglo.api_service.paging.DiscoverMovieDataSource
import com.iglo.api_service.service.movie_discover.DiscoverMovieService


class GetDiscoverMovieUseCase(
    private val discoverMovieService: DiscoverMovieService
) {
    operator fun invoke(id : ArrayList<Int>) = DiscoverMovieDataSource.createPager(
        10,discoverMovieService,id
    ).flow
}