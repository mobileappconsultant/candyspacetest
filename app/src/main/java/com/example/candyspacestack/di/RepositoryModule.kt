package com.example.candyspacestack.di

import com.example.candyspacestack.data.repository.StackSearchRepository
import com.example.candyspacestack.data.repository.StackSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindStackRepository(stackSearchRepositoryImpl: StackSearchRepositoryImpl): StackSearchRepository
}
