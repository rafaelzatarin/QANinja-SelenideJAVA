import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.isChrome;

/** Formas de buscar Elementos em Selenide
 * Buscar elemento em Selenide utilizar o simbolo de dolar
 * Buscar pelo elemento pelo ID - conforme implementado abaixo e no #emailId
 *  $("#login").click();
 * Buscar pelo name do elemento - Conforme implementado abaixo e no byName("password")
 *  $("input[name=email]").setValue("zata@ninjaplus.com");
 * Buscar pelo texto do elemento - Conforme implementado no byText("Entrar")
 * Validação do nome do usuário na tela de login - Conforme implementado no shouldHave(text("Rafael Zata"))
 */

public class LoginTests {

    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
            {"zata@ninjaplus.com","abc","Usuário e/ou senha inválidos"},
            {"zata@gmail.com","abc","Usuário e/ou senha inválidos"},
            {"","123","Opps. Cadê o email?"},
            {"zata@ninjaplus.com","","Opps. Cadê a senha?"}
        };
    }

    public void acessoBrowser() {
        isChrome();
        open("http://ninjaplus-web:5000");
    }

    @Test
    public void loginSucesso() {
        acessoBrowser();
        $("#emailId").setValue("zata@ninjaplus.com");
        $(byName("password")).setValue("123");
        $(byText("Entrar")).click();
        $(".user .info span").shouldHave(text("Rafael Zata"));
        executeJavaScript("localStorage.clear();");
    }

    @Test(dataProvider = "login-alerts")
    public void loginAlerts(String email, String senha, String expectAlert) {
        acessoBrowser();
        $("#emailId").setValue(email);
        $(byName("password")).setValue(senha);
        $(byText("Entrar")).click();
        $(".alert span").shouldHave(text(expectAlert));
    }

}
