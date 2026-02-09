package com.shandy.domain.sellingGoods

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.*
import com.shandy.domain.sellingGoods.dto.SellingGoods
import com.shandy.domain.sellingGoods.dto.SellingGoodsUUIDStringfy
import com.shandy.domain.sellingGoods.repository.FakeSellingGoodsRepository
import com.shandy.domain.sellingGoods.repository.SellingGoodsRepository
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun Application.configureSerialization(repository: SellingGoodsRepository) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        route("/selling-goods") {
            get {
                val goods: List<SellingGoodsUUIDStringfy> = repository.allSellingGoods().map { (id, goodsName, goodsPrice) -> SellingGoodsUUIDStringfy(id.toString(), goodsName, goodsPrice) }
                call.respond(HttpStatusCode.OK, goods)
            }
            get("byId/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val good = repository.sellingGoodsById(Uuid.parse(id))
                if (good == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(HttpStatusCode.OK, good)
            }
            get("/byName/{name}") {
                val name = call.parameters["name"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                try {
                    val good = repository.sellingGoodsByName(name)
                    if (good.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }
                    call.respond(HttpStatusCode.OK, good)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
            post {
                try {
                    val good = call.receive<SellingGoods>()
                    repository.addSellingGoods(good)
                    call.respond(HttpStatusCode.OK)
                } catch (e: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (e: JsonProcessingException) {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
            delete {
                val good = call.receive<SellingGoods>()
                if (good == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@delete
                }
                val result = repository.removeSellingGoods(good)
                if (result) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
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
