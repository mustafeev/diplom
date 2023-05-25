package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.OptionSelection;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.System.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MakingPaymentTest {
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @BeforeEach
    void setUp() {
        String appUrl = getProperty("app.url");
        open(appUrl);
    }

    @BeforeEach
    public void cleanData() {
        SQLHelper.cleanDatabase();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Test-1 Should check approved debit card payment with valid data")
    void shouldSuccessWithApprovedDebitCardWithValidData() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.fillingByValidDataWithApprovedCard();
        makingPayment.checkPaymentSuccess();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        var paymentAmount = SQLHelper.getPaymentAmount();
        assertEquals("APPROVED", statusForPaymentByDebitCard);
        assertEquals(4500000, paymentAmount);
    }

    @Test
    @DisplayName("Test-2 Should check approved credit card payment with valid data")
    void shouldApproveWithValidCreditCardData() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.fillingByValidDataWithApprovedCard();
        makingPayment.checkPaymentSuccess();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        var paymentAmount = SQLHelper.getPaymentAmount();
        assertEquals("APPROVED", statusForPaymentByCreditCard);
        assertEquals(4500000, paymentAmount);
    }

    @Test
    @DisplayName("Test-3 Should check declined debit card payment with valid data")
    void shouldDeclineWithValidDebitCardData() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.fillingByValidDataWithDeclinedCard();
        makingPayment.checkIfPaymentNotSuccessful();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        var paymentAmount = SQLHelper.getPaymentAmount();
        assertEquals("DECLINED", statusForPaymentByDebitCard);
        assertEquals(4500000, paymentAmount);


    }

    @Test
    @DisplayName("Test-4 Should check declined credit card payment with valid data")
    void shouldDeclineWithValidCreditCardData() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.fillingByValidDataWithDeclinedCard();
        makingPayment.checkIfPaymentNotSuccessful();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        var paymentAmount = SQLHelper.getPaymentAmount();
        assertEquals("DECLINED", statusForPaymentByCreditCard);
        assertEquals(4500000, paymentAmount);
    }

    @Test
    @DisplayName("Test-5 Should throw error notification because of null data in debit payment")
    void shouldGetErrorWithNullDataByDebit() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.fillingAllFieldsWithNull();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-6 Should throw error notification because of null data in credit payment")
    void shouldGetErrorWithNullDataByCredit() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.fillingAllFieldsWithNull();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-7 Should throw error if card number all of zeros")
    void shouldGetErrorWithAllZeroCardNumber() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardNumberAllZero();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-7 Should throw error if card number consist of fifteen numbers")
    void shouldGetErrorWithFifteenNumbersCard() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardNumberWithFifteenNumbers();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-8 Should throw error if card number consist of seventeen numbers")
    void shouldGetErrorWithSeventeenNumbersCard() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingValidDataButInvalidCardNumberWithSeventeenSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-9 Should throw error if card number is null")
    void shouldGetErrorWithNullCardNumber() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingValidDataButCardNumberIsNull();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-10 Should throw error if card number is symbols")
    void shouldGetErrorWithCardNumberOfSymbols() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardNumberWithSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-11 Should throw error if incorrect card month")
    void shouldGetErrorWithIncorrectCardMonth() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButIncorrectMonth();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-12 Should throw error if card month is symbol")
    void shouldGetErrorWithCardMonthIsSymbol() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButMonthIsSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-13 Should throw error if card month is one number")
    void shouldGetErrorWithCardMonthIsOneNumber() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButMonthIsOneNumber();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-14 Should throw error if card month is null")
    void shouldGetErrorWithCardMonthIsNull() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButMonthIsNull();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-14 Should throw error if card month is two zeros")
    void shouldGetErrorWithCardMonthIsTwoZeros() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButMonthIsOfZeros();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-15 Should throw error if card year is three zeros")
    void shouldGetErrorWithCardYearIsThreeZeros() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsOfZeros();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-16 Should throw error if card year is one number")
    void shouldGetErrorWithCardYearIsOneNumber() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsOneNumber();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-17 Should throw error if card year is empty")
    void shouldGetErrorWithCardYearIsEmpty() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsEmpty();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-18 Should throw error if card year is incorrect")
    void shouldGetErrorWithCardYearIsIncorrect() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsIncorrect();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-19 Should throw error if card year is symbols")
    void shouldGetErrorWithCardYearIsSymbols() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearWithSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-20 Should throw error if card year is expired")
    void shouldGetErrorWithCardYearIsExpired() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsExpired();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-21 Should throw error if card owner is empty")
    void shouldGetErrorWithCardOwnerIsEmpty() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardOwnerIsEmpty();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-22 Should throw error if card owner is symbol")
    void shouldGetErrorWithCardOwnerIsSymbol() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardOwnerIsSymbol();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-23 Should throw error if card owner is number")
    void shouldGetErrorWithCardOwnerIsNumber() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardOwnerIsNumber();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-24 Should throw error if card owner is cyrillic")
    void shouldGetErrorWithCardOwnerIsCyrillic() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardOwnerIsCyrillic();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-25 Should throw error if card cvc is empty")
    void shouldGetErrorWithCardCvcIsEmpty() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCvcIsEmpty();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }


    @Test
    @DisplayName("Test-26 Should throw error if card cvc is two numbers")
    void shouldGetErrorWithCardCvcIsTwoNumbers() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCvcIsOfTwoNumbers();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-27 Should throw error if card cvc is three zeros")
    void shouldGetErrorWithCardCvcIsThreeZeros() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCvcIsOfZeros();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-28 Should throw error if card cvc is symbol")
    void shouldGetErrorWithCardCvcIsSymbol() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCvcIsSymbol();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }
}
