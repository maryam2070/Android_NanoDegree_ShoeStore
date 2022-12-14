package com.udacity.shoestore

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding:FragmentShoeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


         binding=DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_detail,container,false)
        //    return inflater.inflate(R.layout.fragment_shoe_detail, container, false)

        binding.lifecycleOwner = this

        binding.myShoe=Shoe()
        binding.saveBtn.setOnClickListener {
             val viewModel:ViewModelClass by activityViewModels()



             viewModel.addItem(getShoe()!!)

             findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
         }
         binding.cancelBtn.setOnClickListener {
             // pass fake parameters
             findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
         }



        //to stop back press
         requireActivity().onBackPressedDispatcher.addCallback(this) {

         }


         return binding.root
    }

    fun getShoe(): Shoe? {
        binding.myShoe!!.size=binding.size!!.toDouble()
        return binding.myShoe
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoeDetailFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}



