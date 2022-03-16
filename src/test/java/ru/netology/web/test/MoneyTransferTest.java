package ru.netology.web.test;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @BeforeEach
    void shouldOpen () {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards(){

        int actual = new DashboardPage().getFirstCardBalance();
        int actual2 = new DashboardPage().getSecondCardBalance();

        ElementsCollection buttons = $$("[data-test-id='action-deposit']");
        buttons.first().click();
        int transfer = 1000;
        $("[data-test-id = 'amount'] input").setValue(String.valueOf(transfer));
        $("[data-test-id = 'from'] input").setValue("5559 0000 0000 0002");
        $("button").click();
        $(".heading").shouldBe(visible, Duration.ofSeconds(10));

        int expected = new DashboardPage().getFirstCardBalance() - transfer;
        assertEquals (actual, expected);
        int expected2 = new DashboardPage().getSecondCardBalance() + transfer;
        assertEquals (actual2, expected2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards2(){

        int actual = new DashboardPage().getSecondCardBalance();
        int actual2 = new DashboardPage().getFirstCardBalance();

        ElementsCollection buttons = $$("[data-test-id='action-deposit']");
        buttons.last().click();
        int transfer = 30000;
        $("[data-test-id = 'amount'] input").setValue(String.valueOf(transfer));
        $("[data-test-id = 'from'] input").setValue("5559 0000 0000 0001");
        $("button").click();
        $(".heading").shouldBe(visible, Duration.ofSeconds(10));

        int expected = new DashboardPage().getSecondCardBalance() - transfer;
        assertEquals (actual, expected);
        int expected2 = new DashboardPage().getFirstCardBalance() + transfer;
        assertEquals (actual2, expected2);
    }





     // int extractBalance = new DashboardPage().getFirstCardBalance();






}

