package acme.entities.shouts;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InfoSheet extends DomainEntity {


	protected static final long	serialVersionUID	= 1L;
	
	@NotBlank
	@Pattern(regexp = "(^\\w{2}\\d{2}-(\\d{2}\\d{2}\\d{2}))", message = "default.error.conversion")
	protected String		infoDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date		infoStamp;
	
	@Valid
	@NotNull
	protected Money		infoMoney;
	

	protected Boolean	infoFlag;
	
	@OneToOne(mappedBy = "sheet")
	protected Shout shout;
	

}