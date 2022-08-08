package com.iglo.api_service.use_case

import com.iglo.api_service.service.genre.GenreService
import com.iglo.common.ResponseApps
import com.iglo.common.entity.genre.Genre
import kotlinx.coroutines.flow.flow

class GetGenreUseCase(
    private val genreService: GenreService
) {
    operator fun invoke() = flow<ResponseApps<List<Genre>>> {
        emit(ResponseApps.loading())
        val dataGenre = genreService.getGenreMovie()
        if (dataGenre.isSuccessful){
            dataGenre.body()?.let {
                emit(ResponseApps.success(it.genres))
            }?: kotlin.run {
                emit(ResponseApps.errorBackend(
                    dataGenre.code(),
                    dataGenre.errorBody()
                ))
            }
        } else {
            emit(ResponseApps.errorBackend(
                dataGenre.code(),
                null
            ))
        }
    }
}