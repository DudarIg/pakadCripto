package ru.pakad.pakadcripto

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.pakad.pakadcripto.api.ApiFactory
import ru.pakad.pakadcripto.database.AppDatabase
import ru.pakad.pakadcripto.pojo.CoinPriceInfo
import ru.pakad.pakadcripto.pojo.CoinPriceInfoRawData
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    init {
        loadData()
    }

    // Получение списка криптовалют
    val priceList = db.coinPriceInfoDao().getPriceList()

    // Получение детальной информации о конкретной криптовалюте
    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    // Получение данных с сайта
    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .map { it.data.map { it.coinInfo.name }.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // Запись в БД
                db.coinPriceInfoDao().insertPriceList(it)
                //   Log.d("QQQ","Success: $it")
            }, {
                Log.d("QQQ", "Ошибка: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    // Json переделываем в список
    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}