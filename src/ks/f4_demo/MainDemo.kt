package ks.f4_demo

interface click1{
    fun click1()
    // kotlin 接口可有默认实现
    fun show() = println("hahha")
}

class View :click1{
    override fun click1() {
    }

    override fun show() {
//        super.show()
        println("lala")
    }

}

open class View2{
   val name = "hahah"
    // 作为嵌套类，相当于Java中的静态内部类，不能直接访问外部类
    class kk{
        val age = 10
        fun getName(){
            val v = View2()
            println(v.name)
        }
    }
    // 使用inner 作为内部类，可以访问外部类的
    inner class haha{
        fun getName(){
            println(name)
        }
    }
}

/**
 * 密闭类
 */
sealed class Expr{
    class A(name:String,age:Int) : Expr()
    class B(age: Int) :Expr()
}

fun getSeal(e : Expr){
    // 在When中使用密闭类，不需要提供默认分支
    when(e){
        is Expr.A ->{
            // 使用{ } 可以写很多语句
        }
        is Expr.B ->
            println("hahah")
    }
}

interface Expr1
class A1 : Expr1
class B1 : Expr1
class C1 : Expr1

fun getWhen(e: Expr1){
    when(e){
        is A1 ->{
            println()
        }
        is B1, is C1 ->{
            println()
        }
    }
}

class User1 constructor(name : String){
    val name1 = name
    var age = 2

    init {
        age = 3
    }
}

/**
 * 这里是将主构造构造方法私有化，单例的必备写法
 */
class Single private constructor()

open class V2{
    // 由于类名后面没有括号，所以没有主构造方法
    // 下面两个都是从构造方法
    constructor(name: String){

    }
    constructor(name: String,age: Int){

    }
}
class V3 :V2{
    // 这里使用:super(name)调用父类的构造方法
    constructor(name: String):super(name){

    }
}

class V4{
    // 下面的private set 是将name set方法私有化，让外界无法调用修改，仅当前类里可以修改
    var name = "nana"
      private set

    fun setName(name2: String){
        name = "name2"
    }
}

object P1{
    fun send(){
        println("kaka")
    }
}

class B12 private constructor(){

    companion object{
        fun sendMessage(){
            println("haha")
        }
    }
}


fun main() {
    B12.sendMessage()
//    P1.send()
//    val v = View()
//    v.show()
//    val v2 = View2()
//    val h = v2.haha()
//    h.getName()
//    val k = View2.kk()
//    println(k.age)

//    val n = User1("hah")
//    n.name1
//    println(n.age)
//    val v4 = V4()

}