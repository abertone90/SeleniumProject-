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

public class testSummaryOfAccounts {
    private WebDriver driver;
    private WebDriverWait wait;
    private SummaryOfAccountsPage summaryOfAccounts;
    private String username = "test";
    private String password = "test";

    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/FrontEnd-Summary-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
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

    public void login() throws InterruptedException {
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.putUserName(username);
        newAccountPage.putPass(password);
        newAccountPage.clickLogIn();
    }

    @Test
    @Tag("SummaryOfAccountsView")
    @Tag("FRONTEND")
    @Tag("EXITOSO")
    public void accountSumary() throws InterruptedException {
        ExtentTest test = extent.createTest("Summary of Accounts Test");
        test.log(Status.INFO, "Comienza el Test");

        login();
        summaryOfAccounts.clickAccView();
        String resultado = summaryOfAccounts.msgAccountBalance();
        assertEquals("*Balance includes deposits that may be subject to holds", resultado);

        test.log(Status.PASS, "Verificación de saldo exitosa");
    }

    @AfterEach
    public void cerrar() {
        summaryOfAccounts.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE Vista de Resumen >>>");
    }
}
