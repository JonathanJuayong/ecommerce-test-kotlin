package interfaces

import entities.Product

interface CartInterface {
    var products: MutableList<Pair <Product, Int>>
}