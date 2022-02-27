package kh.h5


data class Stu(val name: String, val age: Int)

fun kLala(stu: Stu?): Stu? {
    // 下面的意思是stu 不为空，则输出name，否则输出"lala"，作用类似于Java的三目运算符
    println(stu?.name ?: "lala")
    return null
}

fun getLala() {
    var l = kLala(null)
    l?.let {
        println(it.name)
    }
}

class SmartList<T> : ArrayList<T>() {
    fun find(t: T): T? {
        val index = super.indexOf(t)
        return if (index >= 0) super.get(index) else null
    }
}


fun main() {
    val s = SmartList<String>()
    s.add("haha")
    // 输出true
    println(s.find("lala").isNullOrEmpty())
    val arry = arrayOf<String>()
    val arry2 = intArrayOf(1, 2)
    for (n in arry2) {
        println(n)
    }

}