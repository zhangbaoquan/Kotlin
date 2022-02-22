package kh.h3

class Bird(val name: String, val age: Int) {

    lateinit var sex: String
    val color: String by lazy {
        if (name == "haha") "Red" else "blue"
    }

    init {
        println("name : {$name},age : {$age}")
    }

    fun printSex() {
        sex = "man"
    }
}