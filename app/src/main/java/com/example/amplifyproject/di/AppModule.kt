package com.example.amplifyproject.di

import com.amplifyframework.auth.AuthCategory
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.category.Category
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAmplifyCategory():AuthCategory = Amplify.Auth

}