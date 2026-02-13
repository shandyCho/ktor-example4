package com.shandy.domain.sellingGoods.model

import com.shandy.domain.sellingGoods.dto.SellingGoods
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.UuidTable
import org.jetbrains.exposed.v1.dao.UuidEntity
import org.jetbrains.exposed.v1.dao.UuidEntityClass
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
object SellingGoodsTable: UuidTable("selling_goods") {
//    val id = uuid("id").entityId()
    val sellerId = uuid("seller_id")
    val goodsName = varchar("goods_name", 255)
    val goodsPrice = integer("goods_price")
}


@OptIn(ExperimentalUuidApi::class)
class SellingGoodsDAO(id: EntityID<Uuid>): UuidEntity(id){
    companion object : UuidEntityClass<SellingGoodsDAO>(SellingGoodsTable)
    val sellerId by SellingGoodsTable.sellerId
    var goodsName by SellingGoodsTable.goodsName
    var goodsPrice by SellingGoodsTable.goodsPrice


}

@OptIn(ExperimentalUuidApi::class)
fun daoToModel(dao: SellingGoodsDAO) = SellingGoods(
    dao.id.value,
    dao.sellerId,
    dao.goodsName,
    dao.goodsPrice,
)