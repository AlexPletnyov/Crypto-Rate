package com.alexpletnyov.crypto_rate.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alexpletnyov.crypto_rate.databinding.ActivityCoinPriceListBinding
import com.alexpletnyov.crypto_rate.domain.CoinInfo
import com.alexpletnyov.crypto_rate.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

	private val viewModel by lazy {
		ViewModelProvider(this)[CoinViewModel::class.java]
	}

	private val binding by lazy {
		ActivityCoinPriceListBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		val adapter = CoinInfoAdapter(this)
		adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
			override fun onCoinClick(coinPriceInfo: CoinInfo) {
				val intent = CoinDetailActivity.newIntent(
					this@CoinPriceListActivity,
					coinPriceInfo.fromSymbol
				)
				startActivity(intent)
			}
		}
		binding.rvPriceList.adapter = adapter
		binding.rvPriceList.itemAnimator = null
		viewModel.coinInfoList.observe(this) {
			adapter.submitList(it)
		}
	}
}