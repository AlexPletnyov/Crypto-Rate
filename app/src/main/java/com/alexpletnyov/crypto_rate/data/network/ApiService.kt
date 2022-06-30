package com.alexpletnyov.crypto_rate.data.network

import com.alexpletnyov.crypto_rate.data.network.model.CoinInfoJsonContainerDto
import com.alexpletnyov.crypto_rate.data.network.model.CoinNamesListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("top/totalvolfull")
	suspend fun getTopCoinsInfo(
		@Query(QUERY_PARAM_API_KEY) apiKey: String = "",
		@Query(QUERY_PARAM_LIMIT) limit: Int = 100,
		@Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
	): CoinNamesListDto

	@GET("pricemultifull")
	suspend fun getFullPriceList(
		@Query(QUERY_PARAM_API_KEY) apiKey: String = "",
		@Query(QUERY_PARAM_FROM_SYMBOLS) fromSymbols: String,
		@Query(QUERY_PARAM_TO_SYMBOLS) toSymbols: String = CURRENCY
	): CoinInfoJsonContainerDto

	companion object {
		private const val QUERY_PARAM_API_KEY = "apy_key"
		private const val QUERY_PARAM_LIMIT = "limit"
		private const val QUERY_PARAM_TO_SYMBOL = "tsym"
		private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
		private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
		private const val CURRENCY = "USD"
	}
}