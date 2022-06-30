package com.alexpletnyov.crypto_rate.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfo(
	@SerializedName("Name")
	@Expose
	val name: String?
)