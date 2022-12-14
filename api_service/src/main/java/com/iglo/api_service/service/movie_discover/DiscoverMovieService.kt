package com.iglo.api_service.service.movie_discover


import com.iglo.common.Constant
import com.iglo.common.entity.discover_movie.DiscoverMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverMovieService {
    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("with_genres") genre : String,
        @Query("page") page : Int = 1,
        @Query("api_key") apiKey : String = Constant.API_KEY
    ) : Response<DiscoverMovieResponse>
}