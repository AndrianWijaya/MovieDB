package com.iglo.api_service.service.review

import com.iglo.common.Constant
import com.iglo.common.entity.movie_review.MovieReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieReviewService {
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Constant.API_KEY
    ) : Response<MovieReviewsResponse>
}