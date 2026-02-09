package com.shandy.domain.sellingGoods.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class SellingGoodsUUIDStringfy(val id: String,
                                    val goodsName: String,
                                    val goodsPrice: Int)
