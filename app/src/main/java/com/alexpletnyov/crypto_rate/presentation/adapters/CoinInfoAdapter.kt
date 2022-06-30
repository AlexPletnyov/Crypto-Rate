package com.alexpletnyov.crypto_rate.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexpletnyov.crypto_rate.R
import com.alexpletnyov.crypto_rate.databinding.ItemCoinInfoBinding
import com.alexpletnyov.crypto_rate.data.model.CoinPriceInfo
import com.alexpletnyov.crypto_rate.utility.TimePatterns
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
	RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

	lateinit var binding: ItemCoinInfoBinding

	var coinInfoList: List<CoinPriceInfo> = listOf()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	var onCoinClickListener: OnCoinClickListener? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
		binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CoinInfoViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
		val coin = coinInfoList[position]
		holder.tvSymbols.text = String.format(
			context.resources.getString(R.string.symbols_template),
			coin.fromSymbol,
			coin.toSymbol
		)
		holder.tvPrice.text = coin.price.toString()
		holder.tvLastUpdateTime.text = String.format(
			context.resources.getString(R.string.last_update_template),
			coin.getFormattedTime(TimePatterns.TIME)
		)
		holder.itemView.setOnClickListener {
			onCoinClickListener?.onCoinClick(coin)
		}
		Picasso.get().load(coin.getFullImageUrl()).into(holder.ivLogoCoin)
	}

	override fun getItemCount() = coinInfoList.size

	inner class CoinInfoViewHolder(
		binding: ItemCoinInfoBinding
	) : RecyclerView.ViewHolder(binding.root) {
		val ivLogoCoin = binding.ivLogoCoin
		val tvSymbols = binding.tvSymbols
		val tvPrice = binding.tvPrice
		val tvLastUpdateTime = binding.tvLastUpdateTime
	}

	interface OnCoinClickListener {
		fun onCoinClick(coinPriceInfo: CoinPriceInfo)
	}
}