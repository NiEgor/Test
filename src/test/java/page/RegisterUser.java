package page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.SettingsBrowser;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegisterUser extends SettingsBrowser {

    private final SelenideElement tableElement = $x("//table[@class='table table-bordered table-striped']");


    @Test
    @DisplayName("Создание счетов")
    public void openPage() {
        open("/angularJs-protractor/BankingProject/#/login");
        $(byText("Customer Login")).click();
        $("#userSelect").click();
        $(byText("Harry Potter")).click();
        $(byText("Login")).click();
        $(".fontBig").shouldHave(text("Harry Potter"));

        step("Счёт 'Deposit'", () ->
                $(byText("Deposit")).click());
        $(By.cssSelector("input[type='number'")).val("144");
        $(By.cssSelector("button[type='submit'")).click();
        $(".error.ng-binding").shouldHave(text("Deposit Successful"));

        step("Счёт 'Withdrawl'", () ->
                $(byText("Withdrawl")).click());
        sleep(3000);
        $x("//input[@type='number']").setValue("144");
        $(By.cssSelector("button[type='submit'")).click();
        $(".error.ng-binding").shouldHave(text("Transaction  Successful"));
        $x("//strong[@class=\"ng-binding\"][2]").shouldHave(text("0"));

        sleep(3000);
        step("Операции 'Transactions' проверка наличие транзакций", () ->
                $(byText("Transactions")).click());

        Table table = new Table(tableElement);
        String debit = table.getValueFromCell(1, "Transaction Type");
        String credit = table.getValueFromCell(2, "Transaction Type");
        $(".table.table-bordered.table-striped").shouldHave(text(debit));
        $(".table.table-bordered.table-striped").shouldHave(text(credit));

        System.out.println("Rows number is:" + table.getRows().size());


    }
}
