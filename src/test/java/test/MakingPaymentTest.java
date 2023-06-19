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
    @DisplayName("Test-7 Should throw error if card number all of zeros in debit payment")
    void shouldGetErrorWithAllZeroCardNumberInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardNumberAllZero();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-7 Should throw error if card number consist of fifteen numbers in debit payment")
    void shouldGetErrorWithFifteenNumbersCardInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardNumberWithFifteenNumbers();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-8 Should throw error if card number consist of seventeen numbers in debit payment")
    void shouldGetErrorWithSeventeenNumbersCardInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingValidDataButInvalidCardNumberWithSeventeenSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-9 Should throw error if card number is null in debit payment")
    void shouldGetErrorWithNullCardNumberInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingValidDataButCardNumberIsNull();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-10 Should throw error if card number is symbols in debit payment")
    void shouldGetErrorWithCardNumberOfSymbolsInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardNumberWithSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-11 Should throw error if incorrect card month in debit payment")
    void shouldGetErrorWithIncorrectCardMonthInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButIncorrectMonth();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-12 Should throw error if card month is symbol in debit payment")
    void shouldGetErrorWithCardMonthIsSymbolInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButMonthIsSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-13 Should throw error if card month is one number in debit payment")
    void shouldGetErrorWithCardMonthIsOneNumberInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButMonthIsOneNumber();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-14 Should throw error if card month is null in debit payment")
    void shouldGetErrorWithCardMonthIsNullInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButMonthIsNull();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-14 Should throw error if card month is two zeros in debit payment")
    void shouldGetErrorWithCardMonthIsTwoZerosInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButMonthIsOfZeros();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-15 Should throw error if card year is three zeros in debit payment")
    void shouldGetErrorWithCardYearIsThreeZerosInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsOfZeros();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-16 Should throw error if card year is one number in debit payment")
    void shouldGetErrorWithCardYearIsOneNumberInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsOneNumber();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-17 Should throw error if card year is empty in debit payment")
    void shouldGetErrorWithCardYearIsEmptyInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsEmpty();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-18 Should throw error if card year is incorrect in debit payment")
    void shouldGetErrorWithCardYearIsIncorrectInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsIncorrect();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-19 Should throw error if card year is symbols in debit payment")
    void shouldGetErrorWithCardYearIsSymbolsInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearWithSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-20 Should throw error if card year is expired in debit payment")
    void shouldGetErrorWithCardYearIsExpiredInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardYearIsExpired();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-21 Should throw error if card owner is empty in debit payment")
    void shouldGetErrorWithCardOwnerIsEmptyInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardOwnerIsEmpty();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-22 Should throw error if card owner is symbol in debit payment")
    void shouldGetErrorWithCardOwnerIsSymbolInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardOwnerIsSymbol();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-23 Should throw error if card owner is number in debit payment")
    void shouldGetErrorWithCardOwnerIsNumberInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardOwnerIsNumber();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-24 Should throw error if card owner is cyrillic in debit payment")
    void shouldGetErrorWithCardOwnerIsCyrillicInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCardOwnerIsCyrillic();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-25 Should throw error if card cvc is empty in debit payment")
    void shouldGetErrorWithCardCvcIsEmptyInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCvcIsEmpty();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }


    @Test
    @DisplayName("Test-26 Should throw error if card cvc is two numbers in debit payment")
    void shouldGetErrorWithCardCvcIsTwoNumbersInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCvcIsOfTwoNumbers();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-27 Should throw error if card cvc is three zeros in debit payment")
    void shouldGetErrorWithCardCvcIsThreeZerosInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCvcIsOfZeros();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-28 Should throw error if card cvc is symbol in debit payment")
    void shouldGetErrorWithCardCvcIsSymbolInDebitPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByDebitCard();
        makingPayment.usingAllValidDataButCvcIsSymbol();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertNull(statusForPaymentByDebitCard);
    }

    @Test
    @DisplayName("Test-29 Should throw error if card number all of zeros in Credit payment")
    void shouldGetErrorWithAllZeroCardNumberInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardNumberAllZero();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-30 Should throw error if card number consist of fifteen numbers in Credit payment")
    void shouldGetErrorWithFifteenNumbersCardInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardNumberWithFifteenNumbers();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-31 Should throw error if card number consist of seventeen numbers in Credit payment")
    void shouldGetErrorWithSeventeenNumbersCardInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingValidDataButInvalidCardNumberWithSeventeenSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-32 Should throw error if card number is null in Credit payment")
    void shouldGetErrorWithNullCardNumberInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingValidDataButCardNumberIsNull();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-33 Should throw error if card number is symbols in Credit payment")
    void shouldGetErrorWithCardNumberOfSymbolsInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardNumberWithSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-34 Should throw error if incorrect card month in Credit payment")
    void shouldGetErrorWithIncorrectCardMonthInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButIncorrectMonth();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-35 Should throw error if card month is symbol in Credit payment")
    void shouldGetErrorWithCardMonthIsSymbolInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButMonthIsSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-36 Should throw error if card month is one number in Credit payment")
    void shouldGetErrorWithCardMonthIsOneNumberInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButMonthIsOneNumber();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-37 Should throw error if card month is null in Credit payment")
    void shouldGetErrorWithCardMonthIsNullInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButMonthIsNull();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-38 Should throw error if card month is two zeros in Credit payment")
    void shouldGetErrorWithCardMonthIsTwoZerosInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButMonthIsOfZeros();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-39 Should throw error if card year is three zeros in Credit payment")
    void shouldGetErrorWithCardYearIsThreeZerosInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardYearIsOfZeros();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-40 Should throw error if card year is one number in Credit payment")
    void shouldGetErrorWithCardYearIsOneNumberInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardYearIsOneNumber();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-41 Should throw error if card year is empty in Credit payment")
    void shouldGetErrorWithCardYearIsEmptyInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardYearIsEmpty();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-42 Should throw error if card year is incorrect in Credit payment")
    void shouldGetErrorWithCardYearIsIncorrectInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardYearIsIncorrect();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-43 Should throw error if card year is symbols in Credit payment")
    void shouldGetErrorWithCardYearIsSymbolsInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardYearWithSymbols();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-44 Should throw error if card year is expired in Credit payment")
    void shouldGetErrorWithCardYearIsExpiredInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardYearIsExpired();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-45 Should throw error if card owner is empty in Credit payment")
    void shouldGetErrorWithCardOwnerIsEmptyInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardOwnerIsEmpty();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-46 Should throw error if card owner is symbol in Credit payment")
    void shouldGetErrorWithCardOwnerIsSymbolInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardOwnerIsSymbol();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-47 Should throw error if card owner is number in Credit payment")
    void shouldGetErrorWithCardOwnerIsNumberInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardOwnerIsNumber();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-48 Should throw error if card owner is cyrillic in Credit payment")
    void shouldGetErrorWithCardOwnerIsCyrillicInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCardOwnerIsCyrillic();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-49 Should throw error if card cvc is empty in Credit payment")
    void shouldGetErrorWithCardCvcIsEmptyInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCvcIsEmpty();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }


    @Test
    @DisplayName("Test-50 Should throw error if card cvc is two numbers in Credit payment")
    void shouldGetErrorWithCardCvcIsTwoNumbersInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCvcIsOfTwoNumbers();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-51 Should throw error if card cvc is three zeros in Credit payment")
    void shouldGetErrorWithCardCvcIsThreeZerosInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCvcIsOfZeros();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }

    @Test
    @DisplayName("Test-52 Should throw error if card cvc is symbol in credit payment")
    void shouldGetErrorWithCardCvcIsSymbolInCreditPayment() {
        var optionSelection = new OptionSelection();
        var makingPayment = optionSelection.buyByCreditCard();
        makingPayment.usingAllValidDataButCvcIsSymbol();
        makingPayment.checkIfWrongFormatOfField();
        var statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertNull(statusForPaymentByCreditCard);
    }
}


