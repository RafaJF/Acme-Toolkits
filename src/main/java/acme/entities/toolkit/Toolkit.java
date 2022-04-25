
package acme.entities.toolkit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;
	
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@NotBlank
	protected String			code;
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String			title;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String			description;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String			assamblyNotes;
	
	@URL
	protected String			url;
	
	@Transient
	protected Money totalPrice;
	
	protected boolean published;
	
	@NotNull
	@ManyToOne(optional=false)
	@Valid
	protected Inventor inventor;
}
