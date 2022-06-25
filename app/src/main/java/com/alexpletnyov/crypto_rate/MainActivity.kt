package com.alexpletnyov.crypto_rate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alexpletnyov.crypto_rate.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

	private val compositeDisposable = CompositeDisposable()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val disposable = ApiFactory.apiService.getFullPriceList(
			fromSymbols = "BTC,ETH,EOS"
		)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				Log.d("TEST_OF_LOADING_DATA", "Success: $it")
			}, {
				Log.d("TEST_OF_LOADING_DATA", "Error: $it")
			})
		compositeDisposable.add(disposable)
	}

	override fun onDestroy() {
		super.onDestroy()
		compositeDisposable.dispose()
	}
}