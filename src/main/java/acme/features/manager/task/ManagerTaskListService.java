package acme.features.manager.task;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagerTaskListService implements AbstractListService<Manager, Task>{
	
	// Internal state

		@Autowired
		protected ManagerTaskRepository repository;


		// AbstractListService<Manager, Task> interface 

		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<Task> request, final Task entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title", "start_date", "end_date", "workload", "description", "op_link");
		}

		@Override
		public Collection<Task> findMany(final Request<Task> request) {
			assert request != null;

			final Collection<Task> result;
			Principal principal;
			principal = request.getPrincipal();

			Calendar  calendar;
			calendar=Calendar.getInstance();   //get the Calendar

			result = this.repository.findNonFinishedTaskByManagerId(calendar.getTime(), principal.getActiveRoleId());
			
			return result;
		}

}
