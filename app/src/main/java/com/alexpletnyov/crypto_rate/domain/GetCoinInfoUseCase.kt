package com.alexpletnyov.crypto_rate.domain

class GetCoinInfoUseCase(private val repository: CoinRepository) {

	operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}