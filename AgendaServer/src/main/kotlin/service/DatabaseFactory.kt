package service

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlin.coroutines.experimental.CoroutineContext
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.FileInputStream
import java.util.*

object DatabaseFactory {

    fun init() {

        Database.connect(hikari())

        // Test database transaction:

        /* transaction {
            TB_User_task.insert {
                it[task_title] = "Task de Teste II"
                it[task_description] = "Estamos testando o server"
                it[task_limit] = DateTime.parse("2018-06-06 10:55:33", DateTimeFormat.forPattern("yyyy-mm-dd hh:mm:ss"))
                it[fk_user] = 2
            }
        } */

    }

    private fun hikari(): HikariDataSource {

        val config = HikariConfig()
        val dbFile = FileInputStream("src/main/resources/db.properties")
        val dbProperties: Properties = Properties()

        dbProperties.load(dbFile)

        config.driverClassName = "com.mysql.jdbc.Driver"
        config.jdbcUrl = "jdbc:mysql://localhost:3306/AgendaPlusPlus"
        config.username = dbProperties.getProperty("db.username")
        config.password = dbProperties.getProperty("db.password")
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        dbFile.close()

        return HikariDataSource(config)

    }

    private val dispatcher: CoroutineContext

    init {
        dispatcher = newFixedThreadPoolContext(5, "database-pool")
    }

    suspend fun <T> dbQuery(
            block: () -> T): T =
            withContext(dispatcher) {
                transaction { block() }
            }

}