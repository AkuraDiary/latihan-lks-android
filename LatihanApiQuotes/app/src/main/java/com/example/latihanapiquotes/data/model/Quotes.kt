package com.example.latihanapiquotes.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// according to JSON response
@Parcelize
data class Quotes(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
):Parcelable
