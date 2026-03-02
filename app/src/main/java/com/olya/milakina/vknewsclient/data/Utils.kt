package com.olya.milakina.vknewsclient.data

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import kotlin.random.Random

internal fun String.toDomainDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT)
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val outputFormat = SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault())
    outputFormat.timeZone = TimeZone.getDefault()

    val date = inputFormat.parse(this)
    return date?.let { outputFormat.format(it) } ?: ""
}

internal fun getCount(): Int = Random.nextInt(0, 10_000)
