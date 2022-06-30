package com.alexpletnyov.crypto_rate.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.alexpletnyov.crypto_rate.data.database.AppDatabase
import com.alexpletnyov.crypto_rate.data.mapper.CoinMapper
import com.alexpletnyov.crypto_rate.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
	context: Context,
	workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

	private val coinInfoDao = AppDatabase.getInstance(context).coinPriceInfoDao()
	private val apiService = ApiFactory.apiService
	private val mapper = CoinMapper()

	override suspend fun doWork(): Result {
		while (true) {
			try {
				val topCoins = apiService.getTopCoinsInfo(limit = 100)
				val fromSymbols = mapper.mapNamesListToString(topCoins)
				val jsonContainer = apiService.getFullPriceList(fromSymbols = fromSymbols)
				val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
				val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
				coinInfoDao.insertPriceList(dbModelList)
			} catch (e: Exception) {
			}
			delay(10_000)
		}
	}

	companion object {

		const val NAME = "RefreshDataWorker"

		fun makeRequest(): OneTimeWorkRequest {
			return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
		}
	}
}