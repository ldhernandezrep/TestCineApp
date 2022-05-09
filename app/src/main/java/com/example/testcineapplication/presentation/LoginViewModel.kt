package com.example.testcineapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testcineapplication.core.ResultsState
import com.example.testcineapplication.repository.LoginRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repoUsuario: LoginRepository) : ViewModel() {


    /**
     * Obtiene el inciio de sesion apartir de un usuario y una contraseña
     */
    fun ObtenerUsuarioPorPasswordAndEmail(email: String, passwordUsuario: String) = liveData(
        viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        emit(ResultsState.Loading())
        try {
            emit(
                ResultsState.Success(
                    repoUsuario.signIn(
                        email,
                        passwordUsuario
                    )
                )
            )
        } catch (ex: Exception) {
            emit(ResultsState.Failure(ex))
        }
    }

}

class LoginViewModelFactory(private val repoUsuario: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginRepository::class.java).newInstance(repoUsuario)
    }
}