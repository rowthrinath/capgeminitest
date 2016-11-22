package automatedtests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by rowthrinathnatarajan on 21/11/2016.
 */
@FixMethodOrder(MethodSorters.JVM)

public class loginpage {
    private static ChromeDriver driver;
    WebElement element;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeClass
    public static void openBrowser() throws Exception{

        String home = System.getProperty("user.home");
        File driverpath=new File(home+"/restful-booker-platform/drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", String.valueOf(driverpath));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public  void launchApp () throws InterruptedException
    {
        driver.get("http://localhost:3003/");
        Thread.sleep(1000);

    }


    @Test
    public  void loginToApp() throws InterruptedException
    {
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("div.container-fluid > ul > li:nth-child(2) > a")).click();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("doLogin")).click();

        do {

            if (driver.findElement(By.id("hotelName")).isDisplayed()) {
                System.out.println("Logged in successfully");
                break;
            }

        }while (driver.findElement(By.id("hotelName")).isDisplayed());


    }

    @Test
    public void createHotel()
    {

        driver.findElement(By.id("hotelName")).sendKeys("Hotel1");
        driver.findElement(By.id("address")).sendKeys("address1");
        driver.findElement(By.id("owner")).sendKeys("owner1");
        driver.findElement(By.id("phone")).sendKeys("phone1");
        driver.findElement(By.id("email")).sendKeys("email1");
        driver.findElement(By.id("createHotel")).click();

        do {

            if (driver.findElement(By.cssSelector("div.container > div:nth-child(3) > div.col-sm-1 >span")).isDisplayed()) {
                System.out.println("Hotel added successfully");
                break;
            }

        }while (driver.findElement(By.cssSelector("div.container > div:nth-child(3) > div.col-sm-1 >span")).isDisplayed());

    }

    @Test
    public void deleteHotel()
    {
        driver.findElement(By.cssSelector("div.container > div:nth-child(3) > div.col-sm-1 >span")).click();
        System.out.println("Hotel deleted successfully");
    }

    @Test
    public void createMultiplehotels() throws InterruptedException {
        String [] hotelnames = {"Hilton", "Ramada", "Scandia", "Great Western"};
        String [] addresses= {"London", "Paris", "Italy", "Germany"};
        String [] owners= {"owner1", "owner2", "owner3", "owner4"};
        String [] phonenumbers = {"123", "456", "789", "012"};
        String [] emails = {"email1@email.com", "email2@email.com", "email3@email.com", "email4@email.com"};

    for (int i=0;i<4;i++)
    {
        Thread.sleep(500);
        driver.findElement(By.id("hotelName")).sendKeys(hotelnames[i]);
        driver.findElement(By.id("address")).sendKeys(addresses[i]);
        driver.findElement(By.id("owner")).sendKeys(owners[i]);
        driver.findElement(By.id("phone")).sendKeys(phonenumbers[i]);
        driver.findElement(By.id("email")).sendKeys(emails[i]);
        driver.findElement(By.id("createHotel")).click();
        Thread.sleep(500);
        System.out.println(hotelnames[i] +" Added");
    }


    }


    @AfterClass
    public static void closeBrowser() throws Exception{
       driver.quit();
    }

}
