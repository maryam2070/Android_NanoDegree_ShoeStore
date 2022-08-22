package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.databinding.ShoeItemBinding



// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListingFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val binding :FragmentShoeListingBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_listing,container,false)
      //  return inflater.inflate(R.layout.fragment_shoe_listing, container, false)
        //setHasOptionsMenu(true)


        binding.btn.setOnClickListener {
            // fake parameters
            findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment())
        }

       val viewModel:ViewModelClass by activityViewModels()
        viewModel.list.observe(viewLifecycleOwner) {
            for (i in it) {

                val view =
                    LayoutInflater.from(requireContext())
                        .inflate(R.layout.shoe_item,null)



                val itemBinding:ShoeItemBinding=DataBindingUtil.setContentView(requireActivity(),R.layout.shoe_item)
                itemBinding.itemCompany.setText(i.company)
                itemBinding.itemDescription.setText(i.description)
                itemBinding.itemSize.setText(i.size.toString())
                itemBinding.itemName.setText(i.name)

                binding.container.addView(view)
            }

        }
        /*var args=ShoeListingFragmentArgs.fromBundle(requireArguments())
        if(args.name!=""&& args.add==true ) {
            //then actual parameters passed and we must store it

        }*/
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_activity_menu, menu)
    }


    // not working for me i can't find the reson so make anew distnition in nav_graph
   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println("HEEEEEEEEERRRRRREeee")

        val navController: NavController =
            Navigation.findNavController(requireActivity(), R.id.myNavHostFragment)
           return (NavigationUI.onNavDestinationSelected(item, navController)|| super.onOptionsItemSelected(item))
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        requireView().findNavController()
            .navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToLoginFragment())
        return true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoeListingFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoeListingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}