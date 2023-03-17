package com.example.flightsearchapp.utlis

suspend fun<T> List<T>.generatePairsToElement(el: T): List<Pair<T, T>> {
    val pairList: MutableList<Pair<T, T>> = mutableListOf()
    for (pair in this)
        if (pair != el) pairList.add(Pair(el, pair))

    return pairList
}