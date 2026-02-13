package com.shandy.domain.userBalance.model

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.UuidTable
import org.jetbrains.exposed.v1.dao.UuidEntity
import org.jetbrains.exposed.v1.dao.UuidEntityClass
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

object UserBalanceTable: UuidTable("user_balance") {
    @OptIn(ExperimentalUuidApi::class)
    val sellerId = uuid("seller_id")
    val proceeds = integer("proceeds")
    val withdrawal = integer("withdrawal")
}

@OptIn(ExperimentalUuidApi::class)
class UserBalanceDAO(id: EntityID<Uuid>): UuidEntity(id) {
    companion object : UuidEntityClass<UserBalanceDAO>(UserBalanceTable)
    var sellerId by UserBalanceTable.sellerId
    var proceeds by UserBalanceTable.proceeds
    var withdrawal by UserBalanceTable.withdrawal
}
