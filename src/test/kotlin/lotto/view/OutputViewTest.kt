package lotto.view

import org.assertj.core.api.Assertions.assertThat
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
        assertThat(actual).isEqualTo(expected)
    }
}
