package grpcServices

import PaymentRequestDto
import PaymentResponse
import PaymentServiceGrpcKt
import bodies.PaymentBody
import services.PaymentService

class PaymentGrpcService(private val paymentService: PaymentService) :
    PaymentServiceGrpcKt.PaymentServiceCoroutineImplBase() {

    override suspend fun proceed(request: PaymentRequestDto): PaymentResponse {
        val body = PaymentBody(request)
        val responseBuilder = PaymentResponse.newBuilder()

        if (body.priceModifier !in body.paymentMethod.minimumPrice..body.paymentMethod.maximumPrice) {
            responseBuilder.error = "incorrect price modifier"
            return responseBuilder.build()
        }

        val payment = paymentService.proceed(body)

        return responseBuilder
            .apply {
                finalPrice = payment.finalPrice.toDouble()
                points = payment.points
            }
            .build()
    }

}