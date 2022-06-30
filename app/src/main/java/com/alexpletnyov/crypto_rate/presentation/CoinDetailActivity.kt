package com.alexpletnyov.crypto_rate.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alexpletnyov.crypto_rate.R
import com.alexpletnyov.crypto_rate.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

	private val viewModel by lazy {
		ViewModelProvider(this)[CoinViewModel::class.java]
	}

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
		viewModel.getDetailInfo(fromSymbol).observe(this) {
			with(binding) {
				tvPrice.text = it.price
				tvMinPrice.text = it.lowDay
				tvMaxPrice.text = it.highDay
				tvLastMarket.text = it.lastMarket
				tvLastUpdateTime.text = it.lastUpdate
				Picasso.get().load(it.imageUrl).into(ivLogoCoin)
				tvSymbols.text = String.format(
					this@CoinDetailActivity.resources.getString(R.string.symbols_template),
					it.fromSymbol,
					it.toSymbol
				)
			}

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