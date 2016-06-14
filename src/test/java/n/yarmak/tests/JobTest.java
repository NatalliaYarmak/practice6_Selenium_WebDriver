package n.yarmak.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;

import n.yarmak.pages.JobPage;
import n.yarmak.pages.JobSearchResultPage;
import n.yarmak.pages.HomePage;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JobTest extends Assert {
	private WebDriver driver;

	/**
	 * Конфигурирование теста: запуск браузера и переход к главной странице
	 * @param appUrl	URL к главной странице
	 */
	@BeforeTest
	@Parameters({"appUrl"})
	public void setUp(String appUrl) {
		driver = new FirefoxDriver();
		System.out.format("Открыли браузер"); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.get(appUrl);
		System.out.format("Перешли на страницу \"%s\"%n", appUrl);
	}
	
	/**
	 * Очистка конфигурации по завершении теста: закрытие браузера
	 */
	@AfterTest
	public void tearDown() {
		System.out.format("Закрыли браузер%n");
		driver.close();
	}
	
	/**
	 * В тесте осуществляется поиск вакансий по заданному запросу request
	 * и проверка соответствия результатов поиска вакансий на соответствие 
	 * регулярному выражению regexp.
	 * В случае успешного выполнения в консоль выводится количество и 
	 * перечень вакансий, соответствующих запросу.
	 * Если по результатам поиска или проверки на соответствие не будет
	 * найдено ни одной подходящей вакансии, то тест "упадёт". 
	 * @param request 	текст запроса
	 * @param regexp	регулярное выражение, для прооверки результатов поиска
	 */
	@Test
	@Parameters({ "request", "regexp" })
	public void testJobSearchResults(String request, String regexp) {
		HomePage homePage = new HomePage(driver);
		System.out.format("Получили доступ к главной странице%n");	
		
		JobPage jobPage = homePage.navigateToJob();
		System.out.format("Получили доступ к странице \"Работа\"%n");
		
		JobSearchResultPage jobSearchResultPage = jobPage.search(request);
		System.out.format("Получили доступ к странице с результатами поиска%n");
		
		List<String> results = jobSearchResultPage.verifySearchResults(regexp);
		System.out.format("Проверили соответствие результатов поиска запросу%n");
		
		if (results.isEmpty()) {
			System.out.format("%nНет результатов, соответствующих запросу%n%n");
			fail();
		}
		
		String testResult = String.format("%nНайдено %d вакансий, соответствующих запросу:%n", results.size());
		for (String result : results) {
			testResult = String.format("%s%s%n",testResult,result);
		}
		System.out.format("%s%n", testResult);
	}
}
