package acme.entities.shouts;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InfoSheet extends DomainEntity {


	protected static final long	serialVersionUID	= 1L;


	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	protected LocalDate		infoDate;
	
	/*@NotBlank
	@Pattern(regexp = "([12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01]))", message = "default.error.conversion")
	protected String		infoDate;*/

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date		infoStamp;
	
	@Valid
	protected Money		infoMoney;
	

	protected Boolean	infoFlag;
	
	@OneToOne(mappedBy = "sheet")
	protected Shout shout;
	

}