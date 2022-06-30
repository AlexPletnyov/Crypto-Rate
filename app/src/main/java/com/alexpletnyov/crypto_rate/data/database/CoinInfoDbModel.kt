package com.alexpletnyov.crypto_rate.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexpletnyov.crypto_rate.data.network.ApiFactory
import com.alexpletnyov.crypto_rate.utility.convertTimestampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
	@PrimaryKey
	val fromSymbol: String,
	val toSymbol: String?,
	val price: Double?,
	val lastUpdate: Long?,
	val highDay: Double?,
	val lowDay: Double?,
	val lastMarket: String?,
	val imageUrl: String?
)