package bodies

import SaleRequestDto
import utils.threadLocalFormatter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime

data class GetSalesBody(
    val startDateTime: ZonedDateTime,
    val endDateTime: ZonedDateTime
) {
    constructor(request: SaleRequestDto) : this(
        startDateTime = LocalDateTime.parse(request.startDateTime, threadLocalFormatter.get()).atZone(ZoneOffset.UTC),
        endDateTime = LocalDateTime.parse(request.endDateTime,  threadLocalFormatter.get()).atZone(ZoneOffset.UTC)
    )
}