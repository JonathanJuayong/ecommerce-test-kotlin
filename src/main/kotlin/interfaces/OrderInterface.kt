package interfaces

import entities.Cart
import java.time.LocalDate

interface OrderInterface {
    val cart: Cart
    val dateCreated: LocalDate
    val orderId: Int
}