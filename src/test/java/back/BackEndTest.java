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
    public void  openNewAccount(){
        System.out.println("Iniciando Primer Test Post");
        ExtentTest test = extent.createTest("POST - Abrir Nueva Cuenta");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Primer Test Post");
        test.log(Status.INFO, "Iniciando Primer Test Post");
        int newAccountType = 1;

        JsonObject request = new JsonObject();
        request.addProperty("customerId", customerId);
        request.addProperty("type", "SAVINGS");
        request.addProperty("balance", 0);

        Response response = given()
                .auth().basic(username, password)
                .contentType("application/json")
                .body(request.toString())
                .post("https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=" + customerId + "&newAccountType=" + newAccountType + "&fromAccountId=" + fromAccountId);

// Para depuración: imprimir los detalles de la respuesta
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        response.then().statusCode(200);

        System.out.println("Primer Test Post finalizado");
        test.log(Status.PASS, "Primer Test Post finalizado");
    }

    @Test
    @Order(3)
    @Tag("POST")
    public void fundsDownloader() {
        System.out.println("Iniciando Test de Descargar Fondos");
        ExtentTest test = extent.createTest("POST - Descargar Fondos");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.INFO, "Iniciando test Descargar Fondos tipo POST");

        // Datos de autenticación básica
        String endpoint = "https://parabank.parasoft.com/parabank/services_proxy/bank/transfer";
        int amount = 500;

        // Enviar la solicitud de transferencia con RestAssured
        Response response = given()
                .auth().basic(username, password)
                .post(endpoint + "?fromAccountId=" + fromAccountId + "&toAccountId=" + toAccountId + "&amount=" + amount);

        test.log(Status.INFO, "Intentando hacer POST a la URL: " + endpoint);

        // Imprimir los detalles de la respuesta para depuración
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Validar el código de estado esperado
        assertEquals(200, response.getStatusCode(), "Error: El código de estado no es 200");

        System.out.println("Test de Descargar Fondos finalizado");
        test.log(Status.PASS, "POST - Descargar Fondos finalizado");
    }
    @Test
    @Order(4)
    @Tag("GET")
    public void testAccountActivity() {
        int fromAccountId = 24111;
        System.out.println("Iniciando Test de Actividad de Cuenta");
        String url = "https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/" + fromAccountId + "/activity";

        ExtentTest test = extent.createTest("GET - Account Activity");
        test.log(Status.INFO, "Comienza el test");
        test.log(Status.INFO, "Iniciando ultimo test GET");


        Response response = given()
                .auth().basic(username, password)
                .get(url);

        // Print response details for debugging
        System.out.println("Request URL: " + url);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Validate expected status code
        assertEquals(200, response.getStatusCode(), "Error: Status no es 200");

        // Print test completion message
        System.out.println("Account Activity Test completed");

        // Mark test as passed in Extent report
        test.log(Status.PASS, "GET - Account Activity completed");
    }

    @AfterAll
    public static void saveReport() {
        // Finalize and save the Extent report
        extent.flush();
        System.out.println("<<< BACKEND TESTS FINISHED >>>");
    }

}


