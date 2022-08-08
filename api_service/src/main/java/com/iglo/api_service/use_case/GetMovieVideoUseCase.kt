package com.iglo.api_service.use_case

import com.iglo.api_service.service.video.MovieVideoService
import com.iglo.common.ResponseApps
import com.iglo.common.entity.video.MovieVideoResponse
import kotlinx.coroutines.flow.flow

class GetMovieVideoUseCase(
    private val getMovieVideoService: MovieVideoService
) {
    operator fun invoke(id : Int) = flow<ResponseApps<MovieVideoResponse>> {
        emit(ResponseApps.loading())
        val data = getMovieVideoService.getMovieVideo(id)
        if (data.isSuccessful){
            data.body()?.let {
                emit(ResponseApps.success(it))
            } ?: kotlin.run {
                emit(ResponseApps.errorBackend(
                    data.code(),
                    null
                ))
            }
        }else{
            emit(ResponseApps.errorBackend(
                data.code(),
                data.errorBody()
            ))
        }
    }
}