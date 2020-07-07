package com.ale.tragosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ale.tragosapp.R
import com.ale.tragosapp.data.model.Drink
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

class TragosDetalleFragment : Fragment() {

    private lateinit var drink: Drink




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
        return inflater.inflate(R.layout.fragment_tragos_detalle, container, false)
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
    }


}