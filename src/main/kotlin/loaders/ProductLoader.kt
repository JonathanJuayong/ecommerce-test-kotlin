package loaders

import entities.Product
import enums.Category.*

class ProductLoader {
    companion object {
        val products = listOf<Product>(
            Product("Item 1", 100,1, A),
            Product("Item 2", 200,2, B),
            Product("Item 3", 300,3, C),
            Product("Item 4", 400,4, A),
            Product("Item 5", 500,5, B),
            Product("Item 6", 600,6, C),
            Product("Item 7", 700,7, A),
            Product("Item 8", 800,8, B),
            Product("Item 9", 900,9, C),
            Product("Item 10", 1000,10, A),
        )
    }
}