package com.example.noteapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.R
import com.example.noteapp.adapter.CarouselAdapter
import com.example.noteapp.databinding.FragmentSplashBinding
import com.example.noteapp.models.CarouselData
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using the view binding
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.carouselRecyclerView.setHasFixedSize(true)
        binding.carouselRecyclerView.
        layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
        val carouselSnapHelper = CarouselSnapHelper()
        carouselSnapHelper.attachToRecyclerView(binding.carouselRecyclerView)

        val carrouselData = mutableListOf<CarouselData>()
        carrouselData.add(CarouselData(
            imageUri = "https://images.pexels.com/photos/799443/pexels-photo-799443.jpeg?auto=compress&cs=tinysrgb&w=1200",
            title = "Welcome to Note",
            description= "Here"
        ))
        carrouselData.add(
            CarouselData(
                imageUri = "https://images.pexels.com/photos/31146550/pexels-photo-31146550/free-photo-of-aerial-view-of-serene-mountain-landscape-at-dusk.jpeg?auto=compress&cs=tinysrgb&w=1200",
                title = "Serene Mountain Landscape at Dusk",
                description = "An aerial view capturing the tranquility of mountains bathed in the soft glow of dusk."
            )
        )

        carrouselData.add(
            CarouselData(
                imageUri = "https://images.pexels.com/photos/6787956/pexels-photo-6787956.jpeg?auto=compress&cs=tinysrgb&w=1200",
                title = "Starry Night Sky Over Forest",
                description = "A mesmerizing view of a star-filled night sky casting a serene ambiance over a dense forest."
            )
        )

        carrouselData.add(
            CarouselData(
                imageUri = "https://images.pexels.com/photos/3747142/pexels-photo-3747142.jpeg?auto=compress&cs=tinysrgb&w=1200",
                title = "Calm Lake Reflecting Mountains",
                description = "A peaceful lake mirroring the surrounding mountains, creating a harmonious natural scene."
            )
        )

        val adapter = CarouselAdapter(carrouselData)
        binding.carouselRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}