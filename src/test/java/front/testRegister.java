package front;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class testRegister {
    private WebDriver driver;
    private WebDriverWait wait;
    private RegisterPage registerPage;

    @BeforeAll
    public static void createReport() {
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
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void register() throws InterruptedException {
        registerPage.clickCreateAcc();
        registerPage.putName("jhon");
        registerPage.putLastName("test");
        registerPage.putAddress("address");
        registerPage.putCity("city");
        registerPage.putState("state");
        registerPage.putZipCode("12345");
        registerPage.putTelephone("12345789");
        registerPage.putSSN("123456789");
        registerPage.putUserName("test1");

        registerPage.putTelephone("12345789");
        registerPage.putPass("test");
        registerPage.repitPass("test");
        registerPage.clickRegistrarse();

        String resultado = registerPage.validaCuentaCreada();
        Assertions.assertEquals("Your account was created successfully. You are now logged in.", resultado);
    }

    @AfterEach
    public void cerrar() {
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}