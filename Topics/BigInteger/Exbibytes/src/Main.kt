import java.math.BigInteger

fun main() {
    // write your code here
    val exbibyte = readln().toBigInteger()
    val equivalent = BigInteger("9223372036854775808")
    println(exbibyte * equivalent)
}