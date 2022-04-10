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

}

fun commissionCalculation(cardType: String = VK_PAY, oldAmount: Int = 0, amount: Int): Double {
    return when (cardType) {
        MASTERCARD, MAESTRO -> calculateCommissionToMastercardAndMaestro(oldAmount, amount)
        VISA, MIR -> calculateCommissionToVisaAndMir(amount)
        else -> 0.0
    }
}

fun calculateCommissionToVisaAndMir(amount: Int): Double {
    return if (amount * PERCENT_TAX_VISA_AND_MIR <= MIN_TAX_VISA_AND_MIR)
        MIN_TAX_VISA_AND_MIR else amount * PERCENT_TAX_VISA_AND_MIR
}

fun calculateCommissionToMastercardAndMaestro(oldAmount: Int, amount: Int): Double {
    return when {
        oldAmount + amount <= MAX_FREE_LIMIT_TO_MASTERCARD_AND_MAESTRO -> 0.0
        else -> (oldAmount + amount - MAX_FREE_LIMIT_TO_MASTERCARD_AND_MAESTRO) *
                PERCENT_TAX_MASTERCARD_AND_MAESTRO + STANDARD_TAX_MASTERCARD_AND_MAESTRO
    }
}
