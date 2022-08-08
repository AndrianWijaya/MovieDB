package com.iglo.api_service.use_case

import com.iglo.api_service.service.movie_detail.MovieDetailService
import com.iglo.common.ResponseApps
import com.iglo.common.entity.movie_detail.MovieDetailsResponse
import kotlinx.coroutines.flow.flow

class GetMovieDetailUseCase(
    private val getMovieDetailService: MovieDetailService
) {
    operator fun invoke(id : Int) = flow<ResponseApps<MovieDetailsResponse>> {
        emit(ResponseApps.loading())
        val data = getMovieDetailService.getMovieDetails(id)
        if (data.isSuccessful){
            data.body()?.let {
                emit(ResponseApps.success(it))
            } ?: kotlin.run {
                emit(ResponseApps.errorBackend(
                    data.code(),
                    null
                ))
            }
        } else{
            emit(ResponseApps.errorBackend(
                data.code(),
                data.errorBody()
            ))
        }
    }
}