package lotto.model

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "[ERROR] 로또 번호는 6개여야 합니다." }
        require(numbers.size == numbers.distinct().size) { "[ERROR] 로또 번호는 중복될 수 없습니다." }
        numbers.forEach { n ->
            require(n in 1..45) { "[ERROR] 로또 번호는 1 ~ 45 사이여야 합니다." }
        }
    }

    fun getNumbers(): List<Int> = numbers
}