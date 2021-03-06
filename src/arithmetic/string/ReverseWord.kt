package arithmetic.string

/**
 * @author coffer
 * @Description : 翻转单词顺序
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出".student a am I"。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * <p>
 * 示例 2：
 * <p>
 * 输入: " hello world! "
 * 输出:"world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 示例 3：
 * <p>
 * 输入: "a good  example"
 * 输出:"example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/12/12 2:14 下午
 */
fun main() {
    reverseWords(" hello world !")
}

/**
 * 使用双指针法：
 * 倒序遍历字符串，记录单词左右索引边界的是pre ，last。
 * 确定每一个单词的边界，将其添加到单词的列表res中
 *
 * 时间复杂度：O(N)，N是字符串的长度，线性遍历
 * 空间复杂度：O(N)，是StringBuilder 占用的字符串长度所用的空间大小
 *
 * @param s 目标字符串
 * @return 结果
 */
fun reverseWords(str: String) {
    val s = str.trim()
    var last = s.length - 1
    var pre = last
    var sb = StringBuilder()
    while (pre >= 0) {
        while (pre >= 0 && s[pre] != ' ') {
            pre--
        }
        sb.append(s.substring(pre + 1, last + 1)).append(' ')
        // 将空格过滤掉
        while (pre >= 0 && s[pre] == ' ') {
            pre--
        }
        // 指向下个单词
        last = pre
    }
    println(sb.toString())

}