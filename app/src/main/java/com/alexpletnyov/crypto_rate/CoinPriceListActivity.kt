package com.alexpletnyov.crypto_rate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alexpletnyov.crypto_rate.adapters.CoinInfoAdapter
import com.alexpletnyov.crypto_rate.databinding.ActivityCoinPriceListBinding
import com.alexpletnyov.crypto_rate.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

	private val viewModel by lazy {
		ViewModelProvider(
			this,
			ViewModelProvider.AndroidViewModelFactory.getInstance(application)
		)[CoinViewModel::class.java]
	}

	private val binding by lazy {
		ActivityCoinPriceListBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		val adapter = CoinInfoAdapter(this)
		adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
			override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
				Log.d("ON_CLICK_TEST", coinPriceInfo.fromSymbol)
			}
		}
		binding.rvPriceList.adapter = adapter
		viewModel.priceList.observe(this) {
			adapter.coinInfoList = it
		}
	}
}