package ru.pakad.pakadcripto.adapters

import CoinInfo
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.pakad.pakadcripto.R
import ru.pakad.pakadcripto.pojo.CoinPriceInfo

class CoinInfoAdapter(private val context: Context): RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoToList : List<CoinPriceInfo> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var funListClick: ((CoinPriceInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoToList.get(position)
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val updateTemplate = context.resources.getString(R.string.update_template)
        with(holder) {
            tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol,coin.toSymbol)
            tvPrice.text = coin.price
            tvLastUpdate.text = String.format(updateTemplate, coin.getFormattedTime())
            Picasso.get().load(coin.getFullImageUrl()).into(ivLogoCoin)
        }
        holder.itemView.setOnClickListener {
            funListClick?.invoke(coin)
        }
    }

    override fun getItemCount() = coinInfoToList.size


    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin: ImageView = itemView.findViewById(R.id.ivLogoCoin)
        val tvSymbols: TextView = itemView.findViewById(R.id.tvSymbols)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvLastUpdate: TextView = itemView.findViewById(R.id.tvLastUpdate)

    }
}