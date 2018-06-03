package service


import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.withContext
import model.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import kotlin.coroutines.experimental.CoroutineContext

object DatabaseFactory {

    fun init() {
        // Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
        Database.connect(hikari())
        transaction {
            create(UserTasks)
            UserTasks.insert {
                it[id_task] = 1
                it[task_title] = "Task de Teste"
                it[task_description] = "Estamos testando o server"
                it[task_limit] = DateTime.parse("2018-05-30 11:31:33", DateTimeFormat.forPattern("yyyy-mm-dd hh:mm:ss"))
            }
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "com.mysql.jdbc.Driver"
        config.jdbcUrl = "jdbc:mysql://localhost:3306/agendaplusplus"
        config.username = "Moas"
        config.password = "admin"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
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