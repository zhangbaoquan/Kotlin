package kh.h7

open class B
class E :B()

// 给B新增一个扩展函数
fun B.foo() = "lalal"
fun main() {
   val l = B()
    l.foo()
}