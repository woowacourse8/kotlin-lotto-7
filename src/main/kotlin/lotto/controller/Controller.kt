package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class Controller {
    val inputView = InputView()
    val outputView = OutputView()
    val lottoMachine = LottoMachine()

    fun run() {
        val lottos = purchaseLotto()
        val winningLotto = enterWinningLotto()
    }

    private fun purchaseLotto(): List<Lotto> {
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

        return lottos
    }

    private fun enterWinningLotto(): WinningLotto {
        outputView.guideWinningNumbers()
        val winningNumbers = inputWinningNumbers()
        println()

        outputView.guideBonusNumber()
        val winningLotto = inputBonusNumber(winningNumbers)
        println()

        return winningLotto
    }

    private fun inputPurchaseAmount(): Int {
        try {
            val input = inputView.input()
            return lottoMachine.checkPurchaseAmount(input)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            inputPurchaseAmount()
        }
        throw IllegalArgumentException("[ERROR] 비정상적인 접근입니다.")
    }

    private fun inputWinningNumbers(): Lotto {
        try {
            val input = inputView.input()
            return lottoMachine.extractWinningNumber(input)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            inputWinningNumbers()
        }
        throw IllegalArgumentException("[ERROR] 비정상적인 접근입니다.")
    }

    private fun inputBonusNumber(winningNumbers: Lotto): WinningLotto {
        try {
            val input = inputView.input()
            val bonusNumber = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 올바른 숫자가 입력되지 않았습니다.")
            return WinningLotto(winningNumbers, bonusNumber)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            inputBonusNumber(winningNumbers)
        }
        throw IllegalArgumentException("[ERROR] 비정상적인 접근입니다.")
    }
}
