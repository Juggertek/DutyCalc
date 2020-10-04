package ch.marcelfuchs.dutycalc.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ch.marcelfuchs.dutycalc.R
import ch.marcelfuchs.dutycalc.databinding.FragmentAddBinding
import ch.marcelfuchs.dutycalc.viewmodel.TourViewModel

class AddFragment : Fragment() {

    private lateinit var mTourViewModel: TourViewModel

    private var mBinding: FragmentAddBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TourViewModel, muss hier instanziert werden, da für DataBinding benötigt.
        mTourViewModel = ViewModelProvider(this).get(TourViewModel::class.java)


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }
}