package com.example.bionetsample.data

data class Regions(
    val  regions: List<RegionItem> = emptyList()
)
data class RegionItem(
    var id: Int,
    var name: String = ""
)