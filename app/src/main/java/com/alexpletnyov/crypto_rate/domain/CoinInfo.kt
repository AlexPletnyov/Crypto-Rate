package com.alexpletnyov.crypto_rate.domain

data class CoinInfo(
	val fromSymbol: String,
	val toSymbol: String?,
	val price: String?,
	val lastUpdateTime: String?,
	val lastUpdateDataTime: String?,
	val highDay: String?,
	val lowDay: String?,
	val lastMarket: String?,
	val imageUrl: String
)
