package bodies

import PaymentMethodDto
import PaymentRequestDto
import enums.PaymentMethod
import exceptions.BadRequestException
import utils.threadLocalFormatter
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime

data class PaymentBody(
    val customerId: Long,
    val price: BigDecimal,
    val priceModifier: BigDecimal,
    val paymentMethod: PaymentMethod,
    val datetime: ZonedDateTime,
    val lastFourSymbol: String?
) {
    constructor(request: PaymentRequestDto) : this(
        customerId = request.customerId,
        price = request.price.toBigDecimal(),
        priceModifier = request.priceModifier.toBigDecimal(),
        paymentMethod = request.paymentMethod.toRequest(),
        datetime = LocalDateTime.parse(request.datetime,  threadLocalFormatter.get()).atZone(UTC),
        lastFourSymbol = request.additionalItem?.last4
    )
}

fun PaymentMethodDto.toRequest() = when (this) {
    PaymentMethodDto.MASTERCARD -> PaymentMethod.MASTERCARD
    PaymentMethodDto.CASH -> PaymentMethod.CASH
    PaymentMethodDto.AMEX -> PaymentMethod.AMEX
    PaymentMethodDto.JCB -> PaymentMethod.JCB
    PaymentMethodDto.BANK_TRANSFER -> PaymentMethod.BANK_TRANSFER
    PaymentMethodDto.CASH_ON_DELIVERY -> PaymentMethod.CASH_ON_DELIVERY
    PaymentMethodDto.CHEQUE -> PaymentMethod.CHEQUE
    PaymentMethodDto.GRAB_PAY -> PaymentMethod.GRAB_PAY
    PaymentMethodDto.LINE_PAY -> PaymentMethod.LINE_PAY
    PaymentMethodDto.PAYPAY -> PaymentMethod.PAYPAY
    PaymentMethodDto.POINTS -> PaymentMethod.POINTS
    PaymentMethodDto.VISA -> PaymentMethod.VISA
    PaymentMethodDto.UNRECOGNIZED -> throw BadRequestException("incorrect PaymentMethod")
}
