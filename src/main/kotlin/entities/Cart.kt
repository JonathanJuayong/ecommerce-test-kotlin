package entities

import interfaces.CartInterface

class Cart(
): CartInterface {
    override var products: MutableList<Pair <Product, Int>> = mutableListOf()

    fun deleteProductById(productId: Int) {
        val productToRemove = getProductById(productId)
        products.remove(productToRemove)
    }

    fun getProductById(id: Int): Pair<Product, Int>? =
        products.find {it.first.productId == id}


    fun addProduct(product: Product, qty: Int) {
        if (isProductExistsById(product.productId)) {
            val cartItem = getProductById(product.productId)!!
            products = (products.filter {
                it.first.productId != cartItem.first.productId
            } + Pair(product, cartItem.second + qty)).toMutableList()
        } else {
            products.add(Pair(product, qty))
        }
    }

    fun clearCart() {
        products.clear()
    }

    private fun isProductExistsById(id: Int): Boolean =
        products.find {it.first.productId == id} != null
}