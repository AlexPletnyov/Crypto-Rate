package com.alexpletnyov.crypto_rate.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alexpletnyov.crypto_rate.data.repository.CoinRepositoryImpl
import com.alexpletnyov.crypto_rate.domain.GetCoinInfoListUseCase
import com.alexpletnyov.crypto_rate.domain.GetCoinInfoUseCase
import com.alexpletnyov.crypto_rate.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

	private val repository = CoinRepositoryImpl(application)

	private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
	private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
	private val loadDataUseCase = LoadDataUseCase(repository)

	val coinInfoList = getCoinInfoListUseCase()

	fun getDetailInfo(fromSymbol: String) = getCoinInfoUseCase(fromSymbol)

	init {
		viewModelScope.launch {
			loadDataUseCase()
		}
	}
}