package kh.h3

class RedFish {

    // 伴生对象相当于Java的static
    companion object{
        val name = "jaja"
    }

    fun test(){
        // 调用单例类的成员变量
        RedCat.name = "haha"
    }
}