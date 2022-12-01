package dtos

import java.math.BigDecimal
import java.time.OffsetDateTime

data class Sale(
    val customerId: Long,
    val sales: BigDecimal,
    val paymentMethod: String,
    val datetime: OffsetDateTime,
    val lastFourSymbol: String?,
    val points: Int
)