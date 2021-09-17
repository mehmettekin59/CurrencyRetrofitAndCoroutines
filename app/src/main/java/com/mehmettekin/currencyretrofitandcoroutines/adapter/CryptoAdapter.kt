package com.mehmettekin.currencyretrofitandcoroutines.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehmettekin.currencyretrofitandcoroutines.databinding.RecyclerviewRowBinding
import com.mehmettekin.currencyretrofitandcoroutines.model.CryptoModel

class CryptoAdapter(val cryptoList: ArrayList<CryptoModel>, private val listener: Listener): RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    interface Listener{ fun onItemClicked(cryptoModel: CryptoModel)  }

    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    class CryptoHolder(val binding: RecyclerviewRowBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(cryptoModel: CryptoModel,colors: Array<String>, position: Int,listener: Listener){

            itemView.setOnClickListener {
                listener.onItemClicked(cryptoModel)
            }

            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            binding.nameText.text = cryptoModel.currency
            binding.priceText.text = cryptoModel.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
       val binding = RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }
    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position,listener)
    }
    override fun getItemCount(): Int {
        return cryptoList.count()
    }
}