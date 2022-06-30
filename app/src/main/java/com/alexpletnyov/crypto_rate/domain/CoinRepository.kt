package com.alexpletnyov.crypto_rate.domain

import androidx.lifecycle.LiveData

interface CoinRepository {

	fun getCoinInfoList(): List<LiveData<CoinInfoEntity>>

	fun getCoinInfo(fromSymbol: String): LiveData<CoinInfoEntity>
}