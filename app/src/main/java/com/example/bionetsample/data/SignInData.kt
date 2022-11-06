package com.example.bionetsample.data

data class Regions(
    val regions: List<RegionItem> = emptyList()
)

data class RegionItem(
    val id: Int,
    val name: String,
) {
    override fun toString() = name
}


data class SchoolTypeItem(
    val id: Int,
    val name: String,
) {
    override fun toString() = name
}

data class Schools(
    val schools: List<SchoolItem> = emptyList()
)

data class SchoolItem(
    val id: Int,
    val name: String,
) {
    override fun toString() = name
}