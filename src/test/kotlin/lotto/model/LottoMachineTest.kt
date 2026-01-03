package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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

    @Test
    fun `확인된 금액을 넣으면 로또 구매 과정에서 예외가 발생하지 않는다`() {
        assertDoesNotThrow {
            lottoMachine.purchaseLotto(2000)
        }
    }

    @Test
    fun `당첨 번호를 입력하면 로또로 반환된다`() {
        // given
        val input = "1,2,3,4,5,6"
        val expected = Lotto(listOf(1,2,3,4,5,6)).getNumbers()

        // when
        val actual = lottoMachine.extractWinningNumber(input).getNumbers()

        // then
        assertEquals(expected, actual)
    }
}
