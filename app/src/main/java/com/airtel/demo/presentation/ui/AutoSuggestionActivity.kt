package com.airtel.demo.presentation.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airtel.demo.R
import com.airtel.demo.data.network.NetworkUtility
import com.airtel.demo.presentation.di.components.AutoSuggestionComponents
import com.airtel.demo.presentation.di.components.DaggerAutoSuggestionComponents
import com.airtel.demo.presentation.viewmodel.AddressViewModel
import javax.inject.Inject

class AutoSuggestionActivity : AppCompatActivity() {

    private val CITY: String = "Gurgaon"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AddressViewModel
    private lateinit var adapter: AutosuggestionAdapter

    private lateinit var searchBox: EditText
    private lateinit var searchLoader: ProgressBar
    private lateinit var suggestionRecyclerView: RecyclerView
    private lateinit var noInternetScreen: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_suggestion)
        initViews()
        injectDependency()
        setAdapter()
        setData()
        setSearchListener()
    }


    private fun initViews() {
        searchBox = findViewById(R.id.search_edittext)
        searchLoader = findViewById(R.id.search_loader)
        suggestionRecyclerView = findViewById(R.id.auto_suggest_list)
        noInternetScreen = findViewById(R.id.no_internet_screen) as LinearLayout
    }

    private fun setAdapter() {
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        suggestionRecyclerView.setHasFixedSize(true)
        suggestionRecyclerView.layoutManager = mLayoutManager
        adapter = AutosuggestionAdapter(this)
        suggestionRecyclerView.adapter = adapter
    }

    private fun injectDependency() {

        val components: AutoSuggestionComponents = DaggerAutoSuggestionComponents.builder().build()

        components.injectAutoSuggestionActivity(this)
        viewModel =
                ViewModelProviders
                        .of(this, viewModelFactory)
                        .get(AddressViewModel::class.java)
    }


    private fun setData() {
        viewModel.addressSuggestions.observe(this, Observer { data ->
            adapter.setData(data.data?.addressList)
            adapter.notifyDataSetChanged()
        })

        viewModel.addressSuggestionError.observe(this, Observer {
            Toast.makeText(applicationContext, it.trim(), Toast.LENGTH_SHORT).show()
        })

        viewModel.loadingState.observe(this, Observer { isLoading ->
            if (isLoading) {
                showLoader()
            } else {
                hideLoader()
            }
        })
    }

    private fun setSearchListener() {

        searchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val isInternetAvailable = NetworkUtility.isNetworkAvailable(applicationContext)
                if (isInternetAvailable) {
                    showAutoSuggestionView()
                    if (s.isNullOrEmpty()) {
                        clearSuggestionsList()
                    } else {
                        viewModel.getAddressSuggestion(s.toString(), CITY)
                    }
                } else {
                    showNoInternetView()
                }
            }
        })
    }

    private fun showNoInternetView() {
        noInternetScreen.visibility = View.VISIBLE
        suggestionRecyclerView.visibility = View.GONE
    }

    private fun showAutoSuggestionView() {
        noInternetScreen.visibility = View.GONE
        suggestionRecyclerView.visibility = View.VISIBLE
    }

    private fun clearSuggestionsList() {
        adapter.setData(null)
        adapter.notifyDataSetChanged()
        hideLoader()
    }

    private fun showLoader() {
        searchLoader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        searchLoader.visibility = View.GONE
    }
}
