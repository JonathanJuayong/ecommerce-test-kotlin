import entities.Cart
import entities.EcommerceApp
import entities.Order
import entities.User

fun main() {
    val initialUser = User()
    EcommerceApp(initialUser).runApp()
}