package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    val lottoMachine = LottoMachine()

    @ParameterizedTest
    @ValueSource(strings = ["1500", "500", "천원"])
    fun `유효하지 않은 구입 금액 입력 시 예외 발생`(input: String) {
        assertThrows<IllegalArgumentException> {
            lottoMachine.validatePurchaseAmount(input.toInt())
        }
    }
}
