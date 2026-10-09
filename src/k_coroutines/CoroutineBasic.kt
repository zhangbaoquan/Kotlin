package k_coroutines

import kotlinx.coroutines.*


/**
 * author       : coffer
 * date         : 2024/10/13
 * description  :
 *  一、launch 启动协程 知识点笔记：
 *  1、协程一旦被 launch，那么它当中执行的任务也不会被中途改变。
 *  2、launch 的协程任务一旦完成了，即使有了结果，也没办法直接返回给调用方。
 *  3、因为launch这个函数的返回值是一个 Job，它其实代表的是协程的句柄（Handle），它并不能为我们返回协程的执行结果。
 *  4、CoroutineScope.launch()，代表了 launch 其实是一个扩展函数，而它的“扩展接收者类型”是 CoroutineScope。
 *  这就意味着，我们的 launch() 会等价于 CoroutineScope 的成员方法。
 *  5、CoroutineContext，它代表了我们协程的上下文，
 *  它的默认值是 EmptyCoroutineContext，如果我们不传这个参数，默认就会使用 EmptyCoroutineContext。
 *  6、CoroutineStart，它代表了协程的启动模式。如果我们不传这个参数，它会默认使用 CoroutineStart.DEFAULT。
 *  CoroutineStart 其实是一个枚举类，一共有：DEFAULT、LAZY、ATOMIC、UNDISPATCHED。
 *  我们最常使用的就是 DEFAULT、LAZY，它们分别代表：立即执行、懒加载执行。
 *
 *  二、runBlocking 启动协程 知识点笔记
 *  1、使用 runBlocking 启动的协程会阻塞当前线程的执行，这样一来，所有的代码就变成了顺序执行
 *  2、runBlocking 确实会阻塞当前线程的执行。
 *  对于这一点，Kotlin 官方也强调了：runBlocking 只推荐用于连接线程与协程，
 *  并且，大部分情况下，都只应该用于编写 Demo 或是测试代码。
 *  3、请不要在生产环境当中使用 runBlocking。
 *  4、runBlocking 就是一个普通的顶层函数，它并不是 CoroutineScope 的扩展函数
 *  5、runBlocking 其实是可以从协程当中返回执行结果的
 *
 *  三、使用async 启动协程 知识点笔记
 * 1、使用 async{} 创建协程，并且还能通过它返回的句柄拿到协程的执行结果
 * 2、async 启动协程以后，它也不会阻塞当前程序的执行流程
 * 2、async{}的返回值，它是一个 Deferred 对象，我们通过调用它的 await() 方法，就可以拿到协程的执行结果。
 *
 * launch 和 async 的两个不同点，
 * 一个是 block 的函数类型，前者的返回值类型是 Unit，后者则是泛型 T；
 * 另外一个不同点在返回值上，前者返回值类型是 Job，后者返回值类型是 Deferred。而 async 可以返回协程执行结果的原因也在于此。
 */

fun main() {
    // 创建协程方式一：使用launch
//    launchTest()
    // 创建协程方式二：使用runBlocking
//    runBlockingTest()
    // 创建协程方式三：使用async
    asyncTestV2()

}

// 创建协程方式一：使用launch
fun launchTest() {
    // 1、GlobalScope.launch{}，它是一个高阶函数，它的作用就是启动一个协程。GlobalScope 是 Kotlin 官方为我们提供的“协程作用域”
    GlobalScope.launch(Dispatchers.IO) {
        // 下面这个会输出 ： Coroutine started: DefaultDispatcher-worker-2 @coroutine#1，
        // 其中 DefaultDispatcher-worker-2 代表的线程名称 ，@coroutine#1 代表了launch创建的协程
        println("Coroutine started: ${Thread.currentThread().name}")
        // 2、从 delay() 的函数签名这里可以发现，它多了一个“suspend”关键字，
        // 这代表了它是一个挂起函数。而这也就意味着，delay 将会拥有“挂起和恢复”的能力。
        delay(1000L)
        println("Hello World!")
    }
    println("After launch: ${Thread.currentThread().name}")
    // 3、Thread.sleep(2000) 的作用了，其实，它就是为了不让我们的主线程退出。
    // 如果没有这句，上面的协程代码也不会被执行
    Thread.sleep(2000L)
    println("Process End")

    // 上面代码的执行顺序是
    // After launch: main
    // Coroutine started: DefaultDispatcher-worker-1 @coroutine#1
    // Hello World!
    // Process End
}

// 创建协程方式二：使用runBlocking
fun runBlockingTest() {
    // 使用 runBlocking 启动的协程会阻塞当前线程的执行，这样一来，所有的代码就变成了顺序执行
    runBlocking {
        println("Coroutine started: ${Thread.currentThread().name}")
        delay(1000L)
        println("Hello World!")
    }
    println("After launch: ${Thread.currentThread().name}")
    Thread.sleep(2000L)
    println("Process End")

    // 上面代码的执行顺序是
    // Coroutine started: main @coroutine#1
    // Hello World!
    // After launch: main
    // Process End
}

fun runBlockingTestV2() {
    runBlocking {
        println("First:${Thread.currentThread().name}")
        delay(1000L)
        println("Hello First!")
    }
    runBlocking {
        println("Second:${Thread.currentThread().name}")
        delay(1000L)
        println("Hello Second!")
    }
    runBlocking {
        println("Third:${Thread.currentThread().name}")
        delay(1000L)
        println("Hello Third!")
    }
    // 删掉了 Thread.sleep
    println("Process end!")
    // 我们调用三次 runBlocking，对应地，程序就启动了三个协程。
/*
输出结果：
First:main @coroutine#1
Hello First!
Second:main @coroutine#2
Hello Second!
Third:main @coroutine#3
Hello Third!
Process end!
*/

}

// 创建协程方式三：使用async
fun asyncTest(){
    runBlocking {
        println("In runBlocking:${Thread.currentThread().name}")
        val deferred: Deferred<String> = async {
            println("In async:${Thread.currentThread().name}")
            delay(1000L) // 模拟耗时操作
            return@async "Task Completed!"
        }
        println("After async:${Thread.currentThread().name}")
        val result = deferred.await()
        println("Result is: $result")
    }
    // 上面的代码启动了两个协程 @coroutine#1 和 @coroutine#2
    // In runBlocking:main @coroutine#1
    // After async:main @coroutine#1
    // In async:main @coroutine#2
    // Result is: Task Completed!
}

fun asyncTestV2(){
    runBlocking {
        val deferred: Deferred<String> = async {
            println("In async:${Thread.currentThread().name}")
            delay(1000L) // 模拟耗时操作
            println("In async after delay!")
            return@async "Task completed!"
        }

        // 不再调用 deferred.await()
        delay(2000L)
    }
}

//
suspend fun requestNetTest(){
    println("挂起函数")
}




















