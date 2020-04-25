package lk.my.store;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MyStoreClass {

	public static void main(String[] args) throws AWTException, Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// get the web site
		driver.get("https://www.mystore.lk/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Actions actions = new Actions(driver);

		// mouse hover
		WebElement overPh = driver.findElement(By.xpath("//a[contains(text(),'Phones & Tablets')]"));
		actions.moveToElement(overPh).build().perform();

		// take screenshot
		TakesScreenshot tk0 = (TakesScreenshot) driver;
		File s0 = tk0.getScreenshotAs(OutputType.FILE);
		File d0 = new File("C:\\project\\sshot0.png");
		FileUtils.copyFile(s0, d0);

		WebElement overShowAll = driver
				.findElement(By.xpath("//div[@id='department-phones-tablets']//div[1]//ul[1]//li[1]//a[1]"));
		actions.moveToElement(overShowAll).click().build().perform();

		// practice git
		System.out.println("developer changes one");
		System.out.println("developer changes two");
		System.out.println("developer changes three");
		System.out.println("developer changes four");
		// search for product

		WebElement searchBox = driver.findElement(By.xpath("//input[@id='search-input']"));
		searchBox.sendKeys("Promate VentGrip G1 Universal Smartphone Holder");
		searchBox.sendKeys(Keys.ENTER);

		WebElement clickSearched = driver.findElement(By.xpath("//div[@class='product-thumbnail']"));
		clickSearched.click();

		WebElement btnBuyNow = driver.findElement(By.xpath("//button[@class='btn btn-success btn-lg btn-buy-now']"));
		btnBuyNow.click();

		// static wait
		Thread.sleep(2000L);

		// take screenshot
		TakesScreenshot tk1 = (TakesScreenshot) driver;
		File s1 = tk1.getScreenshotAs(OutputType.FILE);
		File d1 = new File("C:\\project\\sshot1.png");
		FileUtils.copyFile(s1, d1);

		Thread.sleep(2000L);
		WebElement btnDismiss = driver.findElement(By.xpath("//a[@class='btn btn-md btn-link']"));
		btnDismiss.click();

		WebElement btnCart = driver.findElement(By.xpath("//a[@id='top-cart-button']"));
		actions.moveToElement(btnCart).build().perform();
		Thread.sleep(2000L);

		WebElement btnCheckout = driver
				.findElement(By.xpath("//a[@class='btn btn-sm btn-primary'][contains(text(),'Checkout')]"));
		btnCheckout.click();

		WebElement btnOutCheck = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
		btnOutCheck.click();

		WebElement txtFirstName = driver.findElement(By.xpath("//input[@id='txtEmail']"));
		txtFirstName.sendKeys(getData(1, 0));

		WebElement txtPassword = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		txtPassword.sendKeys(getData(1, 1));

		// take screenshot
		TakesScreenshot tk2 = (TakesScreenshot) driver;
		File s2 = tk2.getScreenshotAs(OutputType.FILE);
		File d2 = new File("C:\\project\\sshot2.png");
		FileUtils.copyFile(s2, d2);

		WebElement btnSignin = driver.findElement(By.xpath("//button[@class='btn btn-success btn-block']"));
		btnSignin.click();

		WebElement lnkTAC = driver.findElement(By.xpath("//a[contains(text(),'Terms and Conditions')]"));
		lnkTAC.click();

		// handling window
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentid = it.next();
		String Childid = it.next();
		driver.switchTo().window(Childid);
		Thread.sleep(2000L);
		driver.switchTo().window(parentid);

	}

	private static String getData(int rowNo, int cellno) throws Throwable {
		String v = null;
		File loc = new File("C:\\Users\\WeKay\\eclipse-workspace\\MyStore\\DataExcel\\Data.xlsx");
		FileInputStream st = new FileInputStream(loc);

		Workbook w = new XSSFWorkbook(st);
		Sheet s = w.getSheet("Sheet1");
		Row r = s.getRow(rowNo);
		Cell c = r.getCell(cellno);
		v = c.getStringCellValue();
		return v;
	}

}
