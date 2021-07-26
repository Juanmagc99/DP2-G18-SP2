package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.InfoSheet;
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
		
		request.unbind(entity, model, "author", "text", "info", "sheet.infoDate", "sheet.infoMoney", "sheet.infoFlag");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		final Date moment;
		final InfoSheet sheet;
		
		sheet = new InfoSheet();
		moment = new Date(System.currentTimeMillis() - 1);
		
		final LocalDate deadline = LocalDate.now();
		final LocalDate deadlineNextWeek = deadline.plusWeeks(1);
		final Date d = java.sql.Date.valueOf(deadlineNextWeek);
		
		
		sheet.setInfoStamp(d);

		//Creating relation
		result = new Shout();
		result.setMoment(moment);
		result.setSheet(sheet);
		
		
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
		
		
		if(!errors.hasErrors("sheet.infoMoney")){
			//Check if the amount is positive
            errors.state(request, entity.getSheet().getInfoMoney().getAmount() >=0, "sheet.infoMoney", "anonymous.shout.sheet.amount");
        }
		
		if(!errors.hasErrors("sheet.infoDate")){
			final String sheetDateString = entity.getSheet().getInfoDate();
			final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            final LocalDate sheetDate = LocalDate.parse(sheetDateString, dtf);
            
            //Get current date as LocalDate
			final LocalDate today = LocalDate.now();
			
			errors.state(request, sheetDate.isEqual(today), "sheet.infoDate", "anonymous.shout.sheet.date.current");
			
			//Check if date is unique
			final Collection<Shout> allShouts = this.repository.findMany();
			
			//INFODATE IS STRING
			final Set<String> setDates = new HashSet<>();
			Boolean uniqueDate = true;
			
			//Add all dates of existing Shouts to a Set
			for (final Shout s : allShouts) {
				setDates.add(s.getSheet().getInfoDate()); 			
			}
			
			//INFODATE IS STRING
			final String sDate = entity.getSheet().getInfoDate();
			
			
			//Tries to add this new date to the Set. Sets don´t allow duplicates, so if add fails, is NOT unique
			if (!setDates.add(sDate)){ 								
				uniqueDate = false;
			}
			
			errors.state(request, uniqueDate, "sheet.infoDate", "anonymous.shout.sheet.date.duplicate");
		}
		
		if(!errors.hasErrors("sheet.infoMoney")) {
			final Money money = entity.getSheet().getInfoMoney();
			final String currency = money.getCurrency();
			
			//Only DOLLARS and EUROS are allowed
			errors.state(request, (currency.equals("EUR") || currency.equals("USD")), "sheet.infoMoney", "anonymous.shout.sheet.currency");	
		}
		
		if(entity.getInfo() != null)	errors.state(request, this.spamService.validateNoSpam(entity.getInfo()), "info", "anonymous.shout.form.label.spam", "spam");
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		
		final LocalDate deadline = LocalDate.now();
		final LocalDate deadlineNextWeek = deadline.plusWeeks(1);
		final Date d = java.sql.Date.valueOf(deadlineNextWeek);
		
		
		entity.getSheet().setInfoStamp(d);
		
		this.repository.save(entity.getSheet());
		this.repository.save(entity);
	}

}