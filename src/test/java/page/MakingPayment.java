package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MakingPayment {

    private final SelenideElement cardNumber = $("input[type='text'][placeholder='0000 0000 0000 0000']");
    private final SelenideElement cardMonth = $("input[type='text'][placeholder='08']");
    private final SelenideElement cardYear = $("input[type='text'][placeholder='22']");
    private final SelenideElement cardOwner = $$("form div:nth-child(3) .input__control").first();
    private final SelenideElement cardCvc = $("input[type='text'][placeholder='999']");
    private final SelenideElement continueButton = $("form div:nth-child(4) .button__content");
    private final SelenideElement successNotification = $(".notification_status_ok .notification__content");
    private final SelenideElement errorNotification = $(".notification_status_error .notification__content");
    private final SelenideElement dataEntryError = $(".input__sub");

    public void fillingByValidDataWithApprovedCard() { //подтвержденная карта, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void fillingByValidDataWithDeclinedCard() { //отклоненная карта, остальные данные валидные
        cardNumber.setValue(DataHelper.declinedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void fillingAllFieldsWithNull() { //все поля пустые
        cardNumber.setValue(DataHelper.getEmptyCardNumber());
        cardMonth.setValue(DataHelper.getEmptyCardMonth());
        cardYear.setValue(DataHelper.getEmptyCardYear());
        cardOwner.setValue(DataHelper.getEmptyOwner());
        cardCvc.setValue(DataHelper.getEmptyCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardNumberAllZero() { //номер карты из нулей, остальные данные валидные
        cardNumber.setValue(DataHelper.invalidCardNumberWithAllZero());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardNumberWithFifteenNumbers() { //номер карты из 15 чисел, остальные данные валидные
        cardNumber.setValue(DataHelper.invalidCardNumberWithFifteenSymbols());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardNumberWithSymbols() { //номер карты c символами, остальные данные валидные
        cardNumber.setValue(DataHelper.invalidCardNumberWithSymbols());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingValidDataButInvalidCardNumberWithSeventeenSymbols() { //номер карты из 17 цифр, остальные данные валидные
        cardNumber.setValue(DataHelper.invalidCardNumberWithSeventeenSymbols());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingValidDataButCardNumberIsNull() { //номер карты пустое, остальные данные валидные
        cardNumber.setValue(DataHelper.getEmptyCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButIncorrectMonth() { //месяц некорректный, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getInvalidCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButMonthIsOneNumber() { //месяц из одной цифры, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.invalidCardMonthWithOneNumber());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButMonthIsSymbols() { //символ вместо месяца, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.invalidCardMonthWithSymbols());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButMonthIsNull() { //поле месяца пустое, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getEmptyCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButMonthIsOfZeros() { //поле месяца заполняется двумя нолями, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.invalidCardMonthWithZero());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardYearIsOfZeros() { //поле год заполняется нолями, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.invalidCardYearWithZero());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardYearIsOneNumber() { //поле год из одной цифры, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.invalidCardYearWithOneNumber());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardYearIsEmpty() { //поле год путое, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getEmptyCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardYearIsIncorrect() { //поле год с некорректным значением, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getInvalidYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardYearWithSymbols() { //поле год с символами, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.invalidCardYearWithSymbols());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardYearIsExpired() { //значение года меньше текущего, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.expiredCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardOwnerIsEmpty() { //поле владелец пустое, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getEmptyOwner());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardOwnerIsSymbol() { //в поле владелец спецсимвол, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.invalidOwnersNameWithSymbols());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardOwnerIsNumber() { //в поле владелец число, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.invalidOwnersNameWithNumbers());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCardOwnerIsCyrillic() { //поле владелец заполнено кириллицей, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getInvalidOwnersName());
        cardCvc.setValue(DataHelper.generateCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCvcIsEmpty() { //поле cvc пустое, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.getEmptyCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCvcIsOfTwoNumbers() { //поле cvc из двух чисел, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateInvalidCvc());
        continueButton.click();
    }

    public void usingAllValidDataButCvcIsOfZeros() { //поле cvc из трех нолей, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.generateInvalidCvcWithZero());
        continueButton.click();
    }

    public void usingAllValidDataButCvcIsSymbol() { //в поле cvc символ, остальные данные валидные
        cardNumber.setValue(DataHelper.approvedCardNumber());
        cardMonth.setValue(DataHelper.getCardMonth());
        cardYear.setValue(DataHelper.getCardYear());
        cardOwner.setValue(DataHelper.getOwnersName());
        cardCvc.setValue(DataHelper.invalidCvcWithSymbols());
        continueButton.click();
    }

    public void checkPaymentSuccess() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(6));
    }

    public void checkIfPaymentNotSuccessful() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(6));
    }

    public void checkIfWrongFormatOfField() {
        dataEntryError.shouldBe(Condition.visible);
    }
}