package com.alexpletnyov.crypto_rate.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoListOfData(
	@SerializedName("Data")
	@Expose
	private val data: List<Datum>? = null
)