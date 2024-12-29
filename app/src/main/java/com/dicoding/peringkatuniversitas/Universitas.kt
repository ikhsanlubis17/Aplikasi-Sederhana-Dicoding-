package com.dicoding.peringkatuniversitas

import android.os.Parcel
import android.os.Parcelable

data class Universitas(
    var name: String,
    var description: String,
    var photo: Int,
    val additionalDescription: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(photo)
        parcel.writeString(additionalDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Universitas> {
        override fun createFromParcel(parcel: Parcel): Universitas {
            return Universitas(parcel)
        }

        override fun newArray(size: Int): Array<Universitas?> {
            return arrayOfNulls(size)
        }
    }
}