package com.shandy.domain.sellingGoods.repository

import com.shandy.domain.sellingGoods.dto.SellingGoods
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
    자세한 내용은 하기 링크 참고
    https://ktor.io/docs/server-integrate-database.html#adapt-repo
 */

interface SellingGoodsRepository {
    suspend fun allSellingGoods(): List<SellingGoods>
    @OptIn(ExperimentalUuidApi::class)
    suspend fun sellingGoodsById(id: Uuid): SellingGoods?
    suspend fun sellingGoodsByName(name: String): List<SellingGoods>
    suspend fun addSellingGoods(sellingGoods: SellingGoods)
    @OptIn(ExperimentalUuidApi::class)
    suspend fun removeSellingGoods(id: Uuid): Boolean
}