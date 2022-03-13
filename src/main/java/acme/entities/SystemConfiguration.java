package acme.entities;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class SystemConfiguration extends AbstractEntity {
	
	protected static final long serialVersionUID = 1L;
	
	protected String systemCurrency = "EUR";
	
	protected final List<String> acceptedCurrencies = Arrays.asList("EUR","USD","GBP");
	
	protected final List<String> strongSpamTerms_en = Arrays.asList("sex","hard core","viagra","cialis");
	
	protected final List<String> strongSpamTerms_es = Arrays.asList("sexo","hard core","viagra","cialis");
	
	protected final Double strongSpamThreshold = 0.1;
	
	protected final List<String> weakSpamTerms_en = Arrays.asList("sexy","nigeria","you've won","one million"); 
	
	protected final List<String> weakSpamTerms_es = Arrays.asList("sexy","nigeria","has ganado","un millón");
	
	protected final Double weakSpamThreshold = 0.25; 
	
	


}
