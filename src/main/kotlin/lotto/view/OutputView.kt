package lotto.view

class OutputView {
    fun guidePurchaseAmount() = println("구입금액을 입력해 주세요.")
    fun guidePurchaseCompleted(count: Int) = println("${count}개를 구매했습니다.")

    fun printLottoNumber(numbers: List<Int>) {
        println(numbers.joinToString(", ", "[", "]"))
    }
}
