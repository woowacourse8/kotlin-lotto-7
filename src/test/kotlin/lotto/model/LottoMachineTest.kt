package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMachineTest {
    val lottoMachine = LottoMachine()

    @Test
    fun `구입 금액이 1,000원 단위가 아닐 때 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            lottoMachine.checkPurchaseAmount("1500")
        }
    }

    @Test
    fun `구입 금액이 1,000원 이하일 때 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            lottoMachine.checkPurchaseAmount("500")
        }
    }
}
