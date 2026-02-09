package com.shandy

import com.shandy.domain.sellingGoods.configureSerialization
import com.shandy.domain.sellingGoods.repository.FakeFakeSellingGoodsRepositoryImpl
import com.shandy.domain.sellingGoods.repository.SellingGoodsRepository
import com.shandy.domain.sellingGoods.repository.SellingGoodsRepositoryImpl
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val repository = FakeFakeSellingGoodsRepositoryImpl()
    val re2 = SellingGoodsRepositoryImpl()
    configureSerialization(re2)
    configureDatabases()
    configureFrameworks()
    configureRouting()
}
