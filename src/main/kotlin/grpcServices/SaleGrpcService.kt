package grpcServices

import SaleRequestDto
import SaleResponse
import SaleServiceGrpcKt
import bodies.GetSalesBody
import services.SaleService

class SaleGrpcService(private val saleService: SaleService) : SaleServiceGrpcKt.SaleServiceCoroutineImplBase() {

    override suspend fun sales(request: SaleRequestDto): SaleResponse {
        val sales = saleService.getSales(GetSalesBody(request))
            .map {
                Sale.newBuilder().apply {
                    sales = it.sales.toDouble()
                    points = it.points
                    datetime = it.datetime.toString()
                }
                    .build()
            }

        return SaleResponse.newBuilder()
            .addAllSales(sales)
            .build()
    }

}