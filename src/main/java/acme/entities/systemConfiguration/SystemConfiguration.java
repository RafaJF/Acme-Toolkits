
package acme.entities.systemConfiguration;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class SystemConfiguration extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	@NotBlank
	protected String			systemCurrency;

	@NotBlank
	protected String			acceptedCurrencies;

	@NotBlank
	protected String			strongSpamTermsEn;

	@NotBlank
	protected String			strongSpamTermsEs;

	
	@Range(min = 0, max = 100)
	protected double			strongThreshold;

	@NotBlank
	protected String			weakSpamTermsEn;

	@NotBlank
	protected String			weakSpamTermsEs;

	
	@Range(min = 0, max = 100)
	protected double			weakThreshold;

}
