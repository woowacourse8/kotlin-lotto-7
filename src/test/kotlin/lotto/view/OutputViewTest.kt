package lotto.view

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OutputViewTest {
    @Test
    fun `숫자리스트를 주면 로또 내역을 출력`() {
        // given
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val expected = println("[1, 2, 3, 4, 5, 6]")

        // when
        val actual = OutputView().printLottoNumber(numbers)

        // then
        assertEquals(expected, actual)
    }
}
