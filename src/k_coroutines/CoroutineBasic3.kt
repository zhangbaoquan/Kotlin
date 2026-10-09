package k_coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * author       : coffer
 * date         : 2024/10/13
 * description  :
 */

fun main() {

//    b3T1()
//    b3T2()
//    b3T3()
//    b3T4()
    b3T5()
}

fun b3T1(){
    runBlocking {
        // 1、创建管道。
        val channel = Channel<Int>()
        launch {
            // 在单独的协程中发送管道数据
            (1..3).forEach {
                // 挂起函数
                channel.send(it)
                logX("Send: $it")
            }
            channel.close()
        }
        launch {
            // 在单独的协程中接收管道消息
            for (i in channel){
                // 挂起函数
                logX("Receive: $i")
            }
        }
        logX("end")
        // 上面的结果是发送和接收交替执行
    }

}

fun b3T2(){
    runBlocking {
        // 1、创建管道。capacity 代表容量，UNLIMITED 是无限容量
        val channel = Channel<Int>(capacity = Channel.Factory.UNLIMITED)
        launch {
            // 在单独的协程中发送管道数据
            (1..3).forEach {
                // 挂起函数
                channel.send(it)
                logX("Send: $it")
            }
            channel.close()
        }
        launch {
            // 在单独的协程中接收管道消息
            for (i in channel){
                // 挂起函数
                logX("Receive: $i")
            }
        }
        logX("end")
        // 对于发送方来说，由于 Channel 的容量是无限大的，所以发送方可以一直往管道当中塞入数据，等数据都塞完以后，接收方才开始接收。
    }
}

fun b3T3(){
    runBlocking {
        // 1、创建管道。CONFLATED 代表了容量为 1，新的数据会替代旧的数据
        val channel = Channel<Int>(capacity = Channel.Factory.CONFLATED)
        launch {
            // 在单独的协程中发送管道数据
            (1..3).forEach {
                // 挂起函数
                channel.send(it)
                logX("Send: $it")
            }
            channel.close()
        }
        launch {
            // 在单独的协程中接收管道消息
            for (i in channel){
                // 挂起函数
                logX("Receive: $i")
            }
        }
        logX("end")
        // 当设置 capacity = CONFLATED 的时候，发送方也会一直发送数据，而且，对于接收方来说，它永远只能接收到最后一条数据。
    }


}

fun b3T4(){
    runBlocking {
        // 1、创建管道。CONFLATED 代表了容量为 1，新的数据会替代旧的数据
        val channel = Channel<Int>(capacity = Channel.Factory.BUFFERED)
        launch {
            // 在单独的协程中发送管道数据
            (1..3).forEach {
                // 挂起函数
                channel.send(it)
                logX("Send: $it")
            }
            channel.close()
        }
        launch {
            // 在单独的协程中接收管道消息
            for (i in channel){
                // 挂起函数
                logX("Receive: $i")
            }
        }
        logX("end")
        // 当设置 capacity = CONFLATED 的时候，发送方也会一直发送数据，而且，对于接收方来说，它永远只能接收到最后一条数据。
    }


}

fun b3T5(){
    runBlocking {
        val channel : ReceiveChannel<Int> = produce {

        }
    }

}























