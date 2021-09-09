package acme.entities.shouts;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExtraSheet extends DomainEntity {


	protected static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@Pattern(regexp = "(^\\w{3}\\-\\d{8}$)", message = "default.error.conversion")
	protected String		xomen;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date		deadline;
	
	@Valid
	@NotNull
	protected Money		budget;
	
	protected Boolean	important;
	
	@OneToOne(mappedBy = "extrasheet")
	protected Shout shout;
	

}