package com.zerogdev.easyshorturl.kotlin.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.zerogdev.easyshorturl.kotlin.R
import com.zerogdev.easyshorturl.kotlin.databinding.FragmentCoinListBinding
import com.zerogdev.easyshorturl.kotlin.domain.model.NetworkUnavailable
import com.zerogdev.easyshorturl.kotlin.presentation.CoinsSharedViewModel
import com.zerogdev.easyshorturl.kotlin.presentation.CoinsSharedViewModelFactory
import com.zerogdev.easyshorturl.kotlin.presentation.SharedViewEffects
import com.zerogdev.easyshorturl.kotlin.presentation.list.adapter.CoinAdapter
import kotlinx.coroutines.flow.collect

class ListFragment: Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentCoinListBinding? = null

    private val viewModel: ListFragmentViewModel by viewModels { ListFragmentViewModelFactory }
    private val shareViewModel: CoinsSharedViewModel by activityViewModels { CoinsSharedViewModelFactory }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        requestCoinList()
    }

    private fun setupUI() {
        setupToolbar()
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewStateUpdates(adapter)
        subscribeToViewEffects()
        subscribeToSharedViewEffects()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "easy"
        }
    }


    private fun createAdapter(): CoinAdapter {
        return CoinAdapter {
            coinId, name -> navigateToHistory(coinId, name)
        }
    }

    private fun navigateToHistory(coinId: String, name: String) {

    }

    private fun setupRecyclerView(coinAdapter: CoinAdapter) {
        binding.coinsRecyclerView.apply {
            adapter = coinAdapter

            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun observeViewStateUpdates(adapter: CoinAdapter) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                updateUi(it, adapter)
            }
        }
    }

    private fun updateUi(viewState: ListFragmentViewState, adapter: CoinAdapter) {
        adapter.submitList(viewState.coins)
        binding.loadingProgressBar.isVisible = viewState.loading
    }

    private fun subscribeToViewEffects() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.viewEffects.collect {
                when (it) {
                    is ListFragmentViewEffects.Failure -> handleFailure(it.cause)
                }
            }
        }
    }

    private fun subscribeToSharedViewEffects() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shareViewModel.sharedViewEffects.collect {
                when(it) {
                    is SharedViewEffects.PriceVariation -> notifyOfPriceVariation(it.variation)
                }
            }
        }

    }

    private fun notifyOfPriceVariation(variation: Int) {
        val message = getString(R.string.price_variation_message, variation)
        showSnackbar(message)
    }


    private fun handleFailure(cause: Throwable) {
        binding.loadingProgressBar.isInvisible = true
        val message = when (cause) {
            is NetworkUnavailable -> getString(R.string.network_unavailable_error_message)
            else -> getString(R.string.generic_error_message)
        }

        showSnackbar(message)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun requestCoinList() {
        viewModel.requestCoinList()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}