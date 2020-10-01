package com.example.amplifyproject.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserState
import com.amazonaws.mobile.client.UserStateDetails
import com.amplifyframework.core.Amplify
import com.example.amplifyproject.R
import com.example.amplifyproject.databinding.FragmentLoginBinding
import com.example.amplifyproject.other.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.Exception


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Amplify.Auth.fetchAuthSession(
            { result ->
                Timber.i("AmplifyQuickstart $result")
            },
            { error ->
                Timber.e("AmplifyQuickstart $error")
            }
        )

        AWSMobileClient.getInstance().initialize(
            requireContext(),
            object : Callback<UserStateDetails?> {

                override fun onResult(result: UserStateDetails?) {
                    when (result?.userState) {
                        UserState.SIGNED_IN -> runOnUiThread {
                            Amplify.Auth.fetchAuthSession(
                                { result -> Timber.i("AmplifyQuickstart $result") },
                                { error ->
                                    Timber.e("AmplifyQuickstart $error")
                                }
                            )

                        }
                        UserState.SIGNED_OUT -> runOnUiThread {
                            Amplify.Auth.fetchAuthSession(
                                { result ->
                                    Timber.i("AmplifyQuickstart $result")
                                },
                                { error ->
                                    Timber.e("AmplifyQuickstart $error")
                                }
                            )

                        }
                        else -> {
                            Amplify.Auth.fetchAuthSession(
                                { result -> Timber.i("AmplifyQuickstart $result") },
                                { error ->
                                    Timber.e("AmplifyQuickstart $error")
                                }
                            )

                            AWSMobileClient.getInstance().signOut()
                        }
                    }
                }

                override fun onError(e: Exception?) {
                    Timber.e(e)
                }
            })


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