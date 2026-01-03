package lotto.controller

import lotto.model.*
import lotto.view.*

class Controller {
    val inputView = InputView()
    val outputView = OutputView()
    val lottoMachine = LottoMachine()

    fun run() {
        purchaseLotto()
    }

    private fun purchaseLotto() {
        outputView.guidePurchaseAmount()
        val amount = inputPurchaseAmount()
        val count = amount / 1000
        println()

        outputView.guidePurchaseCompleted(count)
        val lottos = lottoMachine.purchaseLotto(count)
        lottos.forEach { lotto ->
            outputView.printLottoNumber(lotto.getNumbers().sorted())
        }
        println()
    }

    private fun inputPurchaseAmount(): Int {
        try {
            val input = inputView.input()
            return lottoMachine.checkPurchaseAmount(input)
        } catch(e: IllegalArgumentException) {
            println(e.message)
            inputPurchaseAmount()
        }
        return 0
    }
}
