
package acme.entities.systemConfiguration;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class SystemConfiguration extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	@NotNull
	protected String			systemCurrency;

	@NotNull
	protected String			acceptedCurrencies;

	@NotNull
	protected String			strongSpamTermsEn;

	@NotNull
	protected String			strongSpamTermsEs;

	
	@Range(min = 0, max = 100)
	protected double			strongThreshold;

	@NotNull
	protected String			weakSpamTermsEn;

	@NotNull
	protected String			weakSpamTermsEs;

	
	@Range(min = 0, max = 100)
	protected double			weakThreshold;

}
