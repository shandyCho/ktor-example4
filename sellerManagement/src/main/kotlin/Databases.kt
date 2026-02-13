package com.shandy

import com.shandy.domain.sellingGoods.repository.SellingGoodsRepository
import com.shandy.domain.sellingGoods.repository.SellingGoodsRepositoryImpl
import io.ktor.server.application.*
import io.ktor.server.config.property
import io.ktor.server.plugins.di.dependencies
import java.sql.Connection

import org.jetbrains.exposed.v1.jdbc.Database

fun Application.configureDatabases() {
//    val dbConnection: Connection = connectToPostgres(embedded = true)
    connectToPostgres(embedded = false)
}

/**
 * Makes a connection to a Postgres database.
 *
 * In order to connect to your running Postgres process,
 * please specify the following parameters in your configuration file:
 * - postgres.url -- Url of your running database process.
 * - postgres.user -- Username for database connection
 * - postgres.password -- Password for database connection
 *
 * If you don't have a database process running yet, you may need to [download]((https://www.postgresql.org/download/))
 * and install Postgres and follow the instructions [here](https://postgresapp.com/).
 * Then, you would be able to edit your url,  which is usually "jdbc:postgresql://host:port/database", as well as
 * user and password values.
 *
 *
 * @param embedded -- if [true] defaults to an embedded database for tests that runs locally in the same process.
 * In this case you don't have to provide any parameters in configuration file, and you don't have to run a process.
 *
 * @return [Unit] that represent connection to the database. Please, don't forget to close this connection when
 * your application shuts down by calling [Connection.close]
 * */
fun Application.connectToPostgres(embedded: Boolean) {
    Class.forName("org.postgresql.Driver")
    if (embedded) {
        log.info("Using embedded H2 database for testing; replace this flag to use postgres")
//        return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "root", "")
    } else {
        val url = environment.config.property("postgres.url").getString()
        log.info("Connecting to postgres database at $url")
        val user = environment.config.property("postgres.user").getString()
        val password = environment.config.property("postgres.password").getString()
        Database.connect(
            url = url,
            user = user,
            password = password
        )
    }
}
