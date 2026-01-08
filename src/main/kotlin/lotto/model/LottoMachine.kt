package lotto.model

import camp.nextstep.edu.missionutils.Randoms

class LottoMachine {
    fun validatePurchaseAmount(amount: Int): Int {
        require(amount >= 1000) { "[ERROR] 구입 금액은 1,000원 이상이어야 합니다." }
        require(amount % 1000 == 0) { "[ERROR] 구입 금액은 1,000원 단위여야 합니다." }

        return amount
    }

    fun purchaseLotto(amount: Int): List<Lotto> {
        validatePurchaseAmount(amount)
        val count = amount / 1000

        return List(count) {
            drawLotto()
        }
    }

    private fun drawLotto(): Lotto {
        val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
        return Lotto(numbers.sorted())
    }
}
