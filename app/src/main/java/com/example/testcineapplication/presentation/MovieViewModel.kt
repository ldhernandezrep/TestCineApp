package com.example.testcineapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testcineapplication.core.ResultsState
import com.example.testcineapplication.repository.LoginRepository
import com.example.testcineapplication.repository.MoviesResourceRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repoMovies: MoviesResourceRepository) : ViewModel() {


    fun getMoviesAndResourcesByCine() = liveData(
        viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        emit(ResultsState.Loading())
        try {
            emit(
                ResultsState.Success(
                    repoMovies.getMoviesAndResourcesByCine()
                )
            )
        } catch (ex: Exception) {
            emit(ResultsState.Failure(ex))
        }
    }


}

class MovieViewModelFactory(private val repoMovies: MoviesResourceRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesResourceRepository::class.java)
            .newInstance(repoMovies)
    }

}
