package com.alexpletnyov.crypto_rate.data.network

import com.alexpletnyov.crypto_rate.data.model.CoinInfoListOfData
import com.alexpletnyov.crypto_rate.data.model.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("top/totalvolfull")
	fun getTopCoinsInfo(
		@Query(QUERY_PARAM_API_KEY) apiKey: String = "",
		@Query(QUERY_PARAM_LIMIT) limit: Int = 10,
		@Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
	): Single<CoinInfoListOfData>

	@GET("pricemultifull")
	fun getFullPriceList(
		@Query(QUERY_PARAM_API_KEY) apiKey: String = "",
		@Query(QUERY_PARAM_FROM_SYMBOLS) fromSymbols: String,
		@Query(QUERY_PARAM_TO_SYMBOLS) toSymbols: String = CURRENCY
	): Single<CoinPriceInfoRawData>

	companion object {
		private const val QUERY_PARAM_API_KEY = "apy_key"
		private const val QUERY_PARAM_LIMIT = "limit"
		private const val QUERY_PARAM_TO_SYMBOL = "tsym"
		private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
		private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
		private const val CURRENCY = "USD"
	}
}