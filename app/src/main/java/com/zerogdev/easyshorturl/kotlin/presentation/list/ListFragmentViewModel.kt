package com.zerogdev.easyshorturl.kotlin.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerogdev.easyshorturl.kotlin.domain.Result
import com.zerogdev.easyshorturl.kotlin.domain.model.Coin
import com.zerogdev.easyshorturl.kotlin.domain.repositories.CoinsRepository
import com.zerogdev.easyshorturl.kotlin.presentation.list.mappers.UiCoinMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListFragmentViewModel(
    private val coinsRepository: CoinsRepository,
    private val uiConinMapper: UiCoinMapper
): ViewModel() {



    private val _viewState = MutableStateFlow(ListFragmentViewState())
    val viewState: StateFlow<ListFragmentViewState> get() = _viewState

    private val _viewEffects = MutableSharedFlow<ListFragmentViewEffects>()
    val viewEffects: SharedFlow<ListFragmentViewEffects> get() = _viewEffects

    fun requestCoinList() {
        if (viewState.value.coins.isNotEmpty()) return

        viewModelScope.launch {
            when (val result = coinsRepository.getCoins()) {
                is Result.Success -> handleCoinList(result.value)
                is Result.Failure -> handleFailure(result.cause)
            }
        }
    }

    private fun handleCoinList(coins: List<Coin>) {
        val uiCoins = coins.map { uiConinMapper.toUi(it) }
        _viewState.value = ListFragmentViewState(false, uiCoins)
    }

    private suspend fun handleFailure(cause: Throwable) {
        _viewEffects.emit(ListFragmentViewEffects.Failure(cause))
    }

}