package com.ams.taulaperidica.classes

data class Element (
    val name: String,
    val appearance: String,
    val boil: Double,
    val atomic_mass: Double,

    val category: String,
    val density: Double,
    val discoveredBy: String,
    val melt: Double,
    val molar_heat: Double,
    val namedBy: String,
    val number: Long,
    val period: Long,
    val phase: String,
    val source: String,
    val bohrModelImage: String,
    val bohrModel3D: String,
    val spectralImg: String,
    val summary: String,
    val symbol: String,
    val xpos: Long,
    val ypos: Long,
    val shells: List<Long>,
    val electronConfiguration: String,
    val electronConfigurationSemantic: String,
    val electronAffinity: Double,
    val electronegativityPauling: Double,
    val ionizationEnergies: List<Long>,
    val cpkHex: String,
    val image: Image,
    var fav: Boolean
)

data class Image (
    val title: String,
    val url: String,
    val attribution: String
)

data class Elements(
    var elements: MutableList<Element>
)