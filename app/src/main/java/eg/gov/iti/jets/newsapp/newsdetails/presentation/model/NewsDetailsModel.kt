package eg.gov.iti.jets.newsapp.newsdetails.presentation.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDetailsModel(
    val title: String?,
    val authorName: String?,
    val description: String?,
    val imageUrl: String?,
    var isFavorite:Boolean
): Parcelable
