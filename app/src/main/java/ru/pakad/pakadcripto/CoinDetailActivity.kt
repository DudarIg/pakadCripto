package ru.pakad.pakadcripto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import ru.pakad.pakadcripto.databinding.ActivityCoinDetailBinding

class CoinDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityCoinDetailBinding
    private val viewModel by viewModels<CoinViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra("EXTRA_SYMBOL")) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra("EXTRA_SYMBOL")

        viewModel.getDetailInfo(fromSymbol!!).observe(this, Observer {
            with(binding) {
                tvPrice.text = it.price
                tvMinPrice.text = it.lOWDAY
                tvMaxPrice.text = it.hIGHDAY
                tvLastMarket.text = it.lASTMARKET
                tvLastUpdate.text = it.getFormattedTime()

                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
            }
        })
    }

    companion object {
        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra("EXTRA_SYMBOL", fromSymbol)
            return intent
        }
    }
}