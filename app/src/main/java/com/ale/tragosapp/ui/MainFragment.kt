package com.ale.tragosapp.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.tragosapp.R
import com.ale.tragosapp.data.DataSource
import com.ale.tragosapp.data.model.Drink
import com.ale.tragosapp.domain.RepoImpl
import com.ale.tragosapp.ui.viewmodel.MainViewModel
import com.ale.tragosapp.ui.viewmodel.VMFactory
import com.ale.tragosapp.utils.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(),MainAdapter.OnTragoClickListener {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupDefaultTragosList()
    }

    private fun setupDefaultTragosList(){
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_tragos.adapter = MainAdapter(requireContext(),result.data,this)
                    Log.i("MYTAG", result.data.toString())
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Log.e("MainFragment", "onRetrofitRequest: ${result.exception}")
                    Toast.makeText(requireContext(), "Ocurrió un error al traer los datos ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.fetchAlcoholicFilter.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_tragos.adapter = MainAdapter(requireContext(),result.data,this)

                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Log.e("MainFragment", "onRetrofitRequest: ${result.exception}")
                    Toast.makeText(requireContext(), "Ocurrió un error al traer los datos ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun setupSearchView(){
        searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setTrago(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {return false}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.filtroSinAlcohol -> {
                viewModel.setAlcoholicOrNotFilter("Non_Alcoholic")
                false
            }

            R.id.filtroConAlcohol -> {
                viewModel.setAlcoholicOrNotFilter("Alcoholic")
                false
            }
            else -> {
                false
            }
        }
    }

    override fun onTragoClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        Log.i("MIBEBIDA", drink.nombre)
        findNavController().navigate(R.id.tragosDetalleFragment,bundle)
    }

    private fun setupRecyclerView(){
        rv_tragos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }
}