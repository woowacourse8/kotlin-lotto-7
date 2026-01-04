package lotto.model

class Statistics(val lottos: List<Lotto>, val winningLotto: WinningLotto) {
    fun getResult(): List<Int> {
        val results = mutableListOf(0, 0, 0, 0, 0, 0)

        lottos.forEach { lotto ->
            val result = compareLotto(lotto)
            when (result) {
                LottoResult.FIRST -> results[1]++
                LottoResult.SECOND -> results[2]++
                LottoResult.THIRD -> results[3]++
                LottoResult.FOURTH -> results[4]++
                LottoResult.FIFTH -> results[5]++
                else -> results[0]
            }
        }

        return results
    }

    fun getEarningRate(amount: Int, results: List<Int>): Double {
        val earning =
            results[1] * LottoResult.FIRST.price + results[2] * LottoResult.SECOND.price + results[3] * LottoResult.THIRD.price + results[4] * LottoResult.FOURTH.price + results[5] * LottoResult.FIFTH.price

        return (earning.toDouble() / amount.toDouble()) * 100
    }

    private fun compareLotto(lotto: Lotto): LottoResult {
        val numbers = lotto.getNumbers()
        val winningNumbers = winningLotto.winningNumbers.getNumbers()
        val bonusNumber = winningLotto.bonusNumber

        var matchCount = 0
        var matchBonusNumber = false

        numbers.forEach { n ->
            if (winningNumbers.contains(n)) matchCount++
        }

        if (numbers.contains(bonusNumber)) matchBonusNumber = true

        return matchResult(matchCount, matchBonusNumber)
    }

    private fun matchResult(matchCount: Int, matchBonusNumber: Boolean): LottoResult {
        var lottoResult = LottoResult.entries.find { result ->
            matchCount == result.matchCount
        } ?: LottoResult.NONE

        if (matchCount == 5 && matchBonusNumber) lottoResult = LottoResult.SECOND

        return lottoResult
    }

    enum class LottoResult(
        val matchCount: Int, val price: Int, private val comment: String
    ) {
        FIRST(6, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
        SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
        THIRD(5, 1_500_000, "5개 일치 (1,500,000원)"),
        FOURTH(4, 50_000, "4개 일치 (50,000원)"),
        FIFTH(3, 5_000, "3개 일치 (5,000원)"),
        NONE(0, 0, "");

        fun getResultComment(count: Int) = "${this.comment} - ${count}개"
    }
}
