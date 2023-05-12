package test;


import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.OptionSelection;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.System.getProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakingPaymentTest {
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    //   @BeforeAll
    //   static void setUpTo() {
    //      Properties props = org.postgresql.Driver.parseURL("jdbc:postgresql:myDatabase", null);

    //      String host   = props.getProperty(PGProperty.PG_HOST.getName());
    //      int    port   = Integer.parseInt(props.getProperty(PGProperty.PG_PORT.getName()));
    //      String dbName = props.getProperty(PGProperty.PG_DBNAME.getName());

    //   }

    @BeforeEach
    void setUp() {
        String appUrl = getProperty("app.url");
        open(appUrl);
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldSuccessWithValidDebitCard() {
        val optionSelection = new OptionSelection();
        val makingPayment = optionSelection.buyByDebitCard();
        val validCardInformation = DataHelper.getAuthInfoUseTestData();
        makingPayment.fillingInThePayersData(validCardInformation);
        makingPayment.checkPaymentSuccess();
        val statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        val paymentAmount = SQLHelper.getPaymentAmount();
        assertEquals("APPROVED", statusForPaymentByDebitCard);
        assertEquals("4500000", paymentAmount);
    }

    @Test
    void shouldSuccessWithValidCreditCard() {
        val optionSelection = new OptionSelection();
        val makingPayment = optionSelection.buyByCreditCard();
        val validCardInformation = DataHelper.getAuthInfoUseTestData();
        makingPayment.fillingInThePayersData(validCardInformation);
        makingPayment.checkPaymentSuccess();
        val statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        val paymentAmount = SQLHelper.getPaymentAmount();
        assertEquals("APPROVED", statusForPaymentByCreditCard);
        assertEquals("4500000", paymentAmount);

    }
    @Test
    void shouldDeclineWithInvalidDebitCard() {
        val optionSelection = new OptionSelection();
        val makingPayment = optionSelection.buyByDebitCard();
        val validCardInformation = DataHelper.getAuthInfoUseTestDataWithDeclinedCard();
        makingPayment.fillingInThePayersData(validCardInformation);
        makingPayment.checkIfPaymentNotSuccessful();
        val statusForPaymentByDebitCard = SQLHelper.getPaymentStatusByDebitCard();
        assertEquals("DECLINED", statusForPaymentByDebitCard);
    }

    @Test
    void shouldDeclineWithInvalidCreditCard() {
        val optionSelection = new OptionSelection();
        val makingPayment = optionSelection.buyByCreditCard();
        val validCardInformation = DataHelper.getAuthInfoUseTestDataWithDeclinedCard();
        makingPayment.fillingInThePayersData(validCardInformation);
        makingPayment.checkIfPaymentNotSuccessful();
        val statusForPaymentByCreditCard = SQLHelper.getPaymentStatusByCreditCard();
        assertEquals("DECLINED", statusForPaymentByCreditCard);
    }


    @Test
    void shouldThrowAnErrorIfEmptyFields() {
        val optionSelection = new OptionSelection();
        val makingPayment = optionSelection.buyByDebitCard();
        val invalidCardInformation = DataHelper.getAuthEmptyFormFields();
        makingPayment.fillingInThePayersData(invalidCardInformation);
        makingPayment.checkIfWrongFormatOfField();
    }



}