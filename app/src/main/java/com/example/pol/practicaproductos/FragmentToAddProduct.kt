package com.example.pol.practicaproductos


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pol.practicaproductos.R.drawable.notification_tile_bg
import kotlinx.android.synthetic.main.fragment_fragment_to_add_product.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentToAddProduct: Fragment() {

    var name = " "
    var stock = " "
    var description = " "

    interface OnAddButtonPressedListener {
        fun onAddButtonPressed(product: Product)
    }

    lateinit var listenerButton: OnAddButtonPressedListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_to_add_product, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addProductButton.setOnClickListener {

            setData()

            val fieldsFilled = areFieldsFilled()

            if (fieldsFilled) {
                val product1 = Product(name, stock.toInt(), notification_tile_bg, description)
                listenerButton.onAddButtonPressed(product1)
            }else {
                Toast.makeText(activity ,"Fill the fields propertly",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun areFieldsFilled():Boolean {
        return !(name.isEmpty() || stock.isEmpty() || description.isEmpty())
    }

    private fun setData() {
        name = productName.text.toString()
        stock = stockProduct.text.toString()
        description = descriptionProduct.text.toString()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        listenerButton = activity as OnAddButtonPressedListener
    }
}
