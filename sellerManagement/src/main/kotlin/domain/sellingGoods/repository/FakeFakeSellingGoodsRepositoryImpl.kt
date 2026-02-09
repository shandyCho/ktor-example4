package com.shandy.domain.sellingGoods.repository

import com.shandy.domain.sellingGoods.dto.SellingGoods
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class FakeFakeSellingGoodsRepositoryImpl: FakeSellingGoodsRepository {

    private val goods = mutableListOf<SellingGoods>(
        SellingGoods(id = Uuid.random(), goodsName = "Sailer", goodsPrice = 4000),
        SellingGoods(id = Uuid.random(), goodsName = "pelikan", goodsPrice = 7000),
        SellingGoods(id = Uuid.random(), goodsName = "aurora", goodsPrice = 10000)
    )
    override fun allSellingGoods(): List<SellingGoods> {
        return goods
    }

    override fun sellingGoodsById(id: Uuid): SellingGoods? {
        return goods.find({x -> x.id.equals(id)})
    }

    override fun sellingGoodsByName(name: String): List<SellingGoods> {
        return goods.filter({x -> x.goodsName.equals(name)})
    }

    override fun addSellingGoods(sellingGoods: SellingGoods) {
        if (sellingGoodsById(sellingGoods.id) != null) {
            throw IllegalArgumentException("sellingGoodsById already exists")
        }
        goods.add(sellingGoods)
    }

    override fun removeSellingGoods(sellingGoods: SellingGoods): Boolean {
        return goods.removeIf { x -> x.id.equals(sellingGoods.id) }
    }
}