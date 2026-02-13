package com.shandy.domain.sellingGoods.repository

import com.shandy.domain.sellingGoods.dto.SellingGoods
import com.shandy.domain.sellingGoods.model.SellingGoodsDAO
import com.shandy.domain.sellingGoods.model.SellingGoodsTable
import com.shandy.domain.sellingGoods.model.daoToModel
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class SellingGoodsRepositoryImpl: SellingGoodsRepository {
    override suspend fun allSellingGoods(): List<SellingGoods> {
        return suspendTransaction {
            SellingGoodsDAO.all().map(::daoToModel)
        }
    }

    override suspend fun sellingGoodsById(id: Uuid): SellingGoods? {
        return suspendTransaction {
            SellingGoodsDAO
                .find { (SellingGoodsTable.id eq id) }
                .limit(1)
                .map(::daoToModel)
                .firstOrNull()
        }
    }

    override suspend fun sellingGoodsByName(name: String): List<SellingGoods> {
        return suspendTransaction {
            SellingGoodsDAO
                .find{ (SellingGoodsTable.goodsName eq name) }
                .map(::daoToModel)
        }
    }

    override suspend fun addSellingGoods(sellingGoods: SellingGoods) {
        suspendTransaction {
            SellingGoodsDAO.new {
                goodsName = sellingGoods.goodsName
                goodsPrice = sellingGoods.goodsPrice
            }
        }
    }

    override suspend fun removeSellingGoods(id: Uuid): Boolean {
        val result = suspendTransaction {
            SellingGoodsDAO.findById(id)?.let { it.delete(); true }
        }
        return result != null
    }
}