package com.example.proyecto1

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class GameOptions(
    val numPreguntas: Int = 5,
    val dificultad: String = "NORMAL",
    val conPistas: Boolean = true,
    val temas: List<Boolean> = listOf(true, true, true, true, true)
) : Parcelable {
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(numPreguntas)
        dest.writeString(dificultad)
        dest.writeByte(if (conPistas) 1.toByte() else 0.toByte())
        dest.writeBooleanArray(temas.toBooleanArray())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameOptions> {
        override fun createFromParcel(parcel: Parcel): GameOptions {
            return GameOptions(
                parcel.readInt(),
                parcel.readString() ?: "NORMAL",
                parcel.readByte() != 0.toByte(),
                parcel.createBooleanArray()?.toList() ?: listOf(true, true, true, true, true)
            )
        }

        override fun newArray(size: Int): Array<GameOptions?> {
            return arrayOfNulls(size)
        }
    }
}


class MainViewModel : ViewModel() {

    private val _gameOptions = MutableLiveData<GameOptions>(GameOptions())
    val gameOptions: LiveData<GameOptions> = _gameOptions

    fun updateGameOptions(newOptions: GameOptions) {
        _gameOptions.value = newOptions
    }
}