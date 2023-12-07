package com.azhar.laundry.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_laundry")
class ModelLaundry : Parcelable {
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "nama_jasa")
    var namaJasa: String? = null

    @JvmField
    @ColumnInfo(name = "items")
    var items = 0

    @JvmField
    @ColumnInfo(name = "alamat")
    var alamat: String? = null

    @JvmField
    @ColumnInfo(name = "harga")
    var harga = 0

    constructor()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(uid)
        dest.writeString(namaJasa)
        dest.writeInt(items)
        dest.writeString(alamat)
        dest.writeInt(harga)
    }

    protected constructor(`in`: Parcel) {
        uid = `in`.readInt()
        namaJasa = `in`.readString()
        items = `in`.readInt()
        alamat = `in`.readString()
        harga = `in`.readInt()
    }

    companion object {
        @JvmField
        val CREATOR: Creator<ModelLaundry?> = object : Creator<ModelLaundry?> {
            override fun createFromParcel(source: Parcel): ModelLaundry? {
                return ModelLaundry(source)
            }

            override fun newArray(size: Int): Array<ModelLaundry?> {
                return arrayOfNulls(size)
            }
        }
    }
}