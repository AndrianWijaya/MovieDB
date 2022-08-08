package com.iglo.api_service.use_case

import com.iglo.api_service.paging.MovieReviewDataSource
import com.iglo.api_service.service.review.MovieReviewService

class GetMovieReviewUseCase(
    private val getMovieReviewService: MovieReviewService
) {
    operator fun invoke(id : Int) = MovieReviewDataSource.createPager(
        10, getMovieReviewService, id
    ).flow
}