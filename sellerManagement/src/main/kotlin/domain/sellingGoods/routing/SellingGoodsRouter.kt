package com.shandy.domain.sellingGoods.routing

import com.fasterxml.jackson.core.JsonProcessingException
import com.shandy.domain.sellingGoods.dto.NewSellingGoodsDTO
import com.shandy.domain.sellingGoods.dto.SellingGoods
import com.shandy.domain.sellingGoods.dto.SellingGoodsUUIDStringfy
import com.shandy.domain.sellingGoods.service.SellingGoodsService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.plugins.di.dependencies
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun Application.sellingGoodsRouter() {
    val service: SellingGoodsService by dependencies
    routing {
        route("/selling-goods") {
            get {
                val goods: List<SellingGoodsUUIDStringfy> = service.getAllSellingGoods()

                call.respond(HttpStatusCode.OK, goods)
            }
            get("byId/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val good = service.getSellingGoodsById(Uuid.parse(id))
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
                    val good = service.getSellingGoodsByName(name)
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
                    val good = call.receive<NewSellingGoodsDTO>()
                    val tmp = SellingGoods(Uuid.random(), good.goodsName, good.goodsPrice)
                    service.registerSellingGoods(tmp)
                    call.respond(HttpStatusCode.OK)
                } catch (e: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (e: JsonProcessingException) {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
            delete {
                val good = call.receive<Uuid>()
                if (good == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@delete
                }
                val result = service.deleteSellingGoods(good)
                if (result) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}