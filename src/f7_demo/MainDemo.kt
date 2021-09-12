package f7_demo

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import java.lang.IndexOutOfBoundsException
import java.time.LocalDate


data class Point(var x: Int, var y: Int) {
    // 用于重载运算符的所有函数都需要使用operator关键字标记，这里的重载的是 + 对应的是plus
    operator fun plus(other: Point): Point {
        return Point(other.x + x, other.y + y)
    }

    // 用于重载运算符的所有函数都需要使用operator关键字标记，这里的重载的是 - 对应的是minus
    operator fun minus(other: Point): Point {
        return Point(other.x - x, other.y - y)
    }
}

fun Point.add(other: Point): Point {
    return Point(other.x + x, other.y + y)
}

operator fun Point.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("out")
    }
}

operator fun Point.set(index: Int, value: Int) {
    when (index) {
        0 -> x = value
        1 -> y = value
        else ->
            throw IndexOutOfBoundsException("out")
    }
}

fun showP() {
    val p = Point(3, 3)
    val p1 = Point(1, 1)
    val p2 = Point(2, 2)
//    println(p + p1)
//    println(p - p1)
//    println(p.add(p2))
    p[1] = 20
    println(p[1])
}

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in upperLeft.x until lowerRight.x && p.y in upperLeft.y until lowerRight.y
}

fun show2() {
    val r = Rectangle(Point(13, 3), Point(22, 23))
    val p = Point(20, 10)
    println(r.contains(p))
    println(p in r)
}

fun checkTime() {
    val now = LocalDate.now()
    println(now)
    // 创建一个从今天开始的10天的区间
    val vacation = now..now.plusDays(10)
    // 检测一个特定的日子是否属于一个区间内
    println(now.plusWeeks(1) in vacation)
}

fun show3() {
    val p = Point(10, 20)
    // 使用解构声明，下面的代码相当于val x = p.component1（），y = p.component2（）
    val (x, y) = p
    println(x)
}

fun show4(map: Map<String, String>) {
    for ((key, value) in map) {
        println("$key -> $value")
    }
}

open class PropertyChangeAware {
    /**
     * 用来存储PropertyChangeSupport 的实例并监听属性更改
     */
    protected val changeSupport = PropertyChangeSupport(this)
    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }
}

class Person1(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }
}

fun show5() {
    val p = Person1("haha", 20, 20)
    p.addPropertyChangeListener(
        PropertyChangeListener { event ->
            println(
                "Porperty ${event.propertyName} change " +
                        "from ${event.oldValue} to ${event.newValue}"
            )
        }
    )
    p.age = 35
}

class Home {
    // 定义一个属性，然后把值存储到map中
    private val attributes = hashMapOf<String, String>()

    fun setAttr(name: String, value: String) {
        attributes[name] = value
    }

//    val name: String
//        get() = attributes["name"]!!
    // 上面的是从map中手动检索属性

    // 下面使用委托来实现，效果一样
    val name : String by attributes
}

fun show6(){
    val h = Home()
    val d = hashMapOf<String,String>("name" to "haha","2" to "lala")
    for((key,value) in d){
        h.setAttr(key,value)
    }
    println(h.name)
}

fun main() {
    show6()
//    show5()
//    show4(mapOf("1" to "haah", "2" to "lala"))
//    show3()
//    checkTime()
//    show2()
//    showP()
}