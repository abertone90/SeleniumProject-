package front;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testNewAccount {
    private WebDriver driver;
    private WebDriverWait wait;
    private NewAccountPage newAccountPage;
    private String username = "test";
    private String password = "test";

    @BeforeAll
    public static void createReport() {
        System.out.println("<<< COMIENZAN LOS TEST DE LOGIN >>>");
    }
    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.setup();
        newAccountPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }
    public void Login() throws InterruptedException {
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.putUserName(username);
        newAccountPage.putPass(password);
        newAccountPage.clickLogIn();
    }
    @Test
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void newAccount() throws InterruptedException {
      Login();
        newAccountPage.clickNewAccount();
        newAccountPage.accountType();
        newAccountPage.setAccountConfirmed();
        newAccountPage.setAccountConfirmed();
        String resultado = newAccountPage.succesMsgAcc();
        assertEquals("Congratulations, your account is now open.", resultado);
    }
    @AfterEach
    public void cerrar() {
        newAccountPage.close();
    }
    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE Login y Creaccion de Cuenta >>>");
    }
}