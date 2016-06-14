package n.yarmak.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private String jobText = "Работа";
	private WebDriver driver;
	 
    public HomePage(WebDriver driver) {
		this.driver = driver;
	}

    /**
     * Метод осуществляет переход с главной страницы к странице "Работа" 
     * @return	страница "Работа"
     */
	public JobPage navigateToJob() {
		navigateMenu(jobText).click();
		System.out.format("Перешли по линку \"Работа\"%n");
		return new JobPage(driver);
	}

	/**
	 * Метод позволяет получать доступ к элементам главного меню
	 * на странице по тексту ссылки на эти элементы.
	 * @param linkText	текст ссылки для получения локатора
	 * @return 			элемент меню
	 */
	private WebElement navigateMenu(String linkText) {
		return driver.findElement(By.linkText(linkText));
	}  
}
