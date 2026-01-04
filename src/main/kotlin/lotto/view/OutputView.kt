package lotto.view

class OutputView {
    fun guidePurchaseAmount() = println("구입금액을 입력해 주세요.")
    fun guidePurchaseCompleted(count: Int) = println("${count}개를 구매했습니다.")

    fun printLottoNumber(numbers: List<Int>) {
        println(numbers.joinToString(", ", "[", "]"))
    }

    fun guideWinningNumbers() = println("당첨 번호를 입력해 주세요.")
    fun guideBonusNumber() = println("보너스 번호를 입력해 주세요.")
    fun guideStatistics() = println("당첨 통계\n---")
    fun printEarningRate(earningRate: Double) = println("총 수익률은 ${String.format("%.1f", earningRate)}%입니다.")
}
