package acme.entities.moneyExchange;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Embeddable
@Getter
@Setter
public class MoneyExchange extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Valid
	public Money	source;

	@NotBlank
	public String	currencyTarget;

	// Response attributes ----------------------------------------------------

	@Valid
	public Money	target;

	public Date		date;

}