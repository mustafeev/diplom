package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class OptionSelection {
    private SelenideElement debitPayment = $$(".button").find(exactText("Купить"));
    private SelenideElement creditPayment = $$(".button").find(exactText("Купить в кредит"));


    public MakingPayment buyByDebitCard() {
        debitPayment.click();

        return new MakingPayment();
    }

    public MakingPayment buyByCreditCard() {
        creditPayment.click();

        return new MakingPayment();
    }
}

