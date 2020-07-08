package com.ale.tragosapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.tragosapp.R
import com.ale.tragosapp.data.model.Drink
import com.ale.tragosapp.database.FavoritosCRUD
import com.ale.tragosapp.ui.FavoritosAdapter
import com.ale.tragosapp.ui.MainAdapter
import kotlinx.android.synthetic.main.fragment_favoritos.*


class FavoritosFragment : Fragment(),
    FavoritosAdapter.OnTragoClickListener,
    MainAdapter.OnTragoClickListener {



    var drinks:ArrayList<Drink>? = null
    var crud: FavoritosCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        crud = FavoritosCRUD(this.requireContext())
        drinks = crud?.getDrinks()

        Log.i("MISFAVRECIBIDOS", drinks.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        recycler_fav.adapter = MainAdapter(
            requireContext(),
            this.drinks!!,
            this
        )
    }



    override fun onTragoClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        Log.i("MIBEBIDA", drink.nombre)
        findNavController().navigate(R.id.tragosDetalleFragment,bundle)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false)



    }

    private fun setupRecyclerView() {
        recycler_fav.layoutManager = LinearLayoutManager(requireContext())
        recycler_fav.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )

    }

}