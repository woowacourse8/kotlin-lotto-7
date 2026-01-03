package lotto.model

import camp.nextstep.edu.missionutils.Randoms

class LottoMachine {
    fun checkPurchaseAmount(input: String): Int {
        val amount = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 입력받은 금액이 숫자가 아닙니다.")

        require(amount / 1000 >= 1) { "[ERROR] 구입 금액이 1,000원 이하입니다." }
        require(amount % 1000 == 0) { "[ERROR] 구입 금액이 1,000원 단위가 아닙니다." }

        return amount
    }

    fun purchaseLotto(count: Int): List<Lotto> {
        val lottos = mutableListOf<Lotto>()
        repeat(count) {
            lottos.add(drawLotto())
        }
        return lottos
    }

    fun extractWinningNumber(input: String): Lotto {
        val numbers = input.split(",")
            .map { s ->
                s.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 올바른 숫자가 입력되지 않았습니다.")
            }

        return Lotto(numbers)
    }

    private fun drawLotto(): Lotto {
        return Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6))
    }
}
