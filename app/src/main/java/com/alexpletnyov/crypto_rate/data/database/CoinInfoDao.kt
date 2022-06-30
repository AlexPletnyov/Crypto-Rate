package com.alexpletnyov.crypto_rate.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexpletnyov.crypto_rate.data.network.model.CoinInfoDto

@Dao
interface CoinInfoDao {
	@Query("SELECT * FROM full_price_list ORDER BY fromSymbol")
	fun getPriceList(): LiveData<List<CoinInfoDbModel>>

	@Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
	fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinInfoDbModel>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertPriceList(priceList: List<CoinInfoDbModel>)
}