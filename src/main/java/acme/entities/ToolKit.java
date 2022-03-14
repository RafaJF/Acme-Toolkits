package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class ToolKit extends AbstractEntity{
protected static final long serialVersionUID = 1L;
//(patrón “^[AZ]{3}-[0-9]{3}(-[AZ])?$”, único)
@Column(unique = true)
@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
protected String codigo;
@NotBlank
@Max(101)
@NotBlank
@Max(101)
protected String titulo;
@NotBlank
@Max(256)
protected String descripcion;
@NotBlank
@Max(256)
protected String notasDeMontaje;
@URL
protected String enlace;
//No me queda muy clara la descripcion de la tarea, pero supongo que al ser
// un kit de herramientas deberia llevar una relacion ManyToOne de herramientas?
//@NotNull
//@Valid
//@ManyToOne(optional = false)
//protected Tool tool;	
}
