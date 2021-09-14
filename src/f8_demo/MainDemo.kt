package f8_demo

import java.io.BufferedReader
import java.io.FileReader

fun sum1() {
    val s = { x: Int, y: Int -> x + y }
    //下面的s1 和上面的s等价，这里表示有两个Int参数和一个Int返回值的函数
    val s1: (Int, Int) -> Int = { x, y -> x + y }

    val ac = { println(42) }
    // 下面的ac1和上面的等价,这里是没有参数和返回值的函数，Unit类型用于表示函数不返回任何有用的值
    val ac1: () -> Unit = { println(42) }
}

/**
 * 声明一个高阶函数，下面定义了一个函数类型的参数
 */
fun oneAndTwo(operation: (Int, Int) -> Int) {
    // 调用函数类型的参数
    val r = operation(2, 3)
    println("result is $r")
}

enum class Deli {
    STANDARD, EXPEDITED
}

class Order(val itemCount: Int)

/**
 * 声明一个返回函数的函数，以Order作为参数，并返回一个Double类型的值
 */
fun getShopping(del: Deli): (Order) -> Double {
    if (del == Deli.EXPEDITED) {
        return { Order -> 6 + 2.1 * Order.itemCount }
    }
    // 下面返回的是lambda
    return { Order -> 1.2 * Order.itemCount }
}

data class Pers(
    val firstName: String,
    val lastName: String,
    val phoneName: String
)

class ContactListFilters {
    var preFix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    /**
     * 声明一个返回函数类型的函数，返回函数的参数是Pers，返回类型是Boolean
     */
    fun getPredicate(): (Pers) -> Boolean {
        // 声明一个函数类型的变量
        val startWithPrefix = { p: Pers ->
            p.firstName.startsWith(preFix) || p.lastName.startsWith(preFix)
        }
        if (!onlyWithPhoneNumber) {
            return startWithPrefix
        }
        // 将这个函数返回一个lambda
        return { startWithPrefix(it) }
    }
}

fun shw1() {
    val contact = listOf(
        Pers("haha", "kaka", "lala"),
        Pers("nana", "vovo", "xoxo")
    )
    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        preFix = "Dm"
        onlyWithPhoneNumber = true
    }
    println(contact.filter(contactListFilters.getPredicate()))
}

fun readFirstLineFromFile(path: String): String {
    // 构建一个BufferedReader，调用"use"函数，传递一个lambda执行文件操作
    BufferedReader(FileReader(path)).use { br ->
        // 从函数中返回文件的一行
        return br.readLine()
    }
}

fun for1(peo : List<Pers>){
    for (p in peo){
        if (p.lastName == "alice"){
            println("found")
            return
        }
    }
    println("haha")
}

fun for2(peo : List<Pers>){
    peo.forEach{
        if (it.lastName == "alice"){
            println("found")
            return
        }
    }
    println("haha")
}

fun main() {
    shw1()
//    val cal = getShopping(Deli.EXPEDITED)
//    println("哈哈 ： ${cal(Order(3))}")
//    val sum: (Int, Int) -> Int = { x, y ->
//        x + y
//    }
//
//    oneAndTwo(sum)
}