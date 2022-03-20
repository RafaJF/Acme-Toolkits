package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	protected Status			status;

	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique = true)
	protected String			code;

	@NotBlank
	@Length(max = 255)
	protected String 			legalStuff;

	@Min(0)
	@Valid
	protected Money				budget;

	@Temporal(TemporalType.DATE)
	protected Date				startDate;

	@Temporal(TemporalType.DATE)
	protected Date				endDate;

	@URL
	protected String			moreInfo;

	// Derived attributes -----------------------------------------------------


	// Relationships ----------------------------------------------------------
	
	@JoinColumn(name = "patronId", referencedColumnName = "id")
	protected Patron			patron;

	@JoinColumn(name = "inventorId", referencedColumnName = "id")
	protected Inventor			inventor;
}