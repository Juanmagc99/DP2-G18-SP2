package acme.features.anonymous.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/task")
public class AnonymousTasksController extends AbstractController<Anonymous, Task>{
	
		@Autowired
		protected AnonymousTaskCreateService createService;
		
		@Autowired
		protected AnonymousTaskListService	listService;
		
		@Autowired
		protected AnonymousTaskShowService	showService;
		
		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.LIST, this.listService);
			super.addBasicCommand(BasicCommand.CREATE, this.createService);
			super.addBasicCommand(BasicCommand.SHOW, this.showService);
		}
}
