package ks.f1_2demo

fun main() {
//    initData()
//    println("color : ${switchFun(Color.YELLOW)}")
    show()
}

fun max(a: Int, b: Int): Int = a + b

fun initData() {
    var person = Person("haha", 20)
    person.age = 21
    println("name : ${person.name} ,age :  ${person.age}, hobby : ${person.hobby}")

}

fun switchFun(color: Color) =
    when (color) {
        Color.BLUE ->{
            println("haha")
            getColorName()
        }
        Color.READ ->
            println("haha")
        Color.YELLOW ->
            println("hehe")
    }

fun getColorName() :String{
    return "haha"
}

fun show(){
    val str = try {
        for (i in 1..8){
            // 这里会输出8
            println("闭区间 ： $i")
        }
//        for (i in 10 until 15){
//            // 下面的不会输出15，只会输出14
//            println("开区间 ： $i")
//        }
        10
    }catch (e : Exception){
        -1
    }
    println(str)

}


enum class Color(val r: Int, val g: Int, val b: Int) {
    READ(255, 0, 0),
    BLUE(0, 0, 255),
    YELLOW(255, 255, 0);

    fun getColor() = (r * 256 + g) * 256 + b
}