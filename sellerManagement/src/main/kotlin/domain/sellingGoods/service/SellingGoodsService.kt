package com.shandy.domain.sellingGoods.service

import com.shandy.domain.sellingGoods.dto.SellingGoods
import com.shandy.domain.sellingGoods.dto.SellingGoodsUUIDStringfy
import com.shandy.domain.sellingGoods.repository.SellingGoodsRepository

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class SellingGoodsService(private val repository: SellingGoodsRepository) {
    suspend fun getAllSellingGoods (): List<SellingGoodsUUIDStringfy> {
        return repository.allSellingGoods()
            .map { (id, sellerId, goodsName, goodsPrice) -> SellingGoodsUUIDStringfy(id.toString(), goodsName, goodsPrice) }
    }

    suspend fun getSellingGoodsById(goodsId: Uuid): SellingGoods? {
        return repository.sellingGoodsById(goodsId)
    }

    suspend fun getSellingGoodsByName(goodsName: String): List<SellingGoods> {
        return repository.sellingGoodsByName(goodsName)
    }

    suspend fun registerSellingGoods(sellingGoods: SellingGoods) {
        repository.addSellingGoods(sellingGoods)
    }

    suspend fun deleteSellingGoods(id: Uuid): Boolean {
        return repository.removeSellingGoods(id)
    }
}
