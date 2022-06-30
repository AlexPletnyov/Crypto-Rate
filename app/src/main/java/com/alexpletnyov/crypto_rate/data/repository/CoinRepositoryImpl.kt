package com.alexpletnyov.crypto_rate.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.alexpletnyov.crypto_rate.data.database.AppDatabase
import com.alexpletnyov.crypto_rate.data.mapper.CoinMapper
import com.alexpletnyov.crypto_rate.data.workers.RefreshDataWorker
import com.alexpletnyov.crypto_rate.domain.CoinInfo
import com.alexpletnyov.crypto_rate.domain.CoinRepository

class CoinRepositoryImpl(private val application: Application) : CoinRepository {

	private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
	private val mapper = CoinMapper()

	override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
		return Transformations.map(coinInfoDao.getPriceList()) { list ->
			list.map {
				mapper.mapDbModelToEntity(it)
			}
		}
	}

	override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
		return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
			mapper.mapDbModelToEntity(it)
		}
	}

	override fun loadData() {
		val workManager = WorkManager.getInstance(application)
		workManager.enqueueUniqueWork(
			RefreshDataWorker.NAME,
			ExistingWorkPolicy.REPLACE,
			RefreshDataWorker.makeRequest()
		)
	}
}