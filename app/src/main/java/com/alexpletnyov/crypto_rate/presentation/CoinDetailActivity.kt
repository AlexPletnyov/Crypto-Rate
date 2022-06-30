package com.alexpletnyov.crypto_rate.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alexpletnyov.crypto_rate.R
import com.alexpletnyov.crypto_rate.data.network.ApiFactory.BASE_IMAGE_URL
import com.alexpletnyov.crypto_rate.databinding.ActivityCoinDetailBinding
import com.alexpletnyov.crypto_rate.utility.TimePatterns
import com.alexpletnyov.crypto_rate.utility.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

	private lateinit var viewModel: CoinViewModel

	private val binding by lazy {
		ActivityCoinDetailBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
			finish()
			return
		}
		val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)!!
		viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
		viewModel.getDetailInfo(fromSymbol).observe(this) {
			Picasso.get().load(BASE_IMAGE_URL + it.imageUrl).into(binding.ivLogoCoin)
			binding.tvSymbols.text = String.format(
				this.resources.getString(R.string.symbols_template),
				it.fromSymbol,
				it.toSymbol
			)
			binding.tvPrice.text = it.price.toString()
			binding.tvMinPrice.text = it.lowDay.toString()
			binding.tvMaxPrice.text = it.highDay.toString()
			binding.tvLastMarket.text = it.lastMarket
			binding.tvLastUpdateTime.text = convertTimestampToTime(
				it.lastUpdate,
				TimePatterns.DATA_AND_TIME
			)
		}
	}

	companion object {

		private const val EXTRA_FROM_SYMBOL = "fSym"

		fun newIntent(context: Context, fromSymbol: String): Intent {
			val intent = Intent(context, CoinDetailActivity::class.java)
			intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
			return intent
		}
	}
}