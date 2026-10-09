package k_coroutines

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime


/**
 * author       : coffer
 * date         : 2024/10/13
 * description  :
 */

fun main() {
//    b2T1()
//    b2T2()
//    b2T3()
//    b2T4()
//    b2T5()
//    b2T6()
    b2T7()
}

fun b2T1() {
    runBlocking {
        val job = launch {
            delay(1000L)
        }
        job.log()
        job.cancel()
        job.log()
        delay(1500)
    }
}

fun b2T2(){
    runBlocking {
        val job = launch(start = CoroutineStart.LAZY){
            logX("Coroutine Start")
            delay(1000)
        }
        delay(500)
        job.log()
        // 开启协程任务
        job.start()
        job.log()
        delay(500)
        job.cancel()
        delay(500)
        job.log()
        delay(2000)
        logX("Process end!")
    }
}

fun b2T3(){
    runBlocking {
        suspend fun download(){
            // 模拟下载任务
            val time = (Random.nextDouble()*1000).toLong()
            logX("Delay time: = $time")
            delay(time)
        }

        val job = launch(start = CoroutineStart.LAZY){
            logX("Coroutine start!")
            download()
            logX("Coroutine end!")
        }
        delay(500)
        job.log()
        job.start()
        job.log()
        job.invokeOnCompletion {
            // 协程结束后，会调用下面的代码
            job.log()
        }
        // 等待协程执行完毕
        job.join()
        logX("Process end!")
    }

}

fun b2T4(){
    runBlocking {
        val parentJob: Job
        var job1: Job? = null
        var job2: Job? = null
        var job3: Job? = null

        parentJob = launch {
            job1 = launch {
                delay(1000)
            }
            job2 = launch {
                delay(3000)
            }
            job3 = launch {
                delay(5000)
            }
        }
        delay(500)
        parentJob.children.forEachIndexed { index,job ->
            when(index){
                0 -> println("job1 === job is ${job1 === job}")
                1 -> println("job2 === job is ${job2 === job}")
                2 -> println("job3 === job is ${job3 === job}")
            }
        }
        // 这里大概会挂起5秒
        parentJob.join()
        logX("Process end!")
    }
}

fun b2T5(){
    runBlocking {
        suspend fun getResult1() : String{
            // 模拟耗时操作
            delay(1000)
            return "Result1"
        }
        suspend fun getResult2() : String{
            // 模拟耗时操作
            delay(1000)
            return "Result2"
        }
        suspend fun getResult3() : String{
            // 模拟耗时操作
            delay(1000)
            return "Result3"
        }

        val results :List<String>

        val time = measureTimeMillis {
            val result1 = async {
                getResult1()
            }
            val result2 = async {
                getResult2()
            }
            val result3 = async {
                getResult3()
            }
            results = listOf(result1.await(), result2.await(), result3.await())
        }
        println("Time: $time")
        println(results)
    }
}

fun b2T6(){
    runBlocking {
        val scope = CoroutineScope(Job())
        scope.launch {
            logX("First start")
            delay(1000)
            logX("First end")
        }
        scope.launch {
            logX("2 start")
            delay(1000)
            logX("2 end")
        }
        scope.launch {
            logX("3 start")
            delay(1000)
            logX("3 end")
        }
        delay(500)
        scope.cancel()
        delay(1000)
    }
}

fun b2T7(){
    runBlocking {
        val myExceptionHandler = CoroutineExceptionHandler{ _,throwable ->
            println("Cache exception : $throwable")
        }
        val scope = CoroutineScope(Job())
//        scope.launch(CoroutineName("kaka")){
//
//        }
        scope.launch(myExceptionHandler){

        }

    }
}

/**
 * 这里是定义了一个Job扩展函数
 * 打印Job的状态信息
 */
fun Job.log() {
    logX(
        """
        isActive = $isActive
        isCancelled = $isCancelled
        isCompleted = $isCompleted
    """.trimIndent()
    )
}

/**
 * 控制台输出带协程信息的log
 */
fun logX(any: Any?) {
    println(
        """
================================
$any
Thread:${Thread.currentThread().name}
================================""".trimIndent()
    )
}

























