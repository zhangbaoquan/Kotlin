package ks.f5_demo

data class Per(val name: String, val age: Int)

fun getOldLest(p: List<Per>) {
    var oldP: Per? = null
    for (person in p) {
        if (person.age > 50) {
            oldP = person
        }
    }
    println(oldP)
}

fun findOld() {
    val people = listOf<Per>(Per("haha", 20), Per("kaka", 67))
    println(people.maxByOrNull {
        // 这里的lambda省略了参数的声明，
        it.age
    })
}

fun myLambda() {
    // 这里是完整的演示，x:Int,y:Int 是参数，x+y 是函数体
    val sum = { x: Int, y: Int ->
        x + y
        println("x : $x, y : $y")
    }
    println(sum(1, 2))
}

fun lab() {
    // 如果把一个函数转换成一个值，就可以传递
    val getAge = Per::age
    // 这句话同上
    val getAge2 = { p: Per -> p.age }
    val people = listOf<Per>(Per("haha", 20), Per("kaka", 67))
    println(people.maxByOrNull(getAge))
}

fun listMapFun() {
    val people = listOf<Per>(Per("haha", 20), Per("kaka", 67))
    println(people.filter {
        it.age > 30
    })
    // filter 函数可以从集合中过滤掉一些元素，但不会真正的改变这个集合
    println(people)
}

fun listMapFun1() {
    val list = listOf(1, 2, 3, 4)
    println(list.map {
        it * it
    })
    println(list)
}

fun getName() {
    val people = listOf<Per>(Per("haha", 20), Per("kaka", 67))
    println(people.map {
        it.name
    })
    // 下面的写法和上面等效
    println(people.map(Per::name))
}

fun getName2() {
    val people = listOf<Per>(Per("haha", 20), Per("kaka", 67))
    println(people.filter {
        it.age > 30
    }.map {
        it.name
    })
    // 下面的等效
//    val age = {pe :Per -> pe.age>30}
//    val name = Per::name
//    println(
//        people.filter(age).map(name)
//    )
}

fun getAge() {
    val people = listOf<Per>(Per("haha", 20), Per("kaka", 67))
    val age = { pe: Per ->
        pe.age > 30
    }
    // all 和 any 都返回是布尔值
    // 是否所有的元素都满足年龄>30
    println("all : ${people.all(age)}")
    // 是有有元素满足年龄>30
    println("any : ${people.any(age)}")
    // count返回满足条件的数量
    println("count ：${people.count(age)}")
    // find 返回满足条件的第一个元素
    println("find : ${people.find(age)}")
}

fun funGroup() {
    val people = listOf<Per>(Per("haha", 20), Per("kaka", 67), Per("jiji", 67))
    // groupBy 可以把列表转换成分组的map
    println(people.groupBy {
        it.age
    })
    // 上面的结果是map<Int，List<Per>>，即：{20=[Per(name=haha, age=20)], 67=[Per(name=kaka, age=67), Per(name=jiji, age=67)]}
}

fun funFlatMap() {
    val li = listOf<String>("abc", "abd")
    val l2 = li.flatMap {
        it.toList()
    }
    // 使用flatMap将数据做映射（变换）
    println(l2)
    // 使用toSet 将重复数据去除
    println(l2.toSet())
    val l3 = "bnmk"
    val gn = 12233
    // 使用toList将字符串转成列表
    println(l3.toList())
}

fun funSe() {
    val people = listOf<Per>(
        Per("haha", 20), Per("kaka", 67),
        Per("hihi", 67), Per("hihi", 67)
    )
    println(
        //  使用asSequence 可以将初始集合转换成序列
        people.asSequence()
            .map { it.name }
            .filter { it.startsWith("h") }
            .toSet()
        // 在序列操作中，map、filter都是中间操作，返回是另一个序列，toSet是一次末端操作，返回的是一个结果。
        // 没有末端操作的调用，中间操作是不会被调用的。
    )
    println(
        listOf(1, 2, 3, 4, 5).asSequence().map {
            it * it
        }.toSet()
    )
}

fun funWith() : String{
    val sb1 = StringBuilder()
    for (s in 'A'..'B'){
        sb1.append(s)
    }
    sb1.append("hahah")
    println(sb1.toString())
    // 下面的实现和上面的实现，效果等价
    val sb = StringBuffer()
    return with(sb){
        for (str in 'A'..'Z'){
            this.append(str)
        }
        append("hahah")
        // 从lambda返回值
        this.toString()
    }
    // with的返回值是执行lambda的结果，该结果是lambda中最后一个表达式
}

/**
 * 该方法和上面的with效果一样，这里会返回实参传递给它的对象，个人觉的写起来会更加简单便捷
 */
fun funApply() = StringBuilder().apply {
    for (str in 'A'..'Z'){
        append(str)
    }
    append("hahah")
}.toString()

fun main() {
//    funSe()
//    funFlatMap()
//    funGroup()
//    getAge()
//    getName2()
//    listMapFun1()
//    listMapFun()
//  lab()
}