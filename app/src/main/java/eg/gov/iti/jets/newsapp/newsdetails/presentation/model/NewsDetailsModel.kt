package eg.gov.iti.jets.newsapp.newsdetails.presentation.model

data class NewsDetailsModel(
    val  title:String,
    val authorName:String,
    val  description:String ,
    val imageUrl:String,
    var isFavorite:Boolean
)