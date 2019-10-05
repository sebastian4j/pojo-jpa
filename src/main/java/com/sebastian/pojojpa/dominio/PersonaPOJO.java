package com.sebastian.pojojpa.dominio;

import lombok.Getter;
import lombok.Setter;

/**
 * usado para realizar la conversion desde la query nativa a un pojo.
 *
 * @author Sebastián Ávila A.
 */
@Getter @Setter
public class PersonaPOJO {

    private String nn;
    private String aa;
    
    public PersonaPOJO() {}

    public PersonaPOJO(String nn, String aa) {
        this.nn = nn;
        this.aa = aa;
    }
}
