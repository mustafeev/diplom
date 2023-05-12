package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MakingPayment {

    private SelenideElement cardNumber = $("input[type='text'][placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonth = $("input[type='text'][placeholder='08']");
    private SelenideElement cardYear = $("input[type='text'][placeholder='22']");
    private SelenideElement cardOwner = $$("form div:nth-child(3) .input__control").first();
    private SelenideElement cardCvc = $("input[type='text'][placeholder='999']");
    private SelenideElement continueButton = $("form div:nth-child(4) .button__content");
    private SelenideElement successNotification = $(".notification_status_ok .notification__content");
    private SelenideElement errorNotification = $(".notification_status_error .notification__content");
    private SelenideElement dataEntryError = $(".input__sub");

    public void fillingInThePayersData(DataHelper.ApplicationProcessing applicationProcessing) {
        cardNumber.setValue(applicationProcessing.getCardNumber());
        cardMonth.setValue(applicationProcessing.getMonth());
        cardYear.setValue(applicationProcessing.getYear());
        cardOwner.setValue(applicationProcessing.getOwner());
        cardCvc.setValue(applicationProcessing.getCvc());
        continueButton.click();
    }

    public void checkPaymentSuccess() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(4));
    }

    public void checkIfPaymentNotSuccessful() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(4));
    }

    public void checkIfWrongFormatOfField() {
        dataEntryError.shouldBe(Condition.visible);
    }
}