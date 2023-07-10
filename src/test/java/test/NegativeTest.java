package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import data.DataHelper;
import data.SQLHelper;
import Page.TourPage;

import static com.codeborne.selenide.Selenide.open;

public class NegativeTest {

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
    @DisplayName("Card - Declined card.")
    @Test
    public void shouldNotPayDeclinedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var declinedCard = DataHelper.getDeclinedCard();
        payCard.enterCardData(declinedCard);
        payCard.notSuccessfulCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusPayment(paymentId);
        Assertions.assertEquals("DECLINED", statusPayment);
    }

    @DisplayName("Card - All blank data.")
    @Test
    public void shouldNotPayEmptyForm() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        payCard.enterCardData(emptyCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("Card - Empty field card number.")
    @Test
    public void shouldNotPayEmptyCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        payCard.enterCardData(fieldCardEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Card - Empty field Year.")
    @Test
    public void shouldNotPayEmptyYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        payCard.enterCardData(fieldYearEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Card - Empty field Month.")
    @Test
    public void shouldNotPayEmptyMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        payCard.enterCardData(fieldMonthEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Card - Empty field Holder")
    @Test
    public void shouldNotPayEmptyHolder() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        payCard.enterCardData(fieldHolderEmpty);
        payCard.requiredCardToFillIn();
    }

    @DisplayName("Card - Empty field CVV.")
    @Test
    public void shouldNotPayEmptyCvv() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        payCard.enterCardData(fieldCvvEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Card - Zero month.")
    @Test
    public void shouldNotPayZeroMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("Card - Zero CVV")
    @Test
    public void shouldNotPayZeroCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }


    ///////////Test Credit/////////////
    @DisplayName("Credit - All blank data.")
    @Test
    public void shouldNotCreditEmptyForm() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        buyCredit.enterCreditCardData(emptyCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Credit - Empty field card number.")
    @Test
    public void shouldNotCreditEmptyCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        buyCredit.enterCreditCardData(fieldCardEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Credit - Empty field Year.")
    @Test
    public void shouldNotCreditEmptyYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        buyCredit.enterCreditCardData(fieldYearEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Credit - Empty field Month.")
    @Test
    public void shouldNotCreditEmptyMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        buyCredit.enterCreditCardData(fieldMonthEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Credit - Empty field Holder")
    @Test
    public void shouldNotCreditEmptyHolder() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        buyCredit.enterCreditCardData(fieldHolderEmpty);
        buyCredit.requiredCreditToFillIn();
    }

    @DisplayName("Credit - Empty field CVV.")
    @Test
    public void shouldNotCreditEmptyCvv() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        buyCredit.enterCreditCardData(fieldCvvEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Invalid card number.")
    @Test
    public void shouldNotPayInvalidNumber() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getInvalidNumber();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit- Zero month.")
    @Test
    public void shouldNotCreditZeroMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();

    }

    @DisplayName("Credit - Zero CVV")
    @Test
    public void shouldNotCreditZeroCVV() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }
}