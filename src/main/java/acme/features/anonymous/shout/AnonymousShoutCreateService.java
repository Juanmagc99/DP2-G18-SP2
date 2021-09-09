package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.ExtraSheet;
import acme.entities.shouts.Shout;
import acme.features.administrator.configuration.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state 
	
	protected AnonymousShoutRepository repository;
	protected SpamService spamService;
	
	@Autowired
	protected AnonymousShoutCreateService(final AnonymousShoutRepository repository, final SpamService spamService) {
		this.repository = repository;
		this.spamService = spamService;
	}

	// AbstractCreateService<Administrator, Shout> interface 

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "author", "text", "info", "extrasheet.xomen", "extrasheet.budget", "extrasheet.important", "extrasheet.deadline");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		final Date moment;
		final ExtraSheet extrasheet;
		
		extrasheet = new ExtraSheet();
		moment = new Date(System.currentTimeMillis() - 1);
		

		//Creating relation
		result = new Shout();
		result.setMoment(moment);
		result.setExtrasheet(extrasheet);
		
		
		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(entity.getAuthor() != null && entity.getText() != null) {
			errors.state(request, this.spamService.validateNoSpam(entity.getAuthor()), "author", "anonymous.shout.form.label.spam", "spam");
			errors.state(request, this.spamService.validateNoSpam(entity.getText()), "text", "anonymous.shout.form.label.spam", "spam");			
		}
		
		
		if(!errors.hasErrors("extrasheet.budget")){
			//Check if the amount is positive
            errors.state(request, entity.getExtrasheet().getBudget().getAmount() >=0, "extrasheet.budget", "anonymous.shout.extrasheet.amount");
        }
		
		if(!errors.hasErrors("extrasheet.xomen")){
			
			/*Check if date matches todays date*/
			final String dateStringFull = entity.getExtrasheet().getXomen();
			final String[] dateStringSplited = dateStringFull.split("-");
			final String dateFech = dateStringSplited[1];
			final char[] dateFechSeparated = dateFech.toCharArray();
			
			final String yearString = String.valueOf(dateFechSeparated[0])+String.valueOf(dateFechSeparated[1]);
			final  Integer yearInteger = Integer.valueOf(yearString);
			
			final String monthString = String.valueOf(dateFechSeparated[2])+String.valueOf(dateFechSeparated[3]);
			final Integer monthInteger = Integer.valueOf(monthString);
			
			final String dayString = String.valueOf(dateFechSeparated[4])+String.valueOf(dateFechSeparated[5]);
			final Integer dayInteger = Integer.valueOf(dayString);
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			final LocalDate today = LocalDate.now();
			final String todayYearString = String.valueOf(today.getYear());
			final char[] todayYearChar = todayYearString.toCharArray();
			final String todayYearShortString = String.valueOf(todayYearChar[2])+String.valueOf(todayYearChar[3]);
			final Integer todayYearInteger = Integer.valueOf(todayYearShortString);
			
			final Boolean yearCorrect = yearInteger == todayYearInteger;
			final Boolean monthCorrect = monthInteger == today.getMonthValue();
			final Boolean dayCorrect = dayInteger == today.getDayOfMonth();
			
			errors.state(request, yearCorrect&&monthCorrect&&dayCorrect, "extrasheet.xomen", "anonymous.shout.extrasheet.xomen");
			
			/*Check if dates and id are UNIQUE*/
			final Boolean isUnique = this.repository.isUnique(entity.getExtrasheet().getXomen()) < 1;
			
			errors.state(request, isUnique, "extrasheet.xomen", "anonymous.shout.extrasheet.xomen");
			
		}
		
		if(!errors.hasErrors("extrasheet.budget")) {
			final Money money = entity.getExtrasheet().getBudget();
			final String currency = money.getCurrency();
			
			//Only DOLLARS and EUROS are allowed
			errors.state(request, (currency.equals("EUR") || currency.equals("USD") || currency.equals("GBP")), "extrasheet.budget", "anonymous.shout.extrasheet.currency");	
		}
		
		if(!errors.hasErrors("extrasheet.deadline") && entity.getExtrasheet().getDeadline()!=null) {
            final Date weekLess = entity.getExtrasheet().getDeadline();
            final Calendar minimumDeadline= Calendar.getInstance();
            minimumDeadline.add(Calendar.DATE, 7);
                        
            errors.state(request,weekLess.after(minimumDeadline.getTime()), "extrasheet.deadline", "anonymous.shout.extrasheet.deadline");
        }
		
		if(entity.getInfo() != null)	errors.state(request, this.spamService.validateNoSpam(entity.getInfo()), "info", "anonymous.shout.form.label.spam", "spam");
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity.getExtrasheet());
		this.repository.save(entity);
	}

}