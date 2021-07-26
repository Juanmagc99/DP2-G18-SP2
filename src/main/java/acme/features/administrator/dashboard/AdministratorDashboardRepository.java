/*
 * AdministratorDashboardRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select COUNT(t) from Task t where t.is_private=TRUE")
	Integer numPrivateTask();
	
	@Query("select COUNT(t) from Task t where t.is_private=FALSE")
	Integer numPublicTask();
	
	@Query("select COUNT(t) from Task t where t.end_date<=CURDATE()")
	Integer numFinishedTask();
	
	@Query("select COUNT(t) from Task t where t.end_date>CURDATE()")
	Integer numCurrentTask();

	@Query("select round(avg(datediff(t.end_date, t.start_date)),2), round(stddev(datediff(t.end_date, t.start_date)),2), round(min(datediff(t.end_date, t.start_date)),2), round(max(datediff(t.end_date, t.start_date)),2) from Task t")
	String numExecutions();
	
	
	@Query("select round(avg(t.workload),2), round(stddev(t.workload),2), round(min(t.workload),2), round(max(t.workload),2) from Task t")
	String numWorkloads();
	
	// DASHBOARD PARA Sheet
	
	//the ratio of shouts whose Sheet Flag were flagged as True
	@Query("select 1.0 * count(a) / (select count(b) from Shout b) from Shout a where a.sheet.infoFlag = true")
	Double ratioOfShoutsFlaggedTrue();
			
	@Query("select 1.0 * count(a) / (select count(b) from Shout b) from Shout a where a.sheet.infoFlag = false")
	Double ratioOfShoutsFlaggedFalse();
			
	//the ratio of shouts whose Sheet have XXX (Date con año 2020)
	@Query("select 1.0 * count(a) / (select count(b) from Shout b) from Shout a where a.sheet.infoMoney.amount = 0")
	Double ratioOfShoutsYear2020();
			
	// the average and the standard deviation of the Sheet grouped by currency
	@Query("select avg(s.infoMoney.amount) from InfoSheet s group by s.infoMoney.currency")
	List<Double> averageSheetGroupByCurrency();
			
	@Query("select stddev(s.infoMoney.amount) from InfoSheet s group by s.infoMoney.currency")
	List<Double> deviationSheetGroupByCurrency();

}
