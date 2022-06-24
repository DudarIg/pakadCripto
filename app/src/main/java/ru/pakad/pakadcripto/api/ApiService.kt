package ru.pakad.pakadcripto.api

import CointInfoListOfData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.pakad.pakadcripto.pojo.CoinPriceInfoRawData

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query("api_key") api_Key: String = API_KEY,
        @Query("limit") limit: Int = 50,
        @Query("tsym") tSym : String ="USD"
    ): Single<CointInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query("api_key") api_Key: String = API_KEY,
        @Query("fsyms") fSyms: String,
        @Query("tsyms") tSyms: String = "USD"
    ): Single<CoinPriceInfoRawData>

    companion object {
        private const val API_KEY = "1277c72af4a1a94164f3a0e4e68c7ab82067fd654f410a884e7e5ceeba391118"
    }
}