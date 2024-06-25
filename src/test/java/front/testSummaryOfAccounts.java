package front;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testSummaryOfAccounts {
    private WebDriver driver;
    private WebDriverWait wait;
    private SummaryOfAccountsPage summaryOfAccounts;
    private String username = "test";
    private String password = "test";

    @BeforeAll
    public static void createReport() {
        System.out.println("<<< COMIENZAN LOS TEST DE Vista de Resumen >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        summaryOfAccounts = new SummaryOfAccountsPage(driver, wait);
        summaryOfAccounts.setup();
        summaryOfAccounts.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }
    public void Login() throws InterruptedException {
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.putUserName(username);
        newAccountPage.putPass(password);
        newAccountPage.clickLogIn();
    }
    @Test
    @Tag("SummaryOfAccountsView")
    @Tag("EXITOSO")
    public void accountSumary() throws InterruptedException {
        Login();
        summaryOfAccounts.clickAccView();
        String resultado = summaryOfAccounts.msgAccountBalance();
        assertEquals("*Balance includes deposits that may be subject to holds", resultado);
    }
    @AfterEach
    public void cerrar() {
        summaryOfAccounts.close();
    }
    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE Vista de Resumen >>>");
    }
}