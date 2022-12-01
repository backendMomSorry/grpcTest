package database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import java.sql.Connection
import java.sql.SQLException

object DataSource {
    private val config = HikariConfig()
    private var ds: HikariDataSource

    init {
        with(config) {
            jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
            username = "postgres"
            password = "postgres"

            idleTimeout = 60000;
            maxLifetime = 1800000;
            connectionTestQuery = "SELECT current_timestamp";
            minimumIdle = 20
            validationTimeout = 3000

            ds = HikariDataSource(this)
        }
    }

    @get:Throws(SQLException::class)
    val connection: Connection
        get() = ds.connection

    fun migrate() {
        Flyway.configure().dataSource(ds).load().migrate()
    }

}