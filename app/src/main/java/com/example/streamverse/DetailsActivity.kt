package com.example.streamverse

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class DetailsActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragment = DetailsFragment()
            fragment.arguments = intent.extras
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit()
        }
    }

    companion object {
        const val ARTWORK_LIST = "ArtworkList"
        const val START_POSITION = "StartPosition"
    }
}
