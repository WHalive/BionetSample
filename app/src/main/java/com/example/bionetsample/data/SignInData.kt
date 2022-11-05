package com.example.bionetsample.data

data class Regions(
    val  regions: List<RegionItem> = emptyList()
)
data class RegionItem(
    val id: Int,
    val name: String,
) {
    override fun toString() = name
}