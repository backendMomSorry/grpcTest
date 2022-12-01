package enums

import java.math.BigDecimal

enum class PaymentMethod(val minimumPrice: BigDecimal, val maximumPrice: BigDecimal, val pointsCoefficient: BigDecimal) {

    CASH(BigDecimal("0.9"), BigDecimal("1.0"), BigDecimal("0.05")),
    CASH_ON_DELIVERY(BigDecimal("1.0"), BigDecimal("1.02"), BigDecimal("0.03")),
    VISA(BigDecimal("0.9"), BigDecimal("1.0"), BigDecimal("0.03")),
    MASTERCARD(BigDecimal("0.9"), BigDecimal("1.0"), BigDecimal("0.03")),
    AMEX(BigDecimal("0.98"), BigDecimal("1.01"), BigDecimal("0.02")),
    JCB(BigDecimal("0.95"), BigDecimal("1.0"), BigDecimal("0.05")),
    LINE_PAY(BigDecimal("1.0"), BigDecimal("1.0"), BigDecimal("0.01")),
    PAYPAY(BigDecimal("1.0"), BigDecimal("1.0"), BigDecimal("0.01")),
    POINTS(BigDecimal("1.0"), BigDecimal("1.0"), BigDecimal("0.0")),
    GRAB_PAY(BigDecimal("1.0"), BigDecimal("1.0"), BigDecimal("0.01")),
    BANK_TRANSFER(BigDecimal("1.0"), BigDecimal("1.0"), BigDecimal("0.0")),
    CHEQUE(BigDecimal("0.9"), BigDecimal("1.0"), BigDecimal("0.0")),

}