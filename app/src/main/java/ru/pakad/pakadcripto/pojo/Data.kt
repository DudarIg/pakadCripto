import com.google.gson.annotations.SerializedName

data class CointInfoListOfData (
	@SerializedName("Data") val data : List<Data>
)

data class Data (
	@SerializedName("CoinInfo") val coinInfo : CoinInfo
)

data class CoinInfo (
	@SerializedName("Id") val id : Int,
	@SerializedName("Name") val name : String,
	@SerializedName("FullName") val fullName : String,
	@SerializedName("ImageUrl") val imageUrl : String
)