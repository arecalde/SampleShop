package com.example.sampleshop.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleshop.R
import com.example.sampleshop.databinding.HomeFragmentBinding
import com.example.sampleshop.databinding.OfferItemBinding
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
            binding.movieRecyclerView.adapter = OfferItemAdapter(it, viewLifecycleOwner)
        }

        return binding.root
    }
}

class MyViewHolder(private val binding: OfferItemBinding, private val lifecycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: OfferItem) {
        binding.lifecycleOwner = lifecycleOwner
        binding.offer = item
        binding.executePendingBindings()
    }
}

class OfferItemAdapter(private val items: List<OfferItem>, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemBinding = DataBindingUtil.inflate<OfferItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.offer_item,
            parent,
            false
        )
        return MyViewHolder(itemBinding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}

