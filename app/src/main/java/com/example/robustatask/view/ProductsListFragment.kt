package com.example.robustatask.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.robustatask.R
import com.example.robustatask.model.ProductItem
import com.example.robustatask.network.HandleResponseBody
import com.example.robustatask.view_model.SearchViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A fragment representing a list of Items.
 */
class ProductsListFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private var columnCount = 1
    private  var products : ArrayList<ProductItem> =  ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        viewModel = ViewModelProvider(this).get(getViewModel())

        viewModel.getList()




    }
    private fun updateUI(productList: List<ProductItem>) {
        products.addAll(productList)

    }
  private   fun getViewModel() = SearchViewModel::class.java


    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products_list_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        val btnUp = view.findViewById<FloatingActionButton>(R.id.btn_up)
        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyProductItemRecyclerViewAdapter(products)
                viewModel.productList.observe(viewLifecycleOwner, Observer {
                    when (it) {
                        is HandleResponseBody.Success -> {
                            Log.d("My Tag"  , "Data is Loaded....")
                            updateUI(it.value.productList )
                            (adapter as RecyclerView.Adapter).notifyDataSetChanged()
                        }
                        is HandleResponseBody.Loading -> {
                            Log.d("My Tag"  , "Data is loading....")
                        }
                    }
                })
            }
        }

        btnUp.setOnClickListener{
            recyclerView.smoothScrollToPosition(0)
            btnUp.visibility = View.GONE
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProductsListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}