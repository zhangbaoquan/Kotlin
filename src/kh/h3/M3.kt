package kh.h3

fun main() {

}

sealed class Fish {
    fun eat() {

    }

    class Lala : Fish()
}

interface Dog {
    fun eat()

    // 接口方法可以有默认实现
    fun kind() = println("haha")
}

interface Cat{
    fun run()

    // 接口方法可以有默认实现
    fun kind() = println("lala")
}

class AnHa : Dog,Cat{
    override fun eat() {

    }

    // 这里指定调用的是Dog类的kind方法。
    override fun kind()  = super<Dog>.kind()

    override fun run() {

    }
}