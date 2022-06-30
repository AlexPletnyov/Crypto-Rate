package com.alexpletnyov.crypto_rate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alexpletnyov.crypto_rate.R
import com.alexpletnyov.crypto_rate.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {

	private val viewModel by lazy {
		ViewModelProvider(this)[CoinViewModel::class.java]
	}

	private var _binding: FragmentCoinDetailBinding? = null
	private val binding: FragmentCoinDetailBinding
		get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val fromSymbol = getSymbol()
		viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner) {
			with(binding) {
				tvPrice.text = it.price
				tvMinPrice.text = it.lowDay
				tvMaxPrice.text = it.highDay
				tvLastMarket.text = it.lastMarket
				tvLastUpdateTime.text = it.lastUpdate
				Picasso.get().load(it.imageUrl).into(ivLogoCoin)
				tvSymbols.text = String.format(
					this@CoinDetailFragment.resources.getString(R.string.symbols_template),
					it.fromSymbol,
					it.toSymbol
				)
			}

		}
	}

	private fun getSymbol(): String {
		return requireArguments().getString(EXTRA_FROM_SYMBOL, "")
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

	companion object {

		private const val EXTRA_FROM_SYMBOL = "fSym"

		fun newInstance(fromSymbol: String): Fragment {
			return CoinDetailFragment().apply {
				arguments = Bundle().apply {
					putString(EXTRA_FROM_SYMBOL, fromSymbol)
				}
			}
		}
	}
}