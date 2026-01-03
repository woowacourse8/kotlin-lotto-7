package lotto.model

import camp.nextstep.edu.missionutils.Randoms

class LottoMachine {
    fun purchaseLotto(amount: Int): List<Lotto> {
        val count = amount / 1000
        val lottos = mutableListOf<Lotto>()
        repeat(count) {
            lottos.add(drawLotto())
        }
        return lottos
    }

    fun checkPurchaseAmount(input: String): Int {
        val amount = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 입력받은 금액이 숫자가 아닙니다.")

        require(amount >= 1000) { "[ERROR] 구입 금액이 1,000원 이하입니다." }
        require(amount % 1000 == 0) { "[ERROR] 구입 금액이 1,000원 단위가 아닙니다." }

        return amount
    }

    private fun drawLotto(): Lotto {
        return Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6))
    }
}
