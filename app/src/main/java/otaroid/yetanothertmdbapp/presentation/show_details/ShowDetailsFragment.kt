package otaroid.yetanothertmdbapp.presentation.show_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import otaroid.yetanothertmdbapp.R

class ShowDetailsFragment : Fragment(R.layout.fragment_show_details) {



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShowDetailsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}