package com.zerogdev.easyshorturl.kotlin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zerogdev.easyshorturl.kotlin.domain.repositories.CoinsRepository

object CoinsSharedViewModelFactory: ViewModelProvider.Factory {

    private lateinit var repository: CoinsRepository

    fun inject(repository: CoinsRepository) {
        CoinsSharedViewModelFactory.repository = repository
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoinsSharedViewModel() as T
    }
}