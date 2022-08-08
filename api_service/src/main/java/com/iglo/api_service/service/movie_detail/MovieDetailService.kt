package com.iglo.api_service.service.movie_detail

import com.iglo.common.Constant
import com.iglo.common.entity.movie_detail.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = Constant.API_KEY
    ) : Response<MovieDetailsResponse>
}