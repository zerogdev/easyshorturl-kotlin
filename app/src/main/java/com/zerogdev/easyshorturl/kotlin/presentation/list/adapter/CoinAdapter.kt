package com.zerogdev.easyshorturl.kotlin.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.zerogdev.easyshorturl.kotlin.databinding.RecyclerViewCoinItemBinding
import com.zerogdev.easyshorturl.kotlin.presentation.list.CoinClickListener
import com.zerogdev.easyshorturl.kotlin.presentation.list.model.UiCoin

class CoinAdapter(
    private val listener: CoinClickListener
) : ListAdapter<UiCoin, CoinViewHolder>(CoinDiffUtils()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(
            RecyclerViewCoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}