package kh.h2



fun main() {
    // 高阶函数
    advanceFun()
//    println(sum(1, 2))
}

fun advanceFun() {
    // 设置函数引用
    val t1 = ::isBigCountry
    val l1 = listOf(Country("EU", 1000))
    // 高阶函数使用1
    filterCountry(l1, t1)
    // 也可以直接使用
    just(::equal)
    fuu(foo(1))
}

val sum = { a: Int, b: Int -> a + b }

fun isBigCountry(country: Country): Boolean {
    return country.name == "EU" && country.area > 10000
}

// (Int) -> Unit  高阶函数，左边是参数类型，右边是返回类型
// 下面的方法的第二个参数就是函数类型，可以将前面声明的isBigCountry 作为参数传入
fun filterCountry(countries: List<Country>, test: (Country) -> Boolean) {
    val res = mutableListOf<Country>()
    for (r in countries) {
        // 这里调用传入的函数执行
        if (test(r)) {
            res.add(r)
        }
    }
}

fun equal(n1: Int, n2: Int) = n1 == n2
fun just(test: (Int, Int) -> Boolean) {
    if (test(1, 1)) {
        println("相等")
    }
}

// 返回一个函数
fun foo(x: Int): (Int) -> Int {
    // 这里返回的函数用Lambda表达式
    return { y: Int -> x + y }
}

fun fuu(test: (Int) -> Int) {
    println(test(1))
}

fun schedule(sel: Boolean, current: Day) {
    when(current){
        Day.FRI -> {
            println("haha")
            println("lala")
        }
        Day.MON,Day.SAT -> println("hehe")
        else ->{
            println()
        }
    }
}

