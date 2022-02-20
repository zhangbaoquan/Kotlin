package ks.f3_demo

class Context
open class View

class TextView(context: Context, arr: Int = 0) : View()
class EditView : View()

/**
 * 创建View的扩展函数
 */
fun View.show() = println("View")

fun TextView.show() = println("TextView")

/**
 * 扩展String类型函数
 */
fun String.getLastChar(): Char = get(length - 1)

fun createCollection() {
    val set = hashSetOf<Int>(1, 2, 3)
    val list = arrayListOf<Int>(1, 6, 9)
    val map = hashMapOf<Int, String>(1 to "one", 2 to "two")
    val list1 = listOf<String>("haha", "lala")
    // mutableListOf是可变数组，相当于var,listof 相当于val
    val list2 = mutableListOf<String>("hehe", "kaka")
//    println(set.maxOrNull())
//    list2[0] = ":"
//    println(list2[0])
    println(set)
    println(list)
    println(list1)
    println(map)
}

fun escape() {
    // 使用""" """可以避免被转义
    val str = """2\n3"""
    val str2 = "2\n3"
    println(str)
    println(str2)
}

/**
 * 使用局部函数优化代码结构
 */
fun usePartFun(name: String) {
    fun partFun(name: String, age: Int){
        println("name : $name , age : $age")
    }
    partFun(name,10)
}

fun main() {
//    val context = Context()
//    val v : View = TextView(context)
//    v.show()

//    createCollection()
    escape()
}
