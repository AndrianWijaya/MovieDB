package com.iglo.api_service.service.video

import com.iglo.common.Constant
import com.iglo.common.entity.video.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieVideoService {
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = Constant.API_KEY
    ) : Response<MovieVideoResponse>
}