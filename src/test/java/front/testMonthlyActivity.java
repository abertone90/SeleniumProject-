package front;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testMonthlyActivity {
    private WebDriver driver;
    private WebDriverWait wait;
    private MonthlyActivityPage monthlyActivityPage;
    private String username = "test";
    private String password = "test";

    static ExtentSparkReporter spark;
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        spark = new ExtentSparkReporter("reportes/FrontEnd-MonthlyActivity-Test.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        System.out.println("<<< COMIENZAN LOS TEST DE MonthlyActivity >>>");
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

    public void login() throws InterruptedException {
        MonthlyActivityPage monthlyActivityPage = new MonthlyActivityPage(driver, wait);
        monthlyActivityPage.putUserName(username);
        monthlyActivityPage.putPass(password);
        monthlyActivityPage.clickLogIn();
    }

    @Test
    @Tag("MonthlyActivity")
    @Tag("FRONTEND")
    @Tag("EXITOSO")
    public void accMonthlyActivity() throws InterruptedException {
        ExtentTest test = extent.createTest("Monthly Activity");
        test.log(Status.INFO, "Comienza el Test");

        login();

        String resultado = monthlyActivityPage.msgAccountBalance();
        assertEquals("*Balance includes deposits that may be subject to holds", resultado);

        monthlyActivityPage.clickAccountNumber();
        String resultado2 = monthlyActivityPage.accDetailsMsg();
        assertEquals("Account Details", resultado2);

        monthlyActivityPage.activityPeriod();
        monthlyActivityPage.activityType();
        monthlyActivityPage.clickGo();

        test.log(Status.PASS, "Verificación de actividad mensual exitosa");
    }

    @AfterEach
    public void cerrar() {
        monthlyActivityPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE MonthlyActivity >>>");
    }
}
