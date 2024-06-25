package front;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class testMonthlyActivity {
    private WebDriver driver;
    private WebDriverWait wait;
    private MonthlyActivityPage monthlyActivityPage;
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
        monthlyActivityPage = new MonthlyActivityPage(driver, wait);
        monthlyActivityPage.setup();
        monthlyActivityPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }
    public void Login() throws InterruptedException {
        MonthlyActivityPage monthlyActivityPage = new MonthlyActivityPage(driver, wait);
        monthlyActivityPage.putUserName(username);
        monthlyActivityPage.putPass(password);
        monthlyActivityPage.clickLogIn();
    }
    @Test
    @Tag("SummaryOfAccountsView")
    @Tag("EXITOSO")
    public void accMonthlyActivity() throws InterruptedException {
        Login();

        String resultado = monthlyActivityPage.msgAccountBalance();
        monthlyActivityPage.clickAccountNumber();
        String resultado2 = monthlyActivityPage.accDetailsMsg();
        monthlyActivityPage.activityPeriod();
        monthlyActivityPage.activityType();
        monthlyActivityPage.clickGo();




    }
    /*
    @AfterEach
    public void cerrar() {
        monthlyActivityPage.close();
    }*/
    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST Acc Activity >>>");
    }
}