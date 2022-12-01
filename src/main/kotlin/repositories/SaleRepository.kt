package repositories

import database.DataSource
import dtos.Sale
import enums.PaymentMethod
import org.jooq.RecordMapper
import org.jooq.impl.DSL
import payment.tables.tables.Sales
import payment.tables.tables.records.SalesRecord
import java.math.BigDecimal
import java.time.ZonedDateTime

class SaleRepository {

    fun save(
        paymentMethod: PaymentMethod,
        datetime: ZonedDateTime,
        lastFourDigit: String?,
        points: Int,
        sales: BigDecimal,
        customerId: Long
    ) {
        DataSource.connection.use {
            DSL.using(it)
                .newRecord(Sales.SALES)
                .apply {
                    this.paymentMethod = paymentMethod.name
                    this.datetime = datetime.toOffsetDateTime()
                    this.lastFourDigit = lastFourDigit
                    this.points = points
                    this.sales = sales
                    this.customerId = customerId
                }
                .store()
        }
    }

    fun getSales(startDateTime: ZonedDateTime, endDateTime: ZonedDateTime): List<Sale> {
        return DataSource.connection.use {
            DSL.using(it)
                .selectFrom(Sales.SALES)
                .where(Sales.SALES.DATETIME.between(startDateTime.toOffsetDateTime(), endDateTime.toOffsetDateTime()))
                .fetch()
                .map(Mapper())
        }
    }


    class Mapper : RecordMapper<SalesRecord, Sale> {
        override fun map(record: SalesRecord): Sale = with(record) {
            Sale(
                customerId = customerId,
                sales = sales,
                paymentMethod = paymentMethod,
                datetime = datetime,
                lastFourSymbol = lastFourDigit,
                points = points
            )
        }
    }

}