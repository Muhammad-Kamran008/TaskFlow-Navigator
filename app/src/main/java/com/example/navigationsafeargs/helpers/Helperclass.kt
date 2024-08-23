package com.example.navigationsafeargs.helpers

import android.content.Context
import android.widget.Toast

fun Context.toast(massage:String)= Toast.makeText(this,massage,Toast.LENGTH_LONG).show()