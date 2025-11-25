package com.example.streamverse

import android.content.Intent
import android.os.Bundle
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.VerticalGridPresenter

class MainFragment : VerticalGridSupportFragment() {

    private val artworkList = listOf(
        Artwork("The Starry Night", "Vincent van Gogh", "https://www.vincentvangogh.org/images/paintings/the-starry-night.jpg"),
        Artwork("Mona Lisa", "Leonardo da Vinci", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg/687px-Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg"),
        Artwork("The Persistence of Memory", "Salvador Dalí", "https://upload.wikimedia.org/wikipedia/en/d/dd/The_Persistence_of_Memory.jpg"),
        Artwork("The Scream", "Edvard Munch", "https://upload.wikimedia.org/wikipedia/commons/c/c5/Edvard_Munch%2C_1893%2C_The_Scream%2C_oil%2C_tempera_and_pastel_on_cardboard%2C_91_x_73_cm%2C_National_Gallery_of_Norway.jpg"),
        Artwork("Girl with a Pearl Earring", "Johannes Vermeer", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/1665_Girl_with_a_Pearl_Earring.jpg/800px-1665_Girl_with_a_Pearl_Earring.jpg"),
        Artwork("The Night Watch", "Rembrandt van Rijn", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/The_Night_Watch_-_Rembrandt_van_Rijn.jpg/1280px-The_Night_Watch_-_Rembrandt_van_Rijn.jpg"),
        Artwork("American Gothic", "Grant Wood", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Grant_Wood_-_American_Gothic_-_Google_Art_Project.jpg/800px-Grant_Wood_-_American_Gothic_-_Google_Art_Project.jpg"),
        Artwork("The Kiss", "Gustav Klimt", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/The_Kiss_-_Gustav_Klimt_-_Google_Cultural_Institute.jpg/800px-The_Kiss_-_Gustav_Klimt_-_Google_Cultural_Institute.jpg"),
        Artwork("Las Meninas", "Diego Velázquez", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Las_Meninas%2C_by_Diego_Vel%C3%A1zquez%2C_from_Prado_in_Google_Earth.jpg/1280px-Las_Meninas%2C_by_Diego_Vel%C3%A1zquez%2C_from_Prado_in_Google_Earth.jpg"),
        Artwork("A Sunday Afternoon on the Island of La Grande Jatte", "Georges Seurat", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/A_Sunday_on_La_Grande_Jatte%2C_Georges_Seurat%2C_1884.jpg/1280px-A_Sunday_on_La_Grande_Jatte%2C_Georges_Seurat%2C_1884.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Digital Art Frame"
        setupGrid()
        setupEventListeners()
        requireActivity().startService(Intent(requireActivity(), MusicService::class.java))
    }

    private fun setupGrid() {
        val gridPresenter = VerticalGridPresenter()
        gridPresenter.numberOfColumns = 5 // Adjust number of columns as needed
        setGridPresenter(gridPresenter)

        val cardPresenter = CardPresenter()
        val gridAdapter = ArrayObjectAdapter(cardPresenter)
        gridAdapter.addAll(0, artworkList)
        adapter = gridAdapter
    }

    private fun setupEventListeners() {
        onItemViewClickedListener = OnItemViewClickedListener { _, item, _, _ ->
            if (item is Artwork) {
                val intent = Intent(requireActivity(), DetailsActivity::class.java)
                val bundle = Bundle().apply {
                    putParcelableArrayList(DetailsActivity.ARTWORK_LIST, ArrayList(artworkList))
                    putInt(DetailsActivity.START_POSITION, artworkList.indexOf(item))
                }
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}
