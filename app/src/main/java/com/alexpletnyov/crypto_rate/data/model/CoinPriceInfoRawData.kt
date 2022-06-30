package com.alexpletnyov.crypto_rate.data.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceInfoRawData(
	@SerializedName("RAW")
	@Expose
	val coinPriceInfoJsonObject: JsonObject?
)