package com.alexpletnyov.crypto_rate.domain

class LoadDataUseCase(private val repository: CoinRepository) {

	operator fun invoke() = repository.loadData()
}