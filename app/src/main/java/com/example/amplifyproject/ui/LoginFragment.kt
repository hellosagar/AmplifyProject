package com.example.amplifyproject.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.amplifyframework.core.Amplify
import com.example.amplifyproject.R
import com.example.amplifyproject.databinding.FragmentLoginBinding
import com.example.amplifyproject.other.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Amplify.Auth.fetchAuthSession(
            { result -> Timber.i("AmplifyQuickstart $result") },
            { error -> Timber.e("AmplifyQuickstart $error") }
        )

        initViews()
    }

    private fun initViews() {
        binding.etUsername.setOnClickListener {
            val username = binding.etUsername.text.toString()
        }
        binding.etPassword.setOnClickListener {
            val password = binding.etPassword.text.toString()
        }
        binding.btnLogin.setOnClickListener {

        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }

    }
}