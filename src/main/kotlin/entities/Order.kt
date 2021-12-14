package entities

import interfaces.OrderInterface
import java.time.LocalDate

class Order(
    override val cart: Cart,
    override val dateCreated: LocalDate = LocalDate.now(),
    override val orderId: Int = 0
): OrderInterface{
}