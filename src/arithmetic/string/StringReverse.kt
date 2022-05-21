package arithmetic.string

/**
 * 反转字符串
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

示例 1：

输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]

示例 2：

输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

总结：字符串的反转和数组的反转，在实现思路上有很多共同之处

时间复杂度：O(n/2)
空间复杂度：O(1)
 */

fun main() {
    val c = charArrayOf('h', 'e', 'l', 'l', 'o', 'a')
    println(String(c))
    reverseString(c)
}

/**
 * 反转方法，使用双指针法
 * @param ch 字符数组
 */
fun reverseString(ch: CharArray) {
    var pre = 0
    var last = ch.size - 1
    var c: Char
    while (pre < last) {
        c = ch[pre]
        ch[pre] = ch[last]
        ch[last] = c
        pre++
        last--
    }
    println(String(ch))
}