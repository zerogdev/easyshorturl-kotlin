package com.zerogdev.easyshorturl.kotlin.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zerogdev.easyshorturl.kotlin.domain.repositories.CoinsRepository
import com.zerogdev.easyshorturl.kotlin.presentation.list.mappers.UiCoinMapper
import com.zerogdev.easyshorturl.kotlin.util.NumberFormatter

@Suppress("UNCHECKED_CAST")
object ListFragmentViewModelFactory: ViewModelProvider.Factory {

    private lateinit var repository: CoinsRepository

    fun inject(repository: CoinsRepository) {
        ListFragmentViewModelFactory.repository = repository
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListFragmentViewModel(
            repository,
            UiCoinMapper(NumberFormatter())
        ) as T
    }
}