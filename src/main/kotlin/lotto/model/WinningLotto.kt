package lotto.model

data class WinningLotto(val winningNumbers: Lotto, val bonusNumber: Int) {
    init {
        winningNumbers.getNumbers().forEach { num ->
            require(num != bonusNumber) { "[ERROR] 보너스번호가 당첨번호와 중복됩니다." }
        }
        require(bonusNumber in 1..45) { "[ERROR] 보너스번호가 1 ~ 45 범위를 벗어납니다." }
    }
}
