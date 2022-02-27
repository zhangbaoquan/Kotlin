package kh.h6

fun t1() {
    val list = listOf(1, 2, 3, 4, 5)
    val newList = list.map {
        it * 2
    }
    newList.map {
        println(it)
    }
}

data class Student(val name: String, val age: Int)

fun t2() {
    val s1 = Student("lala", 1)
    val s2 = Student("haha", 2)
    val list1 = listOf<Student>(s1, s2)
    val result = list1.filter {
        it.age > 1
    }.map {
        println(it.name)
    }

    val count = list1.count {
        it.age >= 2
    }
    println(count)

    // 使用sumOf 可以对集合对象里的数值属性求和
    val c2 = list1.sumOf {
        it.age
    }
    println(c2)
}

fun t3() {
    val list1 = listOf<Int>(1, 2, 3, 4, 5)

    // fold 里传入的是自定义计算的初始位置，这里表示从第1个数到最后一个数的累乘的结果
//    val r = list1.fold(1) { result,item ->
    // reduce 不需要初始值，其他和fold一样
    val r = list1.reduce { result, item ->
        result * item
    }
    println(r)
}

fun t4() {
    val s1 = Student("lala", 1)
    val s2 = Student("haha", 2)
    val s3 = Student("haha", 3)
    val list1 = listOf<Student>(s1, s2, s3)

    // 使用groupBy 按照名字进行分组，返回的是一个map对象
    val r = list1.groupBy { it.name }

    val list2 = listOf(listOf(s1, s2), listOf(s2, s3))
    list2.flatMap {
        it.map {
            println(it.name)
        }
    }
    // 使用flatten将嵌套的集合扁平化
    println(list2.flatten())
}

fun t5() {
    val list = listOf<Int>(1, 2, 3, 4, 5)
    // 使用asSequence 将集合转成序列，可以避免里面的集合操作（例如下面的filter、map）产生临时集合进而减小开销，最后再调用toList将序列转成集合
    val l2 = list.asSequence().filter {
        it > 2
    }.map {
        it * 2
    }.toList()
    l2.map {
        println(it)
    }
}

fun t6() {
    // 这里是创建了一个无限自然序列
    val n = generateSequence(0) { it + 1 }
    // 这里是从上面创建的自然序列中选取前10个自然数
    val n2 = n.takeWhile { it <= 9 }.toList()
    n2.map {
        println(it)
    }
}

// 高阶函数，参数是一个无参且类型是Unit 的函数（或者说是Lambda）
fun t7(block:()->Unit){
    println("pre")
    block()
    println("end")
}

inline fun t8(block:()->Unit){
    println("pre")
    block()
    println("end")
}

// 第二个参数不需要内联
inline fun t9(block:()->Unit,noinline block2: () -> Unit){
    println("pre")
    block()
    println("end")
}

inline fun <reified T>t10(){
    println(T::class)
}

fun main() {
//    t1()
//    t2()
//    t3()
//    t4()
//    t5()
//    t6()
//    t7 {
//        println("哈哈")
//    }
//    t8 {
//        println("哈哈")
//    }
//    t9(
//        {
//            println("haha")
//        },
//        {
//            println("lala")
//        }
//    )
    t10<Int>()
}