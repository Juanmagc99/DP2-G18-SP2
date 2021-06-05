package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.InfoSheet;
import acme.entities.shouts.Shout;
import acme.features.administrator.configuration.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
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
		
		sheet.setInfoStamp(moment);

		result = new Shout();
		result.setMoment(moment);
		result.setSheet(sheet);
		sheet.setShout(result);
		
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
		
		if(entity.getSheet().getInfoDate() != null) {
			final String sheetDateString = entity.getSheet().getInfoDate();
			final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			final LocalDate sheetDate = LocalDate.parse(sheetDateString, dtf);
			
			final LocalDate today = LocalDate.now();
			
			System.out.println(sheetDateString);
			System.out.println(sheetDate);
			System.out.println(today);
			
			errors.state(request, !sheetDate.equals(today), "sheet.infoDate", "anonymous.shout.form.label.infoDate");

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
		
		
		entity.getSheet().setInfoStamp(moment);
		
		this.repository.save(entity.getSheet());
		this.repository.save(entity);
	}

}