package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import data.DataHelper;
import data.SQLHelper;
import Page.TourPage;


import static com.codeborne.selenide.Selenide.open;

public class PositiveTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openChrome() {
        open("http://localhost:8080/");
    }
    ///////////Test Card/////////////
    @DisplayName("Successful card purchase.")
    @Test
    public void shouldConfirmPaymentApprovedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var approvedCardInformation = DataHelper.getValidCard();
        payCard.enterCardData(approvedCardInformation);
        payCard.successfulCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusPayment(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }

    @DisplayName("Successful card purchase with current M and Y.")
    @Test
    public void shouldConfirmPaymentCurrentMonthAndYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        payCard.enterCardData(validCardInformation);
        payCard.successfulCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusPayment(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }

    ///////////Test Credit/////////////
    @DisplayName("Successful credit purchase")
    @Test
    public void shouldConfirmCreditApprovedCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var approvedCardInformation = DataHelper.getValidCard();
        buyCredit.enterCreditCardData(approvedCardInformation);
        buyCredit.successfulCreditCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusCredit(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }

    @DisplayName("Successful credit purchase with current M and Y.")
    @Test
    public void shouldConfirmCreditWithCurrentMonthAndYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        buyCredit.enterCreditCardData(validCardInformation);
        buyCredit.successfulCreditCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusCredit(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }
}