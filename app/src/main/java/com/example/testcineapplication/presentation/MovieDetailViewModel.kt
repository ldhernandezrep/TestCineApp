package com.example.testcineapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testcineapplication.repository.MoviesResourceRepository

class MovieDetailVieModel : ViewModel() {


}

class MovieDetailVieModel(
    private val repoMovies: MoviesResourceRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesResourceRepository::class.java)
            .newInstance(repoMovies)
    }

}
