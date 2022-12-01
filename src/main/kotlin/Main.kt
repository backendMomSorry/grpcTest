import database.DataSource
import grpcServices.PaymentGrpcService
import grpcServices.SaleGrpcService
import io.grpc.ServerBuilder
import log.ExceptionInterceptor
import repositories.SaleRepository
import services.PaymentService
import services.SaleService

val saleRepository = SaleRepository()
val paymentService = PaymentService(saleRepository)
val paymentGrpcService = PaymentGrpcService(paymentService)
val saleService = SaleService(saleRepository)
val saleGrpcService = SaleGrpcService(saleService)

fun init() {

    DataSource.migrate()

    val server = ServerBuilder
        .forPort(15001)
        .addService(paymentGrpcService)
        .addService(saleGrpcService)
        .intercept(ExceptionInterceptor())
        .build()

    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        server.awaitTermination()
    })

    server.start()
    server.awaitTermination()
}

fun main() {
    init()
}