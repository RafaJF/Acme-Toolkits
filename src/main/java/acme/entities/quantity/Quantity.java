package acme.entities.quantity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.entities.Toolkit;
import acme.entities.item.Item;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quantity extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@Min(1)
	protected int quantity;
	
	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne
	protected Item item;
	
	@NotNull
	@Valid
	@ManyToOne
	protected Toolkit toolkit;
	
}