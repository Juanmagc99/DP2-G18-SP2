/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
				"numPrivateTask", "numPublicTask", // 
				"numFinishedTask", "numCurrentTask", //
				"numExecutions", "numWorkloads","ratioShoutsFlaggedTrue", "ratioShoutsFlaggedFalse",
				"ratioShoutsYear2020", "averageSheetGroupByCurrency1", "averageSheetGroupByCurrency2",
				"deviationSheetGroupByCurrency1", "deviationSheetGroupByCurrency2");
		}
	
	public String parsenumExecutions(final String s) {
		final String[] trozo =s.split(",");
		final StringBuilder bld = new StringBuilder();
		for (int i = 0; i < trozo.length; i++) {
			final String parte = trozo[i];
			
			if (i == trozo.length-1) {
				bld.append( parte + " dd. ");
			}else {
				bld.append( parte + " dd, ");
			}
			
			
		}
		
		return bld.toString();
	}
	
	public String parseWorkload(final String s) {
		final String[] trozo =s.split(",");
		final StringBuilder bld = new StringBuilder();
		for (int i = 0; i < trozo.length; i++) {
			final String parte = trozo[i];
			final int indexOfDecimal = parte.indexOf(".");
			long horas = Long.parseLong(parte.substring(0, indexOfDecimal));
			long minutos = Long.parseLong(parte.substring(indexOfDecimal+1));
			
			if (minutos>=60) {
				minutos = minutos-60;
				horas = horas + 1;
			}
			
			if (i == trozo.length-1) {
				bld.append(horas + " hrs. y " + minutos + " min. ");
			}else {
				bld.append(horas + " hrs. y " + minutos + " min, ");
			}
			
			
		}
		
		return bld.toString();
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		Integer numPrivateTask;
		Integer numPublicTask;
		Integer numFinishedTask;
		Integer numCurrentTask;
		String numExecutions;
		String numWorkloads;
		
		
		final Double ratioShoutsFlaggedTrue;
		final Double ratioShoutsFlaggedFalse;
		final Double ratioShoutsYear2020;
		final Double averageSheetGroupByCurrency1;
		final Double averageSheetGroupByCurrency2;
		final Double deviationSheetGroupByCurrency1;
		final Double deviationSheetGroupByCurrency2;
		

		numPrivateTask = this.repository.numPrivateTask();
		numPublicTask = this.repository.numPublicTask();
		numFinishedTask = this.repository.numFinishedTask();
		numCurrentTask = this.repository.numCurrentTask();
		numExecutions = this.repository.numExecutions();
		numWorkloads = this.repository.numWorkloads();
		
		ratioShoutsFlaggedTrue = this.repository.ratioOfShoutsFlaggedTrue();
		ratioShoutsFlaggedFalse = this.repository.ratioOfShoutsFlaggedFalse();
		ratioShoutsYear2020 = this.repository.ratioOfShoutsYear2020();
		

		result = new Dashboard();
		result.setNumPrivateTask(numPrivateTask);
		result.setNumPublicTask(numPublicTask);
		result.setNumFinishedTask(numFinishedTask);
		result.setNumCurrentTask(numCurrentTask);
		result.setNumExecutions(this.parsenumExecutions(numExecutions));
		result.setNumWorkloads(this.parseWorkload(numWorkloads));
		
		final List<Double> averageSheetGroupByCurrency = this.repository.averageSheetGroupByCurrency();
		final List<Double> deviationSheetGroupByCurrency = this.repository.deviationSheetGroupByCurrency();
	
		averageSheetGroupByCurrency1 = averageSheetGroupByCurrency.get(0);
		averageSheetGroupByCurrency2 =averageSheetGroupByCurrency.get(1);
		deviationSheetGroupByCurrency1 = deviationSheetGroupByCurrency.get(0);
		deviationSheetGroupByCurrency2 = deviationSheetGroupByCurrency.get(1);
		
		result.setRatioShoutsFlaggedTrue(ratioShoutsFlaggedTrue);
		result.setRatioShoutsFlaggedFalse(ratioShoutsFlaggedFalse);
		result.setRatioShoutsYear2020(ratioShoutsYear2020);
		result.setAverageSheetGroupByCurrency1(averageSheetGroupByCurrency1);
		result.setAverageSheetGroupByCurrency2(averageSheetGroupByCurrency2);
		result.setDeviationSheetGroupByCurrency1(deviationSheetGroupByCurrency1);
		result.setDeviationSheetGroupByCurrency2(deviationSheetGroupByCurrency2);
	
		return result;
	}

}
