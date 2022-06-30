package com.alexpletnyov.crypto_rate.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.alexpletnyov.crypto_rate.data.database.AppDatabase
import com.alexpletnyov.crypto_rate.data.model.CoinPriceInfo
import com.alexpletnyov.crypto_rate.data.model.CoinPriceInfoRawData
import com.alexpletnyov.crypto_rate.data.network.ApiFactory
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

	private val db = AppDatabase.getInstance(application)
	private val compositeDisposable = CompositeDisposable()

	val priceList = db.coinPriceInfoDao().getPriceList()

	init {
		loadData()
	}

	fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
		return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
	}

	private fun loadData() {
		val disposable = ApiFactory.apiService.getTopCoinsInfo()
			.map {
				it.datumList?.map { datum -> datum.coinInfo?.name }?.joinToString(separator = ",")
			}
			.flatMap { ApiFactory.apiService.getFullPriceList(fromSymbols = it) }
			.map { getPriceListFromRawData(it) }
			.delaySubscription(1, TimeUnit.SECONDS)
			.repeat()
			.retry()
			.subscribeOn(Schedulers.io())
			.subscribe({
				db.coinPriceInfoDao().insertPriceList(it)
//				Log.d("TEST_OF_LOADING_DATA", "Success: $it")
			}, {
				Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
			})
		compositeDisposable.add(disposable)
	}

	private fun getPriceListFromRawData(
		coinPriceInfoRawData: CoinPriceInfoRawData
	): List<CoinPriceInfo> {
		val result = ArrayList<CoinPriceInfo>()

		val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
		val coinKeySet = jsonObject.keySet()

		coinKeySet.forEach { coinKey ->
			val currencyJsonObject = jsonObject.getAsJsonObject(coinKey)
			val currencyKeySet = currencyJsonObject.keySet()

			currencyKeySet.forEach { currencyKey ->
				val priceInfo = Gson().fromJson(
					currencyJsonObject.getAsJsonObject(currencyKey),
					CoinPriceInfo::class.java
				)
				result.add(priceInfo)
			}
		}
		return result
	}

	override fun onCleared() {
		super.onCleared()
		compositeDisposable.dispose()
	}
}