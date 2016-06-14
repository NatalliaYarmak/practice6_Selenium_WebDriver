package n.yarmak.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JobSearchResultPage {

	private WebDriver driver;
	private By searchResultsLocator = By.xpath("//td/div/descendant::div/div/div[2]/div[1]/a");

	public JobSearchResultPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * В методе осуществляется проверка результатов поиска на соответствие
	 * заданному регулярному выражению regexp.
	 * Метод возвращает список строк, соответствующих регулярному выражению.
	 * Проверка осуществляется только для первой страницы результатов поиска.
	 * @param regexp	регулярное выражение для проверки результатов поиска
	 * @return			список соответствующих результатов поиска
	 */
	public List<String> verifySearchResults(String regexp) {
		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		List<WebElement> searchResults = driver.findElements(searchResultsLocator);
		List<String> compilantResults = new ArrayList<String>();
		for (WebElement element : searchResults) {
			if (pattern.matcher(element.getText()).matches()) {
				compilantResults.add(element.getText());
			}
		}
		return compilantResults;
	}

}
