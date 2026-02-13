package com.shandy

import com.shandy.domain.sellingGoods.repository.SellingGoodsRepository
import com.shandy.domain.sellingGoods.repository.SellingGoodsRepositoryImpl
import com.shandy.domain.sellingGoods.service.SellingGoodsService
import io.ktor.server.application.*
import io.ktor.server.plugins.di.*

fun Application.dependencyManagement() {
    dependencies {
        provide<SellingGoodsRepository> { SellingGoodsRepositoryImpl() }
        provide<SellingGoodsService> { SellingGoodsService(resolve()) }
    }
}