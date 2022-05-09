package com.example.testcineapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.testcineapplication.cineapiservice.CinepolisApiService
import com.example.testcineapplication.core.ResultsState
import com.example.testcineapplication.data.local.DataBaseCine
import com.example.testcineapplication.data.local.LocalLoginDataSource
import com.example.testcineapplication.data.remote.RemoteLoginDataSource
import com.example.testcineapplication.databinding.ActivityLoginBinding
import com.example.testcineapplication.presentation.LoginViewModel
import com.example.testcineapplication.presentation.LoginViewModelFactory
import com.example.testcineapplication.repository.LoginRepositoryImple
import com.example.testcineapplication.ui.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModelUser by viewModels<LoginViewModel> {
        LoginViewModelFactory(
            LoginRepositoryImple(
                RemoteLoginDataSource(
                    CinepolisApiService.create()
                ), LocalLoginDataSource(DataBaseCine.getDatabase(this).loginDAO())
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener({
            if (binding.etUserName.text.isEmpty()) {
                //binding.tvRegistrarUsuario.error = "Ingrese su nombre de usuario"
                Toast.makeText(this, "Ingrese su nombre de usuario", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (binding.etPassword.text.isEmpty()) {
                Toast.makeText(this, "Ingrese su contraseñao", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            LoguearUsuario();
        })

    }

    private fun LoguearUsuario() {
        //nombre?.length ?: 0
        viewModelUser.ObtenerUsuarioPorPasswordAndEmail(
            binding.etUserName.text.toString(),
            binding.etPassword.text.toString()
        ).observe(this, { x ->
            when (x) {
                is ResultsState.Loading -> {
                    binding.llProgressBar.root.visibility = View.VISIBLE
                    binding.constrainLogin.visibility = View.GONE
                }
                is ResultsState.Success -> {
                    binding.llProgressBar.root.visibility = View.GONE
                    binding.constrainLogin.visibility = View.VISIBLE
                    NavigationToMain()
                    Log.d("Succes", "usuario ${x.data}")
                }
                is ResultsState.Failure -> {
                    binding.llProgressBar.root.visibility = View.GONE
                    binding.constrainLogin.visibility = View.VISIBLE
                    Log.d("Succes", "usuario ${x.exception.message}")
                }
            }
        })
    }

    //Falta poner si hay red y validar los campos que no esten vacios

    private fun NavigationToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}