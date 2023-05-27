package eg.gov.iti.jets.newsapp.newsdetails.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class NewsDetailsModel(
    val title: String?,
    val authorName: String?,
    val description: String?,
    val imageUrl: String?,
    var isFavorite:Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(authorName)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsDetailsModel> {
        override fun createFromParcel(parcel: Parcel): NewsDetailsModel {
            return NewsDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<NewsDetailsModel?> {
            return arrayOfNulls(size)
        }
    }
}