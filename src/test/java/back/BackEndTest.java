package back;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackEndTest {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/BackEnd-Test.html");
    static ExtentReports extent;


    private static int customerId = 19316;
    private static int fromAccountId = 24111;
    private static int toAccountId = 24222;
    private static String username = "test";
    private static String password = "test";


    @BeforeAll
    public static void setup() {
        System.out.println("<<< COMIENZAN LOS TEST DE BACKEND >>>");
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }

    @Test
    @Order(1)
    @Tag("GET")
    public void Registro() {
        System.out.println("Iniciando Primer Test Get");
        ExtentTest test = extent.createTest("GET - Registro");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.INFO, "Iniciando test registro tipo GET");

        Response responseGet = RestAssured.get("https://parabank.parasoft.com/parabank/register.htm");
        System.out.println(responseGet.getBody().asString());
        System.out.println(responseGet.statusCode());
        System.out.println(responseGet.getHeader("Date"));
        System.out.println(responseGet.getTime());
        test.log(Status.INFO, "Test GET a la url https://parabank.parasoft.com/parabank/register.htm");

        // Aserción para comprobar que el status code es 200
        assertEquals(200, responseGet.statusCode(), "El status code debería ser 200");

        System.out.println("Primer Test Get finalizado");
        test.log(Status.PASS, "Primer Test Get finalizado exitosamente");

    }
    @Test
    @Order(2)
    @Tag("POST")
    public void testAbrirNuevaCuenta() {
        ExtentTest test = extent.createTest("POST - Abrir Nueva Cuenta");
        test.log(Status.INFO, "Comienza el Test");

        // Construir el cuerpo de la solicitud
        String requestBody = "{\n" +
                "  \"customerId\": " + customerId + ",\n" +
                "  \"type\": \"SAVINGS\",\n" +
                "  \"balance\": 0\n" +
                "}";

        // Imprimir el cuerpo de la solicitud para depuración
        System.out.println("Request Body: " + requestBody);

        // Enviar la solicitud con RestAssured
        Response response = given()
                .auth().basic(username, password)
                .contentType("application/json")
                .body(requestBody)
                .post("https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?newAccountType=1&fromAccountId=" + fromAccountId);

        // Imprimir los detalles de la respuesta para depuración
        test.log(Status.INFO, "Intentando hacer POST a la URL https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount");

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Validar el código de estado esperado
        assertEquals(200, response.getStatusCode(), "El status code debería ser 200");

        test.log(Status.PASS, "POST - Abrir Nueva Cuenta finalizado");
    }



}


