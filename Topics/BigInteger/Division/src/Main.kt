fun main() {
    // write your code here
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()

    val (c, d) = a.divideAndRemainder(b)

    println("$a = $b * $c + $d")
}