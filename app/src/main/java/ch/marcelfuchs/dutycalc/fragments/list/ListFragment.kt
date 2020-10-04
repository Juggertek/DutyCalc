package ch.marcelfuchs.dutycalc.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ch.marcelfuchs.dutycalc.R
import ch.marcelfuchs.dutycalc.databinding.ListFragmentBinding
import ch.marcelfuchs.dutycalc.viewmodel.TourViewModel
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment() {

    private lateinit var viewModel: TourViewModel

    private var mBinding: ListFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = mBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Recyclerview
        val adapter = ListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // DutyDayViewModel, muss hier instanziert werden, da für DataBinding benötigt.
        viewModel = ViewModelProvider(this).get(TourViewModel::class.java)

        // DataBinding, muss hier instanziert werden, weil nur die onCreateView Funktion eine View? zurückgibt.
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    //Fragments outlive their views. Make sure you clean up any references to the binding class instance in the fragment's onDestroyView() method.
    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}