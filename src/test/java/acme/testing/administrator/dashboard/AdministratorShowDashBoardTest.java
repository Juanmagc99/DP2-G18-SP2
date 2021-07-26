package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorShowDashBoardTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveDashBoardShow(final String nPrivateTasks, final String nPublicTask, 
		final String nFinishedTasks, final String nCurrentTasks, final String tasks, final String workloads,
		final String ratioShoutsFlaggedTrue,final String ratioShoutsFlaggedFalse,
		final String ratioShoutsYear2020,final String averageSheetGroupByCurrency1,final String averageSheetGroupByCurrency2,
		final String deviationSheetGroupByCurrency1,final String deviationSheetGroupByCurrency2) {
		super.signIn("administrator","administrator");
		
		this.navigateHome();
		super.clickOnMenu("Administrator", "Dashboard");
		assert super.getCurrentUrl().equals("/administrator/dashboard/show");
		
		
		final String xpath_tabla1 = "//table[@id='dashboard']//tr[1]/td";
		final String xpath_tabla2 = "//table[@id='dashboard']//tr[2]/td";
		final String xpath_tabla3 = "//table[@id='dashboard']//tr[3]/td";
		final String xpath_tabla4 = "//table[@id='dashboard']//tr[4]/td";
		final String xpath_tabla5 = "//table[@id='dashboard']//tr[5]/td";
		final String xpath_tabla6 = "//table[@id='dashboard']//tr[6]/td";
		final String xpath_tabla7 = "//table[@id='ratio']//tr[1]/td";
		//final String xpath_tabla8 = "//table[@id='ratio']//tr[2]/td";
		final String xpath_tabla9 = "//table[@id='ratio']//tr[2]/td";
		final String xpath_tabla10 = "//table[@id='ratio']//tr[3]/td";
		final String xpath_tabla11 = "//table[@id='ratio']//tr[4]/td";
		final String xpath_tabla12 = "//table[@id='ratio']//tr[5]/td";
		final String xpath_tabla13 = "//table[@id='ratio']//tr[6]/td";
		
		assert super.locateOne(By.xpath(xpath_tabla1)).getText().equals(nPrivateTasks);
		assert super.locateOne(By.xpath(xpath_tabla2)).getText().equals(nPublicTask);
		assert super.locateOne(By.xpath(xpath_tabla3)).getText().equals(nFinishedTasks);
		assert super.locateOne(By.xpath(xpath_tabla4)).getText().equals(nCurrentTasks);
		assert super.locateOne(By.xpath(xpath_tabla5)).getText().equals(tasks);
		assert super.locateOne(By.xpath(xpath_tabla6)).getText().equals(workloads);
		assert super.locateOne(By.xpath(xpath_tabla7)).getText().equals(ratioShoutsFlaggedTrue);
		//assert super.locateOne(By.xpath(xpath_tabla8)).getText().equals(ratioShoutsFlaggedFalse);
		assert super.locateOne(By.xpath(xpath_tabla9)).getText().equals(ratioShoutsYear2020);
		assert super.locateOne(By.xpath(xpath_tabla10)).getText().equals(averageSheetGroupByCurrency1);
		assert super.locateOne(By.xpath(xpath_tabla11)).getText().equals(averageSheetGroupByCurrency2);
		assert super.locateOne(By.xpath(xpath_tabla12)).getText().equals(deviationSheetGroupByCurrency1);
		assert super.locateOne(By.xpath(xpath_tabla13)).getText().equals(deviationSheetGroupByCurrency2);
		
		
		super.signOut();
	}
		
}
