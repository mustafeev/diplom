package data;

import com.github.javafaker.Faker;
import lombok.Value;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;



public class DataHelper {
    private static final Faker fakerEnglish = new Faker(new Locale("en"));
    private static final Faker fakerRussian = new Faker(new Locale("ru", "RU"));
    static Random random = new Random();

    @Value
    public static class ApplicationProcessing {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    private DataHelper() {
    }

    //CARD MONTH

    public static String getCardMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getInvalidCardMonth() {
        return String.valueOf(Math.random()*10);
    }

    public static String getEmptyCardMonth() {
        return (" ");
    }

    public static String invalidCardMonthWithZero() {
        return "00";
    }

    public static String invalidCardMonthWithOneNumber() {

        return fakerEnglish.numerify("#");
    }

    public static String invalidCardMonthWithSymbols() {
        return fakerEnglish.letterify("?");
    }

    // CARD YEAR

    public static String getCardYear() {
        return LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getInvalidYear() {
        return LocalDate.now().plusYears(10).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getEmptyCardYear() {
        return (" ");
    }

    public static String invalidCardYearWithZero() {
        return "00";
    }

    public static String invalidCardYearWithOneNumber() {
        return fakerEnglish.numerify("#");
    }

    public static String invalidCardYearWithSymbols() {
        return RandomStringUtils.randomAscii(1);
    }

    public static String expiredCardYear(){
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    // OWNERS NAME

    public static String getOwnersName() {
        return fakerEnglish.name().fullName();
    }

    public static String getInvalidOwnersName() {
        return fakerRussian.name().fullName();
    }

    public static String getEmptyOwner() {
        return (" ");
    }

    public static String invalidOwnersNameWithSymbols() {
        return RandomStringUtils.randomAscii(1);
    }

    public static String invalidOwnersNameWithNumbers() {
        return fakerEnglish.numerify("#");
    }


    // CVC

    public static String generateCvc() {
        return fakerEnglish.numerify("###");
    }

    public static String generateInvalidCvc() {
        return fakerEnglish.numerify("##");
    }

    public static String getEmptyCvc() {
        return (" ");
    }

    public static String generateInvalidCvcWithZero() {
        return "000";
    }

    public static String invalidCvcWithSymbols() {
        return RandomStringUtils.randomAscii(1);
    }


    // CARD NUMBER


    public static String approvedCardNumber() {
        return "4444444444444441";
    }

    public static String declinedCardNumber() {
        return "4444444444444442";
    }

    public static String invalidCardNumberWithAllZero() {
        return "0000000000000000";
    }

    public static String invalidCardNumberWithFifteenSymbols() {
        return fakerEnglish.numerify("###############");
    }

    public static String invalidCardNumberWithSeventeenSymbols() {
        return fakerEnglish.numerify("#################");
    }

    public static String invalidCardNumberWithSymbols() {
        return RandomStringUtils.randomAscii(1);
    }

    public static String getEmptyCardNumber() {
        return (" ");
    }

}