package com.shandy.domain.sellingGoods.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewSellingGoodsDTO(
    val goodsName: String,
    val goodsPrice: Int,
)
