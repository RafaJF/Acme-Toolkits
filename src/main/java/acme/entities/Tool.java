package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Tool extends AbstractEntity {
protected static final long serialVersionUID = 1L;
@NotBlank
@Max(101)
protected String nombre;
@Column(unique=true)
@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")

protected String codigo;
@NotBlank
@Max(101)
protected String tecnologia;
@NotBlank
@Max(256)
protected String descripcion;
@Min(0)
protected Double precio;
@URL
protected String enlace;
}
