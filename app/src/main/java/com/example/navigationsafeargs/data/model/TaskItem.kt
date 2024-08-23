package com.example.navigationsafeargs.data.model
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class TaskItem(

    @ColumnInfo(name = "title")
    var taskname: String,
    @ColumnInfo(name = "Description")
    var taskDescription: String,
    @ColumnInfo(name = "priority")
    var priority: Int

):Parcelable  {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @IgnoredOnParcel
    var id: Int=0
}
