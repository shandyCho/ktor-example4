package com.shandy.domain.sellingGoods

import com.fasterxml.jackson.databind.*
import com.shandy.domain.sellingGoods.repository.SellingGoodsRepository
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
}




//fun Application.configureSerialization() {
//    install(ContentNegotiation) {
//        jackson {
//            enable(SerializationFeature.INDENT_OUTPUT)
//        }
//    }
//    routing {
//        get("/json/jackson") {
//            call.respond(mapOf("hello" to "world"))
//        }
//        get("/json/kotlinx-serialization") {
//            call.respond(mapOf("hello" to "world"))
//        }
//    }
//}
