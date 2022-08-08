package com.iglo.api_service.service.genre

import com.iglo.common.Constant
import com.iglo.common.entity.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenreMovie(
        @Query("api_key") apiKey : String = Constant.API_KEY
    ) : Response<GenreResponse>
}