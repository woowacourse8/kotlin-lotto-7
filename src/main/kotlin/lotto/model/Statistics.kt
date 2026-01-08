package lotto.model

import java.util.*

class Statistics(
    private val lottos: List<Lotto>,
    private val winningLotto: WinningLotto
) {
    fun getResult(): Map<LottoResult, Int> {
        val result = EnumMap<LottoResult, Int>(LottoResult::class.java)
        LottoResult.entries.forEach { result[it] = 0 }

        lottos.forEach { lotto ->
            val rank = compareLotto(lotto)
            result[rank] = result.getOrDefault(rank, 0) + 1
        }
        return result
    }

    fun getEarningRate(purchaseAmount: Int, result: Map<LottoResult, Int>): Double {
        val totalPrize = result.entries.sumOf { (rank, count) ->
            rank.price.toLong() * count
        }
        return (totalPrize.toDouble() / purchaseAmount) * 100
    }

    private fun compareLotto(lotto: Lotto): LottoResult {
        val matchCount = lotto.getNumbers().count { winningLotto.winningNumbers.getNumbers().contains(it) }
        val matchBonus = lotto.getNumbers().contains(winningLotto.bonusNumber)

        return LottoResult.valueOf(matchCount, matchBonus)
    }
}
