package ru.pakad.pakadcripto.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import ru.pakad.pakadcripto.api.ApiFactory.BASE_IMAGE_URL
import ru.pakad.pakadcripto.utils.convertTimestampToTime

data class CoinPriceInfoRawData(

    @SerializedName("RAW") val coinPriceInfoJsonObject : JsonObject? = null
)

@Entity(tableName = "full_price_list")
data class CoinPriceInfo (
    @PrimaryKey
    @SerializedName("FROMSYMBOL") val fromSymbol : String,

    @SerializedName("TOSYMBOL") val toSymbol : String,
    @SerializedName("MARKET") val market : String,
    @SerializedName("PRICE") val price : String,
    @SerializedName("LASTUPDATE") val lastUpdate : String,
    @SerializedName("LASTVOLUME") val lastVolume : String,
    @SerializedName("LASTVOLUMETO") val lASTVOLUMETO : String,
    @SerializedName("LASTTRADEID") val lASTTRADEID : String,  /// int
    @SerializedName("VOLUMEDAY") val vOLUMEDAY : String,
    @SerializedName("VOLUMEDAYTO") val vOLUMEDAYTO : String,
    @SerializedName("VOLUME24HOUR") val vOLUME24HOUR : String,
    @SerializedName("VOLUME24HOURTO") val vOLUME24HOURTO : String,
    @SerializedName("OPENDAY") val oPENDAY : String,
    @SerializedName("HIGHDAY") val hIGHDAY : String,
    @SerializedName("LOWDAY") val lOWDAY : String,
    @SerializedName("OPEN24HOUR") val oPEN24HOUR : String,
    @SerializedName("HIGH24HOUR") val hIGH24HOUR : String,
    @SerializedName("LOW24HOUR") val lOW24HOUR : String,
    @SerializedName("LASTMARKET") val lASTMARKET : String,
    @SerializedName("VOLUMEHOUR") val vOLUMEHOUR : String,
    @SerializedName("VOLUMEHOURTO") val vOLUMEHOURTO : String,
    @SerializedName("OPENHOUR") val oPENHOUR : String,
    @SerializedName("HIGHHOUR") val hIGHHOUR : String,
    @SerializedName("LOWHOUR") val lOWHOUR : String,
    @SerializedName("TOPTIERVOLUME24HOUR") val tOPTIERVOLUME24HOUR : String,
    @SerializedName("TOPTIERVOLUME24HOURTO") val tOPTIERVOLUME24HOURTO : String,
    @SerializedName("CHANGE24HOUR") val cHANGE24HOUR : String,
    @SerializedName("CHANGEPCT24HOUR") val cHANGEPCT24HOUR : Double,
    @SerializedName("CHANGEDAY") val cHANGEDAY : String,
    @SerializedName("CHANGEPCTDAY") val cHANGEPCTDAY : Double,
    @SerializedName("CHANGEHOUR") val cHANGEHOUR : String,
    @SerializedName("CHANGEPCTHOUR") val cHANGEPCTHOUR : Double,
    @SerializedName("CONVERSIONTYPE") val cONVERSIONTYPE : String,
    @SerializedName("CONVERSIONSYMBOL") val cONVERSIONSYMBOL : String,
    @SerializedName("SUPPLY") val sUPPLY : String,
    @SerializedName("MKTCAP") val mKTCAP : String,
    @SerializedName("MKTCAPPENALTY") val mKTCAPPENALTY : String,
    @SerializedName("CIRCULATINGSUPPLY") val cIRCULATINGSUPPLY : String,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP") val cIRCULATINGSUPPLYMKTCAP : String,
    @SerializedName("TOTALVOLUME24H") val tOTALVOLUME24H : String,
    @SerializedName("TOTALVOLUME24HTO") val tOTALVOLUME24HTO : String,
    @SerializedName("TOTALTOPTIERVOLUME24H") val tOTALTOPTIERVOLUME24H : String,
    @SerializedName("TOTALTOPTIERVOLUME24HTO") val tOTALTOPTIERVOLUME24HTO : String,
    @SerializedName("IMAGEURL") val iMAGEURL : String
)
{
    fun getFormattedTime(): String {
        return convertTimestampToTime(lastUpdate)
    }
    fun getFullImageUrl(): String {
        return BASE_IMAGE_URL + iMAGEURL
    }
}