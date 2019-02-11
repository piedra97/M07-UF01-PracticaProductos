package com.example.pol.practicaproductos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ProductCustom(var context: Context?, items:ArrayList<Product>): BaseAdapter(){
    var items:ArrayList<Product>? = null

    init {
        this.items = items
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder?
        var vista:View? = convertView

        if(vista == null){

            vista = LayoutInflater.from(context).inflate(R.layout.template,null)
            holder = ViewHolder(vista)
            vista.tag = holder

        }else {
            holder = vista.tag as? ViewHolder
        }


        holder?.nameProduct?.text = items!![position].name
        holder?.image?.setImageResource(items!![position].img)
        holder?.stockView?.text = items!![position].stock.toString()
        holder?.deleteTextView?.setOnClickListener{
            items!!.removeAt(position)
            this.notifyDataSetChanged()
        }
        return vista!!
    }

    override fun getItem(position: Int): Any {
        return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items?.count()!!

    }

    private class ViewHolder(myView:View){
        var nameProduct: TextView? = null
        var image: ImageView? = null
        var stockView:TextView? = null
        var deleteTextView:TextView? = null

        init {
            nameProduct = myView.findViewById(R.id.NameProduct)
            image = myView.findViewById(R.id.imageProduct)
            stockView = myView.findViewById(R.id.Stock)
            deleteTextView = myView.findViewById(R.id.deleteText)
        }
    }

}