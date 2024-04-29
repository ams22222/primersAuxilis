package com.technoPia.primersAuxilis.classes

data class Teoria (
    val id: Int,
    val text: String,
    val title: String,
    val url: String
)

data class Teorias(
    var teorias: MutableList<Teoria>
)