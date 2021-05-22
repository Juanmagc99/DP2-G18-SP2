package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamUpdateTest extends AcmePlannerTest {
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updatePositive(final int recordIndex, final String word) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "List spam words");		
		super.checkColumnHasValue(recordIndex, 0, word);
		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("word", word);	
		super.clickOnSubmitButton("Update");
		super.checkColumnHasValue(recordIndex, 0, word);
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("word", word);
		super.signOut();
	}
	


}
