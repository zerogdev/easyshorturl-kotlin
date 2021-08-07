package com.zerogdev.easyshorturl.kotlin.presentation.list

import com.zerogdev.easyshorturl.kotlin.presentation.list.model.UiCoin

data class ListFragmentViewState(
    val loading: Boolean = true,
    val coins: List<UiCoin> = emptyList()
)