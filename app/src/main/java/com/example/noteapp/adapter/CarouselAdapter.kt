package com.example.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.noteapp.databinding.MaskableFrameLayoutBinding
import com.example.noteapp.databinding.SplashItemBinding
import com.example.noteapp.models.CarouselData

class CarouselAdapter(private val carouselDataList: MutableList<CarouselData>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(private val binding: MaskableFrameLayoutBinding): RecyclerView.ViewHolder(binding
        .root) {
        fun bind(data: CarouselData) {
            val includeSplashItemBinding = SplashItemBinding.bind(binding.root)
            val imageView = includeSplashItemBinding.carouselImage
            Glide.
            with(binding.root).
            load(data.imageUri).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView)
            includeSplashItemBinding.carouselTitle.text = data.title
            includeSplashItemBinding.carouselDescription.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(
            MaskableFrameLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(carouselDataList[position])
    }


}