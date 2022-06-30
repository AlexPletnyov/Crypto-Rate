package com.alexpletnyov.crypto_rate.domain

data class CoinInfoEntity(

	val fromSymbol: String,
	val toSymbol: String?,
	val price: Double?,
	val lastUpdate: Int?,
	val highDay: Double?,
	val lowDay: Double?,
	val lastMarket: String?,
	val imageUrl: String?
)
