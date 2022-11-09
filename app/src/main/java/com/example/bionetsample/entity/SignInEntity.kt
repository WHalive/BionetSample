package com.example.bionetsample.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bionetsample.data.RegionItem

data class RegionsEntities(val regions: List<RegionEntity>)

@Entity(tableName = "region_entity")
data class RegionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
) {
    override fun toString() = name
}