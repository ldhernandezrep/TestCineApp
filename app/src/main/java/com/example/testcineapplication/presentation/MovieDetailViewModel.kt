package com.example.testcineapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testcineapplication.core.ResultsState
import com.example.testcineapplication.repository.MoviesResourceRepository
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel(
    private val repoMovies: MoviesResourceRepository
) : ViewModel() {


    fun getMoviesRouteById(idMovie: Int) = liveData(
        viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        emit(ResultsState.Loading())
        try {
            emit(
                ResultsState.Success(
                    repoMovies.getMoviesBYId(idMovie)
                )
            )
        } catch (ex: Exception) {
            emit(ResultsState.Failure(ex))
        }
    }

}

class MovieDetailViewModelFactory(
    private val repoMovies: MoviesResourceRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesResourceRepository::class.java)
            .newInstance(repoMovies)
    }

}
