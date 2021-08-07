package com.zerogdev.easyshorturl.kotlin.presentation.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.zerogdev.easyshorturl.kotlin.databinding.RecyclerViewCoinItemBinding
import com.zerogdev.easyshorturl.kotlin.presentation.list.CoinClickListener
import com.zerogdev.easyshorturl.kotlin.presentation.list.model.UiCoin
import com.zerogdev.easyshorturl.kotlin.presentation.loadImage

class CoinViewHolder(
    private val binding: RecyclerViewCoinItemBinding,
    private val listener: CoinClickListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(coin: UiCoin) {
        val (id, symbol, name, supply, marketCapUsd, priceUsd, changePercent24Hr, image) = coin
        with(binding) {
            coinImage.loadImage(image)
            coinName.text = name
            coinSymbol.text = symbol
            coinPrice.text = priceUsd
            coinSupply.text = supply
            coinMarketCap.text = marketCapUsd
            coinChange.text = changePercent24Hr
        }
        itemView.setOnClickListener { listener.onCoinClicked(id, name) }
    }

}