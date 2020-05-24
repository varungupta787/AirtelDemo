package com.airtel.demo.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airtel.demo.R
import com.airtel.demo.domain.models.Address


class AutosuggestionAdapter(var mContext: Context) : RecyclerView.Adapter<AutosuggestionAdapter.AutoSuggestionViewHolder>() {

    private var addressList: List<Address?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoSuggestionViewHolder {
        val mInflater = LayoutInflater.from(mContext)
        val view = mInflater.inflate(R.layout.suggestion_item, parent, false)
        return AutoSuggestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: AutoSuggestionViewHolder, position: Int) {
        val address = addressList?.get(position)
        holder.address.setText(address?.addressString ?: "")
    }


    override fun getItemCount(): Int {
        return addressList?.size ?: 0
    }

    fun setData(addressList: ArrayList<Address?>?) {
        this.addressList = addressList
    }

    inner class AutoSuggestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var address: TextView

        init {
            address = view.findViewById(R.id.address) as TextView
        }
    }

}
