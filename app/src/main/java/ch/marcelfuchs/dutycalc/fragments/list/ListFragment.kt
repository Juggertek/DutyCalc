package ch.marcelfuchs.dutycalc.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ch.marcelfuchs.dutycalc.R
import ch.marcelfuchs.dutycalc.databinding.FragmentListBinding
import ch.marcelfuchs.dutycalc.model.DutyDay
import ch.marcelfuchs.dutycalc.viewmodel.AddViewModel

class ListFragment : Fragment() {

    private lateinit var mAdapter: ListAdapter

    private lateinit var mAddViewModel: AddViewModel

    private var mBinding: FragmentListBinding? = null

    private var mDutyDayList: List<DutyDay>? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = mBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // ViewModel, muss hier instanziert werden, da für DataBinding benötigt.
        mAddViewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        mAddViewModel.dutyDayList.observe(viewLifecycleOwner, { tour ->
            mAdapter.setData(tour)
        })

        // DataBinding, muss hier instanziert werden, weil nur die onCreateView Funktion eine View? zurückgibt.
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = mAddViewModel

        // Recyclerview
        mAdapter = ListAdapter()
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAddViewModel.dutyDayList.observe(this, Observer { toyEntries ->
            if (toyEntries.isNullOrEmpty()) {
                mAddViewModel.uiState.set(UIState.EMPTY)
            } else {
                mAddViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.dutyDayList = toyEntries
                mDutyDayList = toyEntries
            }
        })

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_ListFragment_to_addFragment)
        }
    }

    //Fragments outlive their views. Make sure you clean up any references to the binding class instance in the fragment's onDestroyView() method.
    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}