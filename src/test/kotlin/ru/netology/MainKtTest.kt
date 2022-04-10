package ru.netology


import junit.framework.TestCase.assertEquals
import org.junit.Test

class MainKtTest {


    @Test
    fun calculateMinimalCommissionToVisa() {
        val cardType = "VISA"
        val oldAmount = 0
        val amount = 100_00
        val expectedCommission = 3500.0

        val actualCommission = commissionCalculation(cardType, oldAmount, amount)

        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun calculateStandardCommissionToMir() {
        val cardType = "MIR"
        val oldAmount = 0
        val amount = 100_000_00
        val expectedCommission = 75000.0

        val actualCommission = commissionCalculation(cardType, oldAmount, amount)

        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun calculateCommissionToVkPay() {
        val cardType = "VK_PAY"
        val oldAmount = 100_000_00
        val amount = 100_000_00
        val expectedCommission = 0.0

        val actualCommission = commissionCalculation(cardType, oldAmount, amount)

        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun calculateCommissionToMastercardBeforeExceedingTheLimit() {
        val cardType = "MASTERCARD"
        val oldAmount = 0
        val amount = 10_000_00
        val expectedCommission = 0.0

        val actualCommission = commissionCalculation(cardType, oldAmount, amount)

        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun calculateCommissionToMaestroAfterExceedingTheLimit() {
        val cardType = "MAESTRO"
        val oldAmount = 50_000_00
        val amount = 30_000_00
        val expectedCommission = 5000.0

        val actualCommission = commissionCalculation(cardType, oldAmount, amount)

        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun calculateCommissionWithCardTypeDefault() {
        val oldAmount = 50_000_00
        val amount = 30_000_00
        val expectedCommission = 0.0

        val actualCommission = commissionCalculation(oldAmount = oldAmount, amount = amount)

        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun calculateCommissionWithOldAmountDefault() {
        val cardType = "MAESTRO"
        val amount = 30_000_00
        val expectedCommission = 0.0

        val actualCommission = commissionCalculation(cardType = cardType, amount = amount)

        assertEquals(expectedCommission, actualCommission)
    }

}
