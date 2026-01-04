package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.Statistics
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class Controller {
    private val inputView = InputView()
    private val outputView = OutputView()
    private val lottoMachine = LottoMachine()

    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottos = buyLottos(purchaseAmount)

        val winningLotto = getWinningLotto()

        showStatistics(lottos, winningLotto, purchaseAmount)
    }

    // --- 입력 로직 ---

    private fun getPurchaseAmount(): Int {
        return retryUntilValid {
            outputView.guidePurchaseAmount()
            val input = inputView.input()
            val amount = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.")
            lottoMachine.validatePurchaseAmount(amount)
        }
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = retryUntilValid {
            outputView.guideWinningNumbers()
            val input = inputView.input()
            parseWinningNumbers(input)
        }

        return retryUntilValid {
            outputView.guideBonusNumber()
            val input = inputView.input()
            val bonusNumber = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.")
            WinningLotto(winningNumbers, bonusNumber)
        }
    }

    // --- 비즈니스 흐름 ---
    private fun buyLottos(amount: Int): List<Lotto> {
        val count = amount / 1000
        outputView.guidePurchaseCompleted(count)

        val lottos = lottoMachine.purchaseLotto(amount)
        lottos.forEach { lotto ->
            outputView.printLottoNumber(lotto.getNumbers())
        }
        return lottos
    }

    private fun showStatistics(
        lottos: List<Lotto>,
        winningLotto: WinningLotto,
        amount: Int
    ) {
        val statistics = Statistics(lottos, winningLotto)
        val result = statistics.getResult()
        val earningRate = statistics.getEarningRate(amount, result)

        outputView.printStatistics(result, earningRate)
    }

    // --- 유틸리티 ---
    private fun parseWinningNumbers(input: String): Lotto {
        val numbers = input.split(",").map {
            it.trim().toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 숫자가 아닌 값이 포함되어 있습니다.")
        }
        return Lotto(numbers)
    }

    private fun <T> retryUntilValid(action: () -> T): T {
        while (true) {
            try {
                return action()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
