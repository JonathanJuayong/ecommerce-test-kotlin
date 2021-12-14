package entities

import interfaces.UserInterface

class User(
    override var username: String = "",
    override var address: String = "",
): UserInterface{
}