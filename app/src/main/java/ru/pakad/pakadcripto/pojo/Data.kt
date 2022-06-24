import com.google.gson.annotations.SerializedName

data class CointInfoListOfData (
	@SerializedName("Data") val data : List<Data>
)

data class Data (
	@SerializedName("CoinInfo") val coinInfo : CoinInfo
)

data class CoinInfo (
	@SerializedName("Name") val name: String? = null
)