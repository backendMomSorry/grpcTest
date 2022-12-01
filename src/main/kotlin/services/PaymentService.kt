package services

import bodies.PaymentBody
import dtos.Payment
import repositories.SaleRepository
import java.math.RoundingMode

const val POINTS_SCALE = 0

class PaymentService(
    private val saleRepository: SaleRepository
) {
    fun proceed(body: PaymentBody): Payment {
        val points = (body.price * body.paymentMethod.pointsCoefficient)
            .setScale(POINTS_SCALE, RoundingMode.DOWN)
            .intValueExact()

        saleRepository.save(
            paymentMethod = body.paymentMethod,
            datetime = body.datetime,
            lastFourDigit = body.lastFourSymbol,
            points = points,
            sales = body.price,
            customerId = body.customerId
        )

        return Payment(
            finalPrice = body.price * body.priceModifier,
            points = points
        )
    }
}