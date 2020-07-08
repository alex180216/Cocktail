package com.ale.tragosapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ale.tragosapp.R
import com.ale.tragosapp.data.model.Drink
import com.ale.tragosapp.database.FavoritosCRUD
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_favoritos_detalle.*
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*
import kotlinx.android.synthetic.main.fragment_tragos_detalle.img_detalle
import kotlinx.android.synthetic.main.fragment_tragos_detalle.instrucciones
import kotlinx.android.synthetic.main.fragment_tragos_detalle.nombre_bebida_detalle

class FavoritosDetalleFragment : Fragment() {

    private lateinit var drink: Drink

    var crud: FavoritosCRUD? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            drink = it.getParcelable<Drink>("drink")!!
            Log.i("RECIBIENDO", drink.descripcion)
            //nombre_bebida_detalle?.setText(drink.nombre)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favoritos_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nombre_bebida_detalle.setText(drink.nombre)
        Picasso.with(context).load(drink.imagen).into(img_detalle);
        if(drink.descripcion != ""){
            instrucciones.setText(drink.descripcion)
        }else{
            instrucciones.setText("No hay instrucciones para esta bebida")
        }

        crud = FavoritosCRUD(view.context)

        btn_delete.setOnClickListener {
            Toast.makeText(view.context, "Borrado de Favoritos", Toast.LENGTH_SHORT).show()

            crud?.deleteDrink(drink)

            Log.i("MIFAVGUARDADO", crud!!.getDrinks().toString())

        }


    }



}