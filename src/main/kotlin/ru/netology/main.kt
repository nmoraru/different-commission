package ru.netology

private const val VK_PAY = "VK PAY"
private const val MASTERCARD = "MASTERCARD"
private const val MAESTRO = "MAESTRO"
private const val VISA = "VISA"
private const val MIR = "MIR"

private const val PERCENT_TAX_VISA_AND_MIR = 0.0075
private const val MIN_TAX_VISA_AND_MIR = 35_00.00

private const val MAX_FREE_LIMIT_TO_MASTERCARD_AND_MAESTRO = 75_000_00.00
private const val PERCENT_TAX_MASTERCARD_AND_MAESTRO = 0.006
private const val STANDARD_TAX_MASTERCARD_AND_MAESTRO = 20_00.00

fun main() {
    println("Тест комиссии для VK PAY: ${commissionCalculation(oldAmount = 100_000_00, amount = 100_000_00)} копеек")
    println("Тест комиссии для MASTERCARD до превышения лимита: " +
            "${commissionCalculation(amount = 10_000_00, cardType = MASTERCARD)} копеек")
    println("Тест комиссии для MAESTRO при превышении лимита: " +
            "${commissionCalculation(oldAmount = 50_000_00, amount = 30_000_00, cardType = MAESTRO)} копеек")
    println("Тест минимальной комиссии для VISA: " +
            "${commissionCalculation(amount = 100_00, cardType = VISA)} копеек")
    println("Тест стандартной комиссии для MIR: " +
            "${commissionCalculation(amount = 100_000_00, cardType = MIR)} копеек")
}

fun commissionCalculation(cardType: String = VK_PAY, oldAmount: Int = 0, amount: Int): Double {
    return when (cardType) {
        MASTERCARD, MAESTRO -> calculateComissionToMastercardAndMaestro(oldAmount, amount)
        VISA, MIR -> calculateComissionToVisaAndMir(amount)
        else -> 0.0
    }
}

fun calculateComissionToVisaAndMir(amount: Int): Double {
    return if (amount * PERCENT_TAX_VISA_AND_MIR <= MIN_TAX_VISA_AND_MIR)
        MIN_TAX_VISA_AND_MIR else amount * PERCENT_TAX_VISA_AND_MIR
}

fun calculateComissionToMastercardAndMaestro(oldAmount: Int, amount: Int): Double {
    return when {
        oldAmount + amount <= MAX_FREE_LIMIT_TO_MASTERCARD_AND_MAESTRO -> 0.0
        else -> (oldAmount + amount - MAX_FREE_LIMIT_TO_MASTERCARD_AND_MAESTRO) *
                PERCENT_TAX_MASTERCARD_AND_MAESTRO + STANDARD_TAX_MASTERCARD_AND_MAESTRO
    }
}
