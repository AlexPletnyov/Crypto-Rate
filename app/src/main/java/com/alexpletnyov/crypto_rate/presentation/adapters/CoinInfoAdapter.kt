package com.alexpletnyov.crypto_rate.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alexpletnyov.crypto_rate.R
import com.alexpletnyov.crypto_rate.databinding.ItemCoinInfoBinding
import com.alexpletnyov.crypto_rate.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(
	private val context: Context
) : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {

	var onCoinClickListener: OnCoinClickListener? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
		val binding = ItemCoinInfoBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
		return CoinInfoViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
		val coin = getItem(position)
		with(holder.binding) {
			tvSymbols.text = String.format(
				context.resources.getString(R.string.symbols_template),
				coin.fromSymbol,
				coin.toSymbol
			)
			val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
			tvPrice.text = coin.price.toString()
			root.setOnClickListener { onCoinClickListener?.onCoinClick(coin) }
			Picasso.get().load(coin.imageUrl).into(ivLogoCoin)
			tvLastUpdateTime.text = String.format(lastUpdateTemplate, coin.lastUpdateTime)
		}
	}

	interface OnCoinClickListener {
		fun onCoinClick(coinPriceInfo: CoinInfo)
	}
}