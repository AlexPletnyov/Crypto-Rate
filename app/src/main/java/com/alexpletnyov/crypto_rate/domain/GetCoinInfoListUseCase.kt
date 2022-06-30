package com.alexpletnyov.crypto_rate.domain

class GetCoinInfoListUseCase(private val repository: CoinRepository) {

	operator fun invoke() = repository.getCoinInfoList()
}