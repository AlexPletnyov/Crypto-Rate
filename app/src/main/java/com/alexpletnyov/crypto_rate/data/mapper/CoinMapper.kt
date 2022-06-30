package com.alexpletnyov.crypto_rate.data.mapper

import com.alexpletnyov.crypto_rate.data.database.CoinInfoDbModel
import com.alexpletnyov.crypto_rate.data.network.model.CoinInfoDto
import com.alexpletnyov.crypto_rate.data.network.model.CoinInfoJsonContainerDto
import com.alexpletnyov.crypto_rate.data.network.model.CoinNamesListDto
import com.alexpletnyov.crypto_rate.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {

	fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
		fromSymbol = dto.fromSymbol,
		toSymbol = dto.toSymbol,
		price = dto.price,
		lastUpdate = dto.lastUpdate,
		highDay = dto.highDay,
		lowDay = dto.lowDay,
		lastMarket = dto.lastMarket,
		imageUrl = dto.imageurl
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
		price = dbModel.price,
		lastUpdate = dbModel.lastUpdate,
		highDay = dbModel.highDay,
		lowDay = dbModel.lowDay,
		lastMarket = dbModel.lastMarket,
		imageUrl = dbModel.imageUrl
	)
}