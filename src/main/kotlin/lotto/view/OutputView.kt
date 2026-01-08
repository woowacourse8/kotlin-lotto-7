package lotto.view

import lotto.model.LottoResult

class OutputView {
    fun guidePurchaseAmount() = println("구입금액을 입력해 주세요.")
    fun guidePurchaseCompleted(count: Int) {
        println()
        println("${count}개를 구매했습니다.")
    }

    fun printLottoNumber(numbers: List<Int>) {
        println(numbers.joinToString(", ", "[", "]"))
    }

    fun guideWinningNumbers() {
        println()
        println("당첨 번호를 입력해 주세요.")
    }

    fun guideBonusNumber() {
        println()
        println("보너스 번호를 입력해 주세요.")
    }

    fun printStatistics(result: Map<LottoResult, Int>, earningRate: Double) {
        println()
        println("당첨 통계")
        println("---")

        val ranks = listOf(LottoResult.FIFTH, LottoResult.FOURTH, LottoResult.THIRD, LottoResult.SECOND, LottoResult.FIRST)

        ranks.forEach { rank ->
            val count = result[rank] ?: 0
            println(rank.getResultComment(count))
        }

        println("총 수익률은 ${String.format("%.1f", earningRate)}%입니다.")
    }
}
