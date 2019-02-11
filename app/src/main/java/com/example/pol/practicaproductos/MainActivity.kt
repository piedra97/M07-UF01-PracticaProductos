package com.example.pol.practicaproductos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View


class MainActivity : AppCompatActivity(), FragmentListProduct.OnProductClickedListener, FragmentToAddProduct.OnAddButtonPressedListener {

    private val fragmentListProduct = FragmentListProduct()
    private val fragmentManager = supportFragmentManager

    override fun onProductClicked(product: Product) {
        val tabletFragment:FragmentDetails? = supportFragmentManager.findFragmentById(R.id.details_fragment) as? FragmentDetails
        val isATablet= (tabletFragment != null)
        val fragmentDetails = FragmentDetails.newInstance(product)
        if(isATablet) {
            supportFragmentManager.beginTransaction().
                replace(R.id.details_fragment,fragmentDetails).
                    commit()
        }else {
            supportFragmentManager.beginTransaction().replace(R.id.main_container, fragmentDetails).addToBackStack(null)
                .commit()
        }

    }

    override fun onAddButtonPressed(product: Product) {

        if (fragmentManager.backStackEntryCount > 0) {
            fragmentListProduct.products.add(product)
            fragmentManager.popBackStack()
            fragmentManager.beginTransaction().
                replace(R.id.main_container, fragmentListProduct).
                commit()


        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById<View>(R.id.main_container) != null) {
            phoneFragment()

        }else {
            tabletFragment()
        }
    }

    private fun tabletFragment() {
        val fragmentListTablet = FragmentListProduct()
        val fragmentDetailsTablet = FragmentDetails()
        supportFragmentManager.beginTransaction().
            replace(R.id.list_tablet,fragmentListTablet).
            commit()
        supportFragmentManager.beginTransaction().
            replace(R.id.details_fragment,fragmentDetailsTablet).
            commit()
    }

    private fun phoneFragment() {
        val fragmentList = FragmentListProduct()
        supportFragmentManager.beginTransaction().
            replace(R.id.main_container, fragmentList).
            commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.addButton -> {
                val addProductFragment = FragmentToAddProduct()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, addProductFragment)
                    .addToBackStack(null).commit()
                return true

            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}


