package com.alexpletnyov.crypto_rate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

	private val viewModel by lazy {
		ViewModelProvider(
			this,
			ViewModelProvider.AndroidViewModelFactory.getInstance(application)
		)[CoinViewModel::class.java]
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
//		viewModel.priceList.observe(this) {
//			Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
//		}
		viewModel.getDetailInfo("BTC").observe(this) {
			Log.d("TEST_OF_LOADING_DATA", "getDetailInfo: $it")
		}
	}
}