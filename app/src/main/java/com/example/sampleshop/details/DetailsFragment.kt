package com.example.sampleshop.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.sampleshop.R
import com.example.sampleshop.databinding.DetailsFragmentBinding
import com.example.sampleshop.databinding.OfferDetailBinding
import com.example.sampleshop.helpers.ItemAdapter
import com.example.sampleshop.model.OfferDetail

class DetailsFragment : Fragment() {


    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModelFactory(
            requireActivity().application,
            args.id
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DetailsFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.offerDetails.observe(viewLifecycleOwner) {
            binding.detailsRecyclerView.adapter = ItemAdapter<OfferDetail, OfferDetailBinding>(
                it,
                viewLifecycleOwner,
                R.layout.offer_detail
            ) { offerDetail, offerDetailBinding ->
                offerDetailBinding.offerDetail = offerDetail
            }
        }

        return binding.root
    }
}
