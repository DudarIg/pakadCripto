package ru.pakad.pakadcripto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import ru.pakad.pakadcripto.adapters.CoinInfoAdapter

import ru.pakad.pakadcripto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<CoinViewModel>()
    private val adapter = CoinInfoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCointPriceList.adapter = adapter

        viewModel.priceList.observe(this, Observer {
           adapter.coinInfoToList = it
        })

        adapter.funListClick = {
                Log.d("QQQ", it.fromSymbol)
        }
    }

}
