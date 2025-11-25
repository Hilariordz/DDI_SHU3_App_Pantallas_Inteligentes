package com.example.streamverse

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var artworkList: ArrayList<Artwork>
    private var currentPosition: Int = 0

    private val handler = Handler(Looper.getMainLooper())
    private val slideShowRunnable = object : Runnable {
        override fun run() {
            showNextImage()
            handler.postDelayed(this, 60000) // 60 seconds
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.details_image)

        arguments?.let {
            artworkList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelableArrayList(DetailsActivity.ARTWORK_LIST, Artwork::class.java)
            } else {
                it.getParcelableArrayList(DetailsActivity.ARTWORK_LIST)
            } ?: ArrayList()

            currentPosition = it.getInt(DetailsActivity.START_POSITION, 0)
        }

        updateImage()
    }

    private fun updateImage() {
        if (artworkList.isNotEmpty()) {
            val artwork = artworkList[currentPosition]
            Glide.with(this)
                .load(artwork.imageUrl)
                .into(imageView)
        }
    }

    private fun showNextImage() {
        if (artworkList.isNotEmpty()) {
            currentPosition = (currentPosition + 1) % artworkList.size
            updateImage()
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(slideShowRunnable, 60000) // Start slideshow with new duration
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(slideShowRunnable) // Stop slideshow
    }
}
