package com.example.amplifyproject.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amplifyframework.auth.AuthCategory
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.example.amplifyproject.other.Resource
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class AuthRepo @Inject constructor(
    private val authCategory: AuthCategory
) {

    private var userCreatedLiveData = MutableLiveData<Resource<String>>()

   fun createUser(): LiveData<Resource<String>> {

        userCreatedLiveData.postValue(Resource.loading(null))

        authCategory.signUp(
            "username",
            "Password123",
            AuthSignUpOptions.builder().userAttribute(
                AuthUserAttributeKey.email(), "my@email.com").build(),
            { result ->
                Timber.i("AuthQuickStart Result: $result")
                userCreatedLiveData.postValue(Resource.success("User created"))
            },
            { error ->
                userCreatedLiveData.postValue(Resource.error("User created",null))
                Timber.e(error, "AuthQuickStart Sign up failed")
            }
        )
        return userCreatedLiveData
    }

}