package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OptionSelection {
    private SelenideElement debitPayment = $$(".button").find(exactText("Купить"));
    private SelenideElement creditPayment = $$(".button").find(exactText("Купить в кредит"));
    private SelenideElement kindOfPayment = $("#root > div > h3");

    public MakingPayment buyByDebitCard() {
        debitPayment.click();
        kindOfPayment.shouldHave(exactText("Оплата по карте"));
        return new MakingPayment();
    }

    public MakingPayment buyByCreditCard() {
        creditPayment.click();
        kindOfPayment.shouldHave(exactText("Кредит по данным карты"));
        return new MakingPayment();
    }
}

