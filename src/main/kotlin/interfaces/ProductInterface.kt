package interfaces

import enums.Category

interface ProductInterface {
    val productName: String
    val price: Int
    val productId: Int
    val category: Category
}