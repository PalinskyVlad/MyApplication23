package com.example.ulad.myapplication23.model

import android.os.Parcel
import android.os.Parcelable

class Product(var id: Long, var name: String, var UPC: String, var producer: String, var price: Int, var shelfLife: Int, var quantity: Int) : Parcelable {

    constructor() : this(0, "", "", "", 0, 0, 0)

    constructor (input: Parcel) : this(input.readLong(), input.readString(), input.readString(), input.readString(), input.readInt(), input.readInt(), input.readInt())

    override fun writeToParcel(output: Parcel, flags: Int) {
        output.writeLong(id)
        output.writeString(name)
        output.writeString(UPC)
        output.writeString(producer)
        output.writeInt(price)
        output.writeInt(shelfLife)
        output.writeInt(quantity)
    }

    override fun describeContents(): Int = 0

    override fun toString() =  "Product(id=$id, name='$name', UPC='$UPC', producer='$producer', price=$price, shelfLife='$shelfLife', quantity=$quantity)"

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel) = Product(parcel)

        override fun newArray(size: Int) = arrayOfNulls<Product>(size)
    }
}