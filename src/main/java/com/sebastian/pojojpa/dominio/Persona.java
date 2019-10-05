package com.sebastian.pojojpa.dominio;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * entidad que representa una persona.
 * @author Sebastián Ávila A.
 */
@SqlResultSetMapping(name = "personapojo",
        classes = {
            @ConstructorResult(targetClass = PersonaPOJO.class,
                    columns = {
                        @ColumnResult(name = "nn", type = String.class),
                        @ColumnResult(name = "aa", type = String.class)})})
@Getter @Setter
@Entity
@Table(schema = "jpa", name = "persona")
public class Persona {

    @Id
    private int id;
    private String nombre;
    private String apellido;
}
