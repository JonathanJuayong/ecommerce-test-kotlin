package entities

import enums.Category
import interfaces.ProductInterface

data class Product(
    override val productName: String,
    override val price: Int,
    override val productId: Int,
    override val category: Category
): ProductInterface
