package services

import bodies.GetSalesBody
import dtos.Sale
import repositories.SaleRepository

class SaleService(private val saleRepository: SaleRepository) {

    fun getSales(body: GetSalesBody): List<Sale> {
        return saleRepository.getSales(body.startDateTime, body.endDateTime)
    }

}