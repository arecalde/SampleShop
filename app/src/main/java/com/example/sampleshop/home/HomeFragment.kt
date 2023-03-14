package com.example.sampleshop.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sampleshop.R
import com.example.sampleshop.databinding.HomeFragmentBinding
import com.example.sampleshop.databinding.OfferItemBinding
import com.example.sampleshop.helpers.ItemAdapter
import com.example.sampleshop.model.OfferItem

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HomeFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.listOfOffers.observe(viewLifecycleOwner) {
            binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 2)
            binding.movieRecyclerView.adapter =
                ItemAdapter<OfferItem, OfferItemBinding>(it, viewLifecycleOwner, R.layout.offer_item) {
                    offerItem, offerItemBinding ->
                    offerItemBinding.offer = offerItem
                }

            it.forEach { offerItem ->
                offerItem.launchDetails.observeEvent(viewLifecycleOwner) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(offerItem.id.orEmpty())
                    findNavController().navigate(action)
                }

                context?.let { fragmentContext ->
                    offerItem.changeDrawable(fragmentContext)
                }
            }
        }

        return binding.root
    }
}
