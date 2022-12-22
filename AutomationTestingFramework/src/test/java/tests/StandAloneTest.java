package tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("userEmail")).sendKeys("aparna123pandey@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");

		driver.findElement(By.id("login")).click();
		
		//capture the product list...
		//instead of iterating over list we are using streams here....
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> l= driver.findElements(By.cssSelector(".mb-3"));
		
		
		WebElement ele= l.stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		ele.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role='alertdialog'][aria-label='Product Added To Cart']")));
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
		
		
		
		
		
	}

}
