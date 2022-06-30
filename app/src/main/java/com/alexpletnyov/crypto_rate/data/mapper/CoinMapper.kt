package com.alexpletnyov.crypto_rate.data.mapper

import com.alexpletnyov.crypto_rate.data.database.CoinInfoDbModel
import com.alexpletnyov.crypto_rate.data.network.model.CoinInfoDto
import com.alexpletnyov.crypto_rate.data.network.model.CoinInfoJsonContainerDto
import com.alexpletnyov.crypto_rate.data.network.model.CoinNamesListDto
import com.alexpletnyov.crypto_rate.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinMapper {

	fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
		fromSymbol = dto.fromSymbol,
		toSymbol = dto.toSymbol,
		price = dto.price,
		lastUpdate = dto.lastUpdate,
		highDay = dto.highDay,
		lowDay = dto.lowDay,
		lastMarket = dto.lastMarket,
		imageUrl = BASE_IMAGE_URL + dto.imageurl
	)

	fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
		val result = mutableListOf<CoinInfoDto>()
		val jsonObject = jsonContainer.json ?: return result
		val coinKeySet = jsonObject.keySet()
		coinKeySet.forEach { coinKey ->
			val currencyJsonObject = jsonObject.getAsJsonObject(coinKey)
			val currencyKeySet = currencyJsonObject.keySet()

			currencyKeySet.forEach { currencyKey ->
				val priceInfo = Gson().fromJson(
					currencyJsonObject.getAsJsonObject(currencyKey),
					CoinInfoDto::class.java
				)
				result.add(priceInfo)
			}
		}
		return result
	}

	fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
		return namesListDto.names?.map {
			it.coinName?.name
		}?.joinToString(separator = ",") ?: ""
	}

	fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo = CoinInfo(
		fromSymbol = dbModel.fromSymbol,
		toSymbol = dbModel.toSymbol,
		price = dbModel.price.toString(),
		lastUpdate = convertTimestampToTime(dbModel.lastUpdate, TIME_PATTERN_ONLY_TIME),
		highDay = dbModel.highDay.toString(),
		lowDay = dbModel.lowDay.toString(),
		lastMarket = dbModel.lastMarket,
		imageUrl = dbModel.imageUrl
	)

	private fun convertTimestampToTime(timestamp: Long?, pattern: String): String {
		if (timestamp == null) return ""
		val stamp = Timestamp(timestamp * 1000)
		val date = Date(stamp.time)
		val sdf = SimpleDateFormat(pattern, Locale.getDefault())
		sdf.timeZone = TimeZone.getDefault()
		return sdf.format(date)
	}

	companion object {

		const val BASE_IMAGE_URL = "https://cryptocompare.com"
		private const val TIME_PATTERN_ONLY_TIME = "HH:mm:ss"
		private const val TIME_PATTERN_DATA_AND_TIME = "yy-MM-dd HH:mm:ss"
	}
}