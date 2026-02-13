package com.shandy.domain.sellingGoods.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@OptIn(ExperimentalUuidApi::class)
data class SellingGoods(val id: Uuid,
                        val sellerId: Uuid,
                        val goodsName: String,
                        val goodsPrice: Int)
