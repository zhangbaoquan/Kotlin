package ks.f9_demo

import java.util.*

/**
 * 将类型参数标记成reified
 */
inline fun <reified T> loadService(){
    // 这里是将T::class当成类型形参的类访问
    ServiceLoader.load(T::class.java)
}

//class Activity
//
//class Intent(activity1: Activity,activity2: Activity)
//
///**
// * 简化Android上的startActivity
// */
//inline fun <reified T:Activity> startActivity(){
//    val intent = Intent(Activity(),T::class.java)
//    startActivity<>(intent)
//}

fun main() {

}