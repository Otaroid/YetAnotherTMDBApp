package otaroid.yetanothertmdbapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import otaroid.yetanothertmdbapp.R
import otaroid.yetanothertmdbapp.presentation.popular_tv_shows.PopularShowsViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}