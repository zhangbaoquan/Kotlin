package arithmetic.string


/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 * <p>
 * 限制：
 * <p>
 * 0 <= s 的长度 <= 10000
 */
fun main() {
    replaceSpace("We are happy.")
}

/**
 * 替换字符串里的字符。
 * 注意：由于每次替换从 1 个字符变成 3 个字符，所以数组长度需要是字符串的3倍
 * 时间复杂度是 循环了一遍字符串，因此是O(n)，空间复杂度是原来三倍的空间 即O(n)
 * @param s 字符串
 */
fun replaceSpace(str: String) {
    val len = str.length
    var ch = CharArray(len * 3)
    var index = 0
    for (i in 0 until len ){
        if (str[i] != ' '){
            ch[index++] = str[i]
        }else{
            ch[index++] = '%'
            ch[index++] = '2'
            ch[index++] = '0'
        }
    }
    println(String(ch,0,index))
}