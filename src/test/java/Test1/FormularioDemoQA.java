package Test1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FormularioDemoQA {

    private WebDriver driver;
    private JavascriptExecutor js;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        driver.get("https://demoqa.com/automation-practice-form");
    }

    private void pausa(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void llenarFormulario() {

        pausa(2);

        driver.findElement(By.id("firstName"))
                .sendKeys("Alejandro");

        pausa(1);

        driver.findElement(By.id("lastName"))
                .sendKeys("Chavarria");

        pausa(1);

        driver.findElement(By.id("userEmail"))
                .sendKeys("alejandro@test.com");

        pausa(1);

        driver.findElement(By.xpath("//label[text()='Male']")).click();

        pausa(1);

        driver.findElement(By.id("userNumber"))
                .sendKeys("8888888888");

        pausa(1);

        WebElement fecha = driver.findElement(By.id("dateOfBirthInput"));
        fecha.sendKeys(Keys.CONTROL + "a");
        fecha.sendKeys("31 Mar 1995");
        fecha.sendKeys(Keys.ENTER);

        pausa(1);

        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        subjects.sendKeys("Maths");
        subjects.sendKeys(Keys.ENTER);

        pausa(1);

        driver.findElement(By.xpath("//label[text()='Sports']")).click();

        pausa(1);

        driver.findElement(By.id("currentAddress"))
                .sendKeys("Heredia, Costa Rica");

        pausa(1);

        js.executeScript("window.scrollBy(0,500)");

        pausa(1);

        driver.findElement(By.id("react-select-3-input"))
                .sendKeys("NCR");
        driver.findElement(By.id("react-select-3-input"))
                .sendKeys(Keys.ENTER);

        pausa(1);

        driver.findElement(By.id("react-select-4-input"))
                .sendKeys("Delhi");
        driver.findElement(By.id("react-select-4-input"))
                .sendKeys(Keys.ENTER);

        pausa(2);

        WebElement botonSubmit = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].scrollIntoView(true);", botonSubmit);

        pausa(1);

        js.executeScript("arguments[0].click();", botonSubmit);

        pausa(3);

        WebElement tituloResultado = driver.findElement(By.id("example-modal-sizes-title-lg"));
        assertTrue(tituloResultado.getText().contains("Thanks for submitting the form"));

        System.out.println("Formulario enviado correctamente.");
    }

    @After
    public void tearDown() {
        pausa(5);
        if (driver != null) {
            driver.quit();
        }
    }
}
