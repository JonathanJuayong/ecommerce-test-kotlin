package entities

import loaders.ProductLoader
import utils.Utils

class EcommerceApp(
    private val user: User,
) {
    private val productsList = ProductLoader.products
    private val cart = Cart()

    private fun askForUsername(): String =
        Utils.askForInput(
            "Please enter a username (Must 3-15 characters)",
            "Invalid username",
            { it.length >= 3 },
            { it.length <= 15 }
        )

    private fun askForMenuOption(): String =
        Utils.askForInput(
            "",
            "Please enter either a, b, c, e",
            { it in listOf("a", "b", "c", "e") },
        )

    private fun askForProductId(): String =
        Utils.askForInput(
            "Please enter product ID",
            "Invalid product ID",
            { it in productsList.map { product -> "${product.productId}" } }
        )

    private fun askForProductQty(): String =
        Utils.askForInput(
            "Please enter qty (1 - 99)",
            "Please enter a valid amount",
            { it.toIntOrNull() != null },
            { it.toInt() in (1..99).toList() }
        )

    private fun askForCartOption(): String =
        Utils.askForInput(
            "",
            "Please enter either b, or d",
            { it in listOf("b", "d") }
        )

    private fun userSetup() {
        val username = askForUsername()
        user.username = username
        println("Hello, ${user.username}! Welcome to the store!")
    }

    private fun displayProducts() {
        println("Here are our products:")
        println("ID | Product Name | Price | Category ")
        productsList.forEach {
            println("${it.productId} | ${it.productName} | PHP ${it.price} | ${it.category}")
        }
        val productId = askForProductId().toInt()
        val qty = askForProductQty().toInt()
        val product = productsList.find { it.productId == productId }!!
        cart.addProduct(product, qty)

        println("${product.productName} has been successfully added to cart.")
    }

    private fun displayCart() {
        println("Here is your cart:")
        println("ID | Product Name | Price | Qty")
        cart.products.forEach { (product, qty) ->
            println("${product.productId} | ${product.productName} | PHP ${product.price} | $qty")
        }

        println("Enter (d) to delete an item")
        println("Enter (b) to go back to main menu")
        when (askForCartOption()) {
            "d" -> {
                deleteProductFromCart()
                displayCart()
            }
            "b" -> return
        }
    }

    private fun displayCheckout() {
        if (cart.products.isEmpty()) {
            println("Your cart is empty.")
            return
        }
        val userInput = Utils.askForInput(
            "Please enter your address (up to 100 characters)",
            "Invalid address",
            { it.length <= 50 }
        )

        user.address = userInput
        cart.clearCart()
        println("Your address is ${user.address}")
        println("Items will be delivered in 7 to 10 days,")
    }

    private fun deleteProductFromCart() {
        if (cart.products.isEmpty()) {
            println("Your cart is empty.")
            println()
            return
        }

        val ids = cart.products.map { "${it.first.productId}" }
        val userInput = Utils.askForInput(
            "Please enter the product ID to be deleted",
            "ID cannot be found.",
            { it in ids },
        ).toInt()
        cart.deleteProductById(userInput)
        println("Item with id $userInput has been successfully deleted.")
    }

    private fun displayMainMenu() {
        val menuOptions = listOf("a - Browse Products", "b - View Cart", "c - Checkout", "e - Exit")
        println("Please select from the menu below")
        menuOptions.forEach { println(it) }
        when (askForMenuOption()) {
            "a" -> {
                displayProducts()
                displayMainMenu()
            }
            "b" -> {
                displayCart()
                displayMainMenu()
            }
            "c" -> {
                displayCheckout()
                displayMainMenu()
            }
            "e" -> {
                println("Thank you for shopping with us!")
            }
        }
    }

    fun runApp() {
        userSetup()
        displayMainMenu()
    }
}