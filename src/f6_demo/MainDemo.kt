package f6_demo

import java.io.BufferedReader

fun check(name: String?) {
    // 下面的意思是str 运行为空， ?. 是空安全运算符，如果name != null 则调用name 的toUpperCase()方法，否则返回null
    val str: String? = name?.toUpperCase()
}

fun check2(s: String?) {
    // 如果s != null ，则返回s，否则返回""， ?: null合并运算符
    val t: String = s ?: ""
}

fun sendE(em: String) {
    println(em)
}

fun send() {
    val em: String? = ""
    em?.let {
        // 这里将 ?. 与let结合使用，可以实现在对表达式求值时，检查求值结果是否为null
        // 这里的实现效果是如果em不为null，则可以调用sendE，否则不会执行
        sendE(em)
    }
    // 下面的实现效果和上面的等价
//    if (em != null){
//        sendE(em)
//    }
}

fun readE(reader: BufferedReader): List<Int?>{
    // 值是可空的集合，注意，arrayListOf是可变集合
    val r = arrayListOf<Int?>()
    for (line in reader.lineSequence()){
        try {
            r.add(line.toInt())
        }catch (e: Exception){
            r.add(null)
        }
    }
    return r
}

fun arrLis(){
    // 创建数组
    val ar = arrayOf("haha","haha")
    for (a in ar){
        println(a)
    }
    ar[0] = "ll"
    for (a in ar){
        println(a)
    }
    // 创建集合
    val ls = arrayListOf("jahah","jajaj")
    ls.add("klklk")
}

fun main() {
    arrLis()
//    val i1 = 1
//    val l1: Long = i1.toLong()
//    println(l1)
}