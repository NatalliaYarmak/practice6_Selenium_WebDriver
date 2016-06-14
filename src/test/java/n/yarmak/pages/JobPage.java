package n.yarmak.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JobPage {
	private By searchTextBoxLocator = By.xpath("//input[@name='text']");
	private By searchBtnLocator = By.xpath("//button[@type='submit']");
	private WebDriver driver;
	private WebElement searchInputElement;
	private WebElement searchBtnElement;

	public JobPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * В методе осуществляется ввод заданного текста запроса text в поле ввода
	 * и нажатие на кнопку "Найти".
	 * По нажатию на кнопку "Найти" осуществляется переход к странице с 
	 * результатами поиска.
	 * @param text	текст запроса
	 * @return 		страница с результатами поиска
	 */
	public JobSearchResultPage search(String text) {
		searchInputElement = driver.findElement(searchTextBoxLocator);
		searchInputElement.clear();
		searchInputElement.sendKeys(text);
		System.out.format("Ввели запрос \"%s\" в строку поиска%n", text);

		searchBtnElement = driver.findElement(searchBtnLocator);
		searchBtnElement.click();
		System.out.format("Нажали кнопку \"Найти\"%n");

		return new JobSearchResultPage(driver);
	}
}
