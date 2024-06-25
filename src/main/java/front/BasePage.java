package front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private WebDriver driver;
    public WebDriverWait wait;

    /** Constructor de la página base.
     * @param driver El controlador del navegador web.
     */
    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    /** Método para configurar opciones del navegador
     */
    protected void setup() {
        driver.manage().window().maximize();
    }

    /** Método para navegar a la URL especificada.
     * @param url La URL a la que se desea navegar.
     */
    protected void getUrl(String url) throws InterruptedException {
        driver.get(url);
    }

    /** Método para cerrar el navegador web.
     */
    protected void close() {
        driver.quit();
    }

    /** Método para encuentrar un elemento en la página mediante el localizador especificado.
     * @param locator El localizador del elemento.
     * @return El elemento encontrado.
     */
    protected WebElement findElement(By locator) throws InterruptedException {
        return driver.findElement(locator);
    }

    /** Método para ingresar texto en el elemento especificado.
     * @param inputText El texto a ingresar.
     * @param locator El localizador del elemento.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    protected void sendText(String inputText, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(inputText);
    }

    /** Método para hacer click en el elemento especificado.
     * @param locator El localizador del elemento.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    protected void click(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    /** Método para enviar una tecla al elemento especificado.
     * @param key La tecla a enviar.
     * @param locator El localizador del elemento.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).sendKeys(key);
    }

    /** Método para obtener el texto del elemento especificado.
     * @param locator El localizador del elemento.
     * @return El texto del elemento.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    protected String getText(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this.findElement(locator).getText();
    }

    protected void selectOptionFromDropdown(By dropdownLocator, int optionIndex) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        dropdown.click();  // Hacer clic en el elemento del menú desplegable si es necesario

        // Crear un objeto Select a partir del elemento encontrado
        Select select = new Select(dropdown);
        select.selectByIndex(optionIndex);  // Seleccionar la opción por su índice
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear(); // Limpiar el campo antes de escribir
        element.sendKeys(text);
    }
    /** Customer Login*/
    private  By userN = By.xpath("//input[@name='username']");
    private  By password = By.xpath("//input[@name='password']");
    private  By logIn =  By.xpath("//input[@value='Log In']");
    public void putUserName(String user) throws InterruptedException {
        this.type(userN, user);
    }
    public void putPass(String pass) throws InterruptedException {
        this.type(password, pass);
    }
    public void clickLogIn() throws InterruptedException {
        this.click(logIn);
    }


}