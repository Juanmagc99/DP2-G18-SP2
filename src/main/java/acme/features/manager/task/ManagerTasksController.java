package acme.features.manager.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Manager;

@Controller
@RequestMapping("/manager/task")
public class ManagerTasksController extends AbstractController<Manager, Task>{
	
		@Autowired
		protected ManagerTaskCreateService createService;
		
		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.CREATE, this.createService);
		}
}