package com.example.amplifyproject.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.amplifyproject.R
import com.example.amplifyproject.databinding.FragmentRegisterBinding
import com.example.amplifyproject.other.BaseFragment
import com.example.amplifyproject.other.Resource
import com.example.amplifyproject.other.Status
import com.example.amplifyproject.repositories.AuthRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews()
    }

    private fun initViews() {

        val username = binding.etName.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        binding.btnRegister.setOnClickListener {
            viewModel.createUser().observe(viewLifecycleOwner, Observer {
                when(it.status){
                    Status.LOADING -> {
                        Timber.d("Loading")
                    }
                    Status.SUCCESS -> {
                        Timber.d("Success")
                    }
                    Status.ERROR -> {
                        Timber.d("Error")
                    }
                }
            })
        }
    }
}

class RegisterViewModel @ViewModelInject constructor(
    private val repository: AuthRepo
) : ViewModel() {

    fun createUser(): LiveData<Resource<String>> =  repository.createUser()

}