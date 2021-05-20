package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class AdministratorSpamCreateTest extends AcmePlannerTest {
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex,final String word ) {
		assert !StringHelper.isBlank(word);		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create spam word");

		super.fillInputBoxIn("word", word);
		super.clickOnSubmitButton("Create");

		super.clickOnMenu("Administrator", "List spam words");

		super.checkColumnHasValue(recordIndex, 10, word);
		super.checkColumnHasValue(recordIndex, 11, word);
		super.checkColumnHasValue(recordIndex, 12, word);
		super.checkColumnHasValue(recordIndex, 13, word);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex,final String word ) {
		assert !StringHelper.isBlank(word);		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create spam word");

		super.fillInputBoxIn("word", word);
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();
	}
}