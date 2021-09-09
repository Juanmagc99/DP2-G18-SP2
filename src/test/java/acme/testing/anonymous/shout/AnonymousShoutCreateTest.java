package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest  extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex,final String author,final String text,final String info, 
		final String xomen, final String budget, final String important, final String deadline) {
		assert !StringHelper.isBlank(author);		
		assert !StringHelper.isBlank(text);	
		
		super.clickOnMenu("Anonymous", "Shout!");
		

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("extrasheet.xomen", xomen);
		super.fillInputBoxIn("extrasheet.budget", budget);
		super.fillInputBoxIn("extrasheet.important", important);
		super.fillInputBoxIn("extrasheet.deadline", deadline);
		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "List-Shout");
		
		final String budgetsp[] = budget.split(" ");
		
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, xomen);
		super.checkColumnHasValue(recordIndex, 5, budgetsp[0]);
		super.checkColumnHasValue(recordIndex, 6, budgetsp[1]);
		super.checkColumnHasValue(recordIndex, 7, important);
		super.checkColumnHasValue(recordIndex, 8, deadline);



	
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex,final String author,final String text,final String info, 
		final String xomen, final String budget, final String important, final String deadline) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
	
	

}
