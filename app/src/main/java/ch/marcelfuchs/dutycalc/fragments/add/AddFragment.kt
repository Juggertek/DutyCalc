package ch.marcelfuchs.dutycalc.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ch.marcelfuchs.dutycalc.R
import ch.marcelfuchs.dutycalc.databinding.FragmentAddBinding
import ch.marcelfuchs.dutycalc.viewmodel.AddViewModel

class AddFragment : Fragment() {

    private lateinit var mViewModel: AddViewModel

    private var mBinding: FragmentAddBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TourViewModel, muss hier instanziert werden, da für DataBinding benötigt.
        mViewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        // DataBinding, muss hier instanziert werden, weil nur die onCreateView Funktion eine View? zurückgibt.
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = mViewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnDayDec.setOnClickListener { mViewModel.decreaseDay() }
        binding.btnDayInc.setOnClickListener { mViewModel.increaseDay() }

        binding.floatingActionButton.setOnClickListener { sendVariables() }
    }

    //Fragments outlive their views. Make sure you clean up any references to the binding class instance in the fragment's onDestroyView() method.
    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    /*
    * Da das ViewModel keinen Bezug zur View haben darf, beziehe ich die Strings für die Variablen
    * hier im Fragment aus den EditText Boxen.
    * Das Datum muss übrigens nicht aus der TextView übernommen werden, dieses besteht ja bereits
    * als newDutyDay Variable im ViewModel und kann später einfach von dort übernommen werden.
    * */

    fun sendVariables() {
        val hasStby = binding.cbHasStby.isChecked
        val stbyStart = binding.etStbyStart.text.toString()
        val stbyEnd = binding.etStbyEnd.text.toString()
        val show = binding.etShow.text.toString()
        val dutyClosing = binding.etDutyClosing.text.toString()

        // ...und übergebe diese per Funktionsaufruf an das ViewModel.
        mViewModel.insertDataToDatabase(hasStby, stbyStart, stbyEnd, show, dutyClosing)
    }
}