package ks.f1_2demo

class Person(val name : String,var age : Int,val hobby : String = "read") {
    // name使用的val 表示只读属性，仅能调用getter方法
    // age 使用的是var,表示既可以读、也可以写
    val isChild : Boolean
         get() {
             return age < 18
         }
}