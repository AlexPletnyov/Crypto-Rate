package com.alexpletnyov.crypto_rate.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceInfo(
	@SerializedName("TYPE")
	@Expose
	private val type: String? = null,

	@SerializedName("MARKET")
	@Expose
	private val market: String? = null,

	@SerializedName("FROMSYMBOL")
	@Expose
	private val fromSymbol: String? = null,

	@SerializedName("TOSYMBOL")
	@Expose
	private val toSymbol: String? = null,

	@SerializedName("FLAGS")
	@Expose
	private val flags: String? = null,

	@SerializedName("PRICE")
	@Expose
	private val price: Double? = null,

	@SerializedName("LASTUPDATE")
	@Expose
	private val lastUpdate: Int? = null,

	@SerializedName("LASTVOLUME")
	@Expose
	private val lastvolume: Double? = null,

	@SerializedName("LASTVOLUMETO")
	@Expose
	private val lastVolumeto: Double? = null,

	@SerializedName("LASTTRADEID")
	@Expose
	private val lastTradeid: String? = null,

	@SerializedName("VOLUMEDAY")
	@Expose
	private val volumeDay: Double? = null,

	@SerializedName("VOLUMEDAYTO")
	@Expose
	private val volumeDayTo: Double? = null,

	@SerializedName("VOLUME24HOUR")
	@Expose
	private val volume24hour: Double? = null,

	@SerializedName("VOLUME24HOURTO")
	@Expose
	private val volume24hourTo: Double? = null,

	@SerializedName("OPENDAY")
	@Expose
	private val openDay: Double? = null,

	@SerializedName("HIGHDAY")
	@Expose
	private val highDay: Double? = null,

	@SerializedName("LOWDAY")
	@Expose
	private val lowDay: Double? = null,

	@SerializedName("OPEN24HOUR")
	@Expose
	private val open24hour: Double? = null,

	@SerializedName("HIGH24HOUR")
	@Expose
	private val high24hour: Double? = null,

	@SerializedName("LOW24HOUR")
	@Expose
	private val low24hour: Double? = null,

	@SerializedName("LASTMARKET")
	@Expose
	private val lastMarket: String? = null,

	@SerializedName("VOLUMEHOUR")
	@Expose
	private val volumeHour: Double? = null,

	@SerializedName("VOLUMEHOURTO")
	@Expose
	private val volumeHourTo: Double? = null,

	@SerializedName("OPENHOUR")
	@Expose
	private val openHour: Double? = null,

	@SerializedName("HIGHHOUR")
	@Expose
	private val highHour: Double? = null,

	@SerializedName("LOWHOUR")
	@Expose
	private val lowHour: Double? = null,

	@SerializedName("TOPTIERVOLUME24HOUR")
	@Expose
	private val topTierVolume24hour: Double? = null,

	@SerializedName("TOPTIERVOLUME24HOURTO")
	@Expose
	private val topTierVolume24hourTo: Double? = null,

	@SerializedName("CHANGE24HOUR")
	@Expose
	private val change24hour: Double? = null,

	@SerializedName("CHANGEPCT24HOUR")
	@Expose
	private val changePct24hour: Double? = null,

	@SerializedName("CHANGEDAY")
	@Expose
	private val changeDay: Double? = null,

	@SerializedName("CHANGEPCTDAY")
	@Expose
	private val changePctDay: Double? = null,

	@SerializedName("CHANGEHOUR")
	@Expose
	private val changeHour: Double? = null,

	@SerializedName("CHANGEPCTHOUR")
	@Expose
	private val changePctHour: Double? = null,

	@SerializedName("CONVERSIONTYPE")
	@Expose
	private val conversionType: String? = null,

	@SerializedName("CONVERSIONSYMBOL")
	@Expose
	private val conversionSymbol: String? = null,

	@SerializedName("SUPPLY")
	@Expose
	private val supply: Int? = null,

	@SerializedName("MKTCAP")
	@Expose
	private val mktCap: Double? = null,

	@SerializedName("MKTCAPPENALTY")
	@Expose
	private val mktCapPenalty: Int? = null,

	@SerializedName("CIRCULATINGSUPPLY")
	@Expose
	private val circulatingSupply: Int? = null,

	@SerializedName("CIRCULATINGSUPPLYMKTCAP")
	@Expose
	private val circulatingSupplyMktCap: Double? = null,

	@SerializedName("TOTALVOLUME24H")
	@Expose
	private val totalVolume24h: Double? = null,

	@SerializedName("TOTALVOLUME24HTO")
	@Expose
	private val totalVolume24hTo: Double? = null,

	@SerializedName("TOTALTOPTIERVOLUME24H")
	@Expose
	private val totalTopTierVolume24h: Double? = null,

	@SerializedName("TOTALTOPTIERVOLUME24HTO")
	@Expose
	private val totalTopTierVolume24hTo: Double? = null,

	@SerializedName("IMAGEURL")
	@Expose
	private val imageurl: String? = null
)