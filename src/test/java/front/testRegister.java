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

public class testRegister {
    private WebDriver driver;
    private WebDriverWait wait;
    private RegisterPage registerPage;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/FrontEnd-Register-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE REGISTRO >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        registerPage = new RegisterPage(driver, wait);

        registerPage.setup();
        registerPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("Register")
    @Tag("FRONTEND")
    @Tag("EXITOSO")
    public void register() throws InterruptedException {
        ExtentTest test = extent.createTest("Register");
        test.log(Status.INFO, "Comienza el Test");

        registerPage.clickCreateAcc();
        registerPage.putName("jhon");
        registerPage.putLastName("test");
        registerPage.putAddress("address");
        registerPage.putCity("city");
        registerPage.putState("state");
        registerPage.putZipCode("12345");
        registerPage.putTelephone("12345789");
        registerPage.putSSN("123456789");
        registerPage.putUserName("test");
        registerPage.putTelephone("12345789");
        registerPage.putPass("test");
        registerPage.repitPass("test");
        registerPage.clickRegistrarse();

        String resultado = registerPage.validaCuentaCreada();
        Assertions.assertEquals("Your account was created successfully. You are now logged in.", resultado);

        test.log(Status.PASS, "Se registro exitosamente");
    }

    @AfterEach
    public void cerrar() {
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}
