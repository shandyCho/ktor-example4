package com.shandy.domain.sellingGoods.model

import com.shandy.domain.sellingGoods.dto.SellingGoods
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.v1.core.Transaction
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.UuidTable
import org.jetbrains.exposed.v1.dao.UuidEntity
import org.jetbrains.exposed.v1.dao.UuidEntityClass
import org.jetbrains.exposed.v1.jdbc.transactions.experimental.newSuspendedTransaction
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


object SellingGoodsTable: UuidTable("selling_goods") {
//    val id = uuid("id").entityId()
    val goodsName = varchar("goodsName", 255)
    val goodsPrice = integer("goodsPrice")
}


@OptIn(ExperimentalUuidApi::class)
class SellingGoodsDAO(id: EntityID<Uuid>): UuidEntity(id){
    companion object : UuidEntityClass<SellingGoodsDAO>(SellingGoodsTable)

    var goodsName by SellingGoodsTable.goodsName
    var goodsPrice by SellingGoodsTable.goodsPrice


}

suspend fun <T> suspendedTransaction(block: Transaction.() -> T): T {
    return newSuspendedTransaction(Dispatchers.IO, statement = block)
}

@OptIn(ExperimentalUuidApi::class)
fun daoToModel(dao: SellingGoodsDAO) = SellingGoods(
    dao.id.value,
    dao.goodsName,
    dao.goodsPrice,
)