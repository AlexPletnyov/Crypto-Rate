package com.alexpletnyov.crypto_rate.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alexpletnyov.crypto_rate.data.database.AppDatabase
import com.alexpletnyov.crypto_rate.data.mapper.CoinMapper
import com.alexpletnyov.crypto_rate.data.network.ApiFactory
import com.alexpletnyov.crypto_rate.domain.CoinInfo
import com.alexpletnyov.crypto_rate.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(application: Application) : CoinRepository {

	private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
	private val apiService = ApiFactory.apiService
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

	override suspend fun loadData() {
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
}