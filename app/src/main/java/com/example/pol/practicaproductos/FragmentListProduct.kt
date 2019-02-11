package com.example.pol.practicaproductos



import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.AdapterView
import android.widget.ListView
import android.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentListProduct : Fragment() {

    interface OnProductClickedListener {
        fun onProductClicked(product:Product)
    }

    var products:ArrayList<Product> = ArrayList()
    var list:ListView? = null
    lateinit var listenerList:OnProductClickedListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        var  view = inflater.inflate(R.layout.fragment_fragment_list_product, container, false)
        setHasOptionsMenu(true)
        return view
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater:MenuInflater){
        inflater.inflate(R.menu.menu, menu)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureList()
        setListener()
    }

    private fun setListener() {
        list?.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            listenerList.onProductClicked(products[position])
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        addProductstoList()
        listenerList = context as OnProductClickedListener

    }

    private fun addProductstoList() {
        products.add(Product("Product 1", 12, R.drawable.manzana, "Product 1 decription"))
        products.add(Product("Product 2", 20, R.drawable.durazno, "Product 2 description"))
        products.add(Product("Product 3", 50,R.drawable.platano,"Product 3 description"))
        products.add(Product("Product 4", 5,R.drawable.sandia , "Product 4 description"))
        products.add(Product("Product 5",80, R.drawable.sandia, "Product 5 description"))
    }

    fun configureList() {
        val adapter = ProductCustom(this.context, products)
        list = activity!!.findViewById(R.id.product_list)
        list?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
