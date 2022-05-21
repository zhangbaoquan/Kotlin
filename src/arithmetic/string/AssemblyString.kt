package arithmetic.string

import java.util.*
import kotlin.collections.HashSet


/**
 * @author coffer
 * @Description :
 *
 * 输入一个字符串，打印出该字符串中字符的所有排列
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 *
 * @date 2021/3/31 4:10 下午
 */
fun main() {
    val data = permutation("abc")
    for (element in data){
        println(element)
    }
}

lateinit var c: CharArray
var list: MutableList<String> = LinkedList()

fun permutation(str: String): Array<String> {
    c = str.toCharArray()
    dfs(0)
    return list.toTypedArray()
}


/**
 * 对于一个长度为n的字符串（假设字符互不重复），其排列方案数共有:n * (n-1) * (n-2).... * 2 * 1
 * 根据字符串排列的特点，考虑深度优先搜索所有排列方案。即通过字符交换，先固定第1位字符（n 种情况）
 * 再固定第2位字符（n-1种情况）、... 、最后固定第n位字符（1种情况）。
 *
 * 时间复杂度：O(N ! N)
 * 空间复杂度：O(N方)：全排列的递归深度为N，递归中辅助 Set 累计存储的字符数量最多为N，总共 N*N
 */
fun dfs(x: Int) {
    if (x == c.size - 1) {
        list.add(String(c))
        return
    }
    val set = HashSet<Char>()
    // until 是半开区间，相当于 c.size - 1
    for (i in x until c.size) {
        if (set.contains(c[i])){
            // 去除重复，剪枝
            continue
        }
        set.add(c[i])
        // 交换，将 c[i] 固定在第 x 位
        swap(i,x)
        // 开启固定第 x + 1 位字符
        dfs(x+1)
        // 恢复交换
        swap(i,x)
    }

}

fun swap(a: Int, b: Int) {
    val tmp = c[a]
    c[a] = c[b]
    c[b] = tmp
}


