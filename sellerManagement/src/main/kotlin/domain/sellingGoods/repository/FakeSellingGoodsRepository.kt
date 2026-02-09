package com.shandy.domain.sellingGoods.repository

import com.shandy.domain.sellingGoods.dto.SellingGoods
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface FakeSellingGoodsRepository {
    fun allSellingGoods(): List<SellingGoods>
    @OptIn(ExperimentalUuidApi::class)
    fun sellingGoodsById(id: Uuid): SellingGoods?
    fun sellingGoodsByName(name: String): List<SellingGoods>
    fun addSellingGoods(sellingGoods: SellingGoods)
    fun removeSellingGoods(sellingGoods: SellingGoods): Boolean
}