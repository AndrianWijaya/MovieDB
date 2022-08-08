package com.iglo.moviedb.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iglo.api_service.use_case.GetMovieDetailUseCase
import com.iglo.api_service.use_case.GetMovieReviewUseCase
import com.iglo.api_service.use_case.GetMovieVideoUseCase
import com.iglo.common.ResponseApps
import com.iglo.common.entity.movie_detail.MovieDetailsResponse
import com.iglo.common.entity.movie_review.Review
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieReviewUseCase: GetMovieReviewUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase,
    application: Application
) : AndroidViewModel(application) {

    val data = MutableLiveData<ResponseApps<MovieDetailsResponse>>()
    val dataPaging = MutableLiveData<PagingData<Review>>()
    val movie = MutableLiveData<Int>()
    val video = MutableLiveData<String>()


    fun getMovieDetail(movieId : Int){
        viewModelScope.launch {
            getMovieDetailUseCase.invoke(movieId).collect{
                data.postValue(it)
            }
            getMovieVideoUseCase.invoke(movieId).collect{
                video.postValue(it.data?.results?.last()?.key?.ifEmpty { "jLMBLuGJTsA" })
            }
            getMovieReviewUseCase.invoke(movieId).cachedIn(viewModelScope).collect{
                dataPaging.postValue(it)
            }

        }
    }

}