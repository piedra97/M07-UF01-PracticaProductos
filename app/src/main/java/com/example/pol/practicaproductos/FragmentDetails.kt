package com.example.pol.practicaproductos


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fragment_details.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentDetails : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_details, container, false)
    }

    override fun onStart() {
        super.onStart()

        showDetails()
    }

    private fun showDetails() {
        val item = arguments!!.getParcelable<Product>("product")

        imageDetail.setImageResource(item.img)
        nameDetails.text = item.name
        stockDetails.text = item.stock.toString()
        descDetails.text = item.description
    }

    companion object {
        fun newInstance(product: Product): FragmentDetails{
            val fragmentDetails = FragmentDetails()
            val args = Bundle()

            args.putParcelable("product", product)
            fragmentDetails.arguments = args

            return fragmentDetails
        }
    }
}




