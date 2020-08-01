import org.testng.annotations.Test;
import static com.codeborne.selenide.WebDriverRunner.isChrome;

public class HelloSelenideTest {

    @Test
    public void OnAir() {
        isChrome();
        open('h')
    }
}
