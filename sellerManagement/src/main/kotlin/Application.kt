package com.shandy

import com.shandy.domain.sellingGoods.configureSerialization
import com.shandy.domain.sellingGoods.routing.sellingGoodsRouter
import io.ktor.server.application.*


fun main(args: Array<String>) {
//    io.ktor.server.netty.EngineMain.main(args)
    // 서버가 생성되자마자 실행되는 대신, 서버를 생성한 다음 실행되기 까지 필요한 작업이 존재하는 경우,
    // 다음의 코드를 사용하여 server.start() 이전에 필요한 작업들을 등록하여 실행할 수 있다
    val server = io.ktor.server.netty.EngineMain.createServer(args)
    // 이 곳에 서버가 실행되기 전 수행해야할 작업들을 등록할 수 있다.

    server.start(wait = true)
}

fun Application.module() {
    println("load default module")
    configureDatabases()
    configureFrameworks()
    configureRouting()
}

fun Application.sellingGoodsModule() {
    println("load selling goods module")
    dependencyManagement()
    configureSerialization()
    sellingGoodsRouter()

}

