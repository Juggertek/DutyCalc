package ch.marcelfuchs.dutycalc.fragments.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ch.marcelfuchs.dutycalc.R
import ch.marcelfuchs.dutycalc.databinding.FragmentAddBinding
import ch.marcelfuchs.dutycalc.model.DutyDay
import ch.marcelfuchs.dutycalc.model.Tour
import ch.marcelfuchs.dutycalc.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import java.sql.Date
import java.sql.Time

class AddFragment : Fragment() {

    private lateinit var mViewModel: ViewModel

    private var mBinding: FragmentAddBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TourViewModel, muss hier instanziert werden, da für DataBinding benötigt.
        mViewModel = ViewModelProvider(this).get(ViewModel::class.java)

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

        binding.btnAddDutyDay.setOnClickListener { insertDataToDatabase() }
    }

    //Fragments outlive their views. Make sure you clean up any references to the binding class instance in the fragment's onDestroyView() method.
    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    private fun insertDataToDatabase() {
        val date = Date.valueOf(tvDate.text.toString())
        val standByStart = Time.valueOf(etStbyStart.text.toString())
        val standByEnd = Time.valueOf(etStbyStart.text.toString())
        val show = Time.valueOf(etShow.text.toString())
        val closingTime = Time.valueOf(etDutyClosing.text.toString())

//        try {
//            if (inputCheck(firstName, lastName, age, dob)) {
//                // Create User Object
        val dutyDay = DutyDay(
            date,
            standByStart,
            standByEnd,
            show,
            closingTime
        )

        val tour= Tour(dutyDay)

//                    firstName,
//                    lastName,
//                    age.toIntOrNull(),
//                    if (dob.isEmpty()) null else Date.valueOf(dob)
//                )
        // Add Data to Database
        mViewModel.addTour(tour)
//                Toast.makeText(
//                    requireContext(), "Successfully added!", Toast.LENGTH_LONG
//                ).show()
//                // Navigate Back
//                findNavController().navigate(R.id.action_addFragment_to_listFragment)
//            } else {
//                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
//                    .show()
//            }
//        } catch (e: IllegalArgumentException) {
//            Toast.makeText(requireContext(), "Please use format: YYYY-MM-DD", Toast.LENGTH_LONG)
//                .show()
//        }
    }
}