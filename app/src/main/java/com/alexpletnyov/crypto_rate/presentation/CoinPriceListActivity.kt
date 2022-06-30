package com.alexpletnyov.crypto_rate.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alexpletnyov.crypto_rate.R
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
				if (isOnePaneMode()) {
					launchDetailActivity(coinPriceInfo.fromSymbol)
				} else {
					launchDetailFragment(coinPriceInfo.fromSymbol)
				}
			}
		}
		binding.rvPriceList.adapter = adapter
		binding.rvPriceList.itemAnimator = null
		viewModel.coinInfoList.observe(this) {
			adapter.submitList(it)
		}
	}

	private fun isOnePaneMode() = binding.fragmentContainer == null

	private fun launchDetailActivity(fromSymbol: String) {
		val intent = CoinDetailActivity.newIntent(
			this@CoinPriceListActivity,
			fromSymbol
		)
		startActivity(intent)
	}

	private fun launchDetailFragment(fromSymbol: String) {
		supportFragmentManager.popBackStack()
		supportFragmentManager
			.beginTransaction()
			.replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
			.addToBackStack(null)
			.commit()
	}
}