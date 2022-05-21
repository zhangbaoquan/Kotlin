package arithmetic.string

/**
 * @author coffer
 * @Description : 两个字符串相加
 * 例 ：
 * s1 = "51189"，s2 = "967895"  ，和为"1019084"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings/solution/add-strings-shuang-zhi-zhen-fa-by-jyd/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/12/12 2:14 下午
 */

fun main() {
    println("两个字符串相加的和 ： ${addString("9923", "4567")}")
}

fun addString(str1: String, str2: String): String {
    val sb: StringBuilder = StringBuilder()
    var i = str1.length - 1
    var j = str2.length - 1
    // 进位标识
    var carry = 0
    while (i >= 0 || j >= 0) {
        val a1 = if (i >= 0) str1[i] - '0' else 0
        val a2 = if (j >= 0) str2[j] - '0' else 0
        val tmp = a1 + a2 + carry
        // 取出十位
        carry = tmp / 10
        // 取出个位
        sb.append(tmp % 10)
        i--
        j--
    }
    // 将进位拼接上，然后翻转
    if (carry == 1) sb.append(carry)
    return sb.reverse().toString()
}