package com.sebastian.pojojpa;

import com.sebastian.pojojpa.dominio.Persona;
import com.sebastian.pojojpa.dominio.PersonaPOJO;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * pruebas para obtener el pojo de distintas maneras.
 *
 * @author Sebastián Ávila A.
 */
public class ContextoPersistenciaTest {

    @Test
    void obtieneContextoPersistenciaTest() {
        try (final var ps = PostgresContainerSetUp.cargar()) {
            PostgresContainerSetUp.comenzar(ps);
            // obtener el contexto de persistencia
            final var em = ContextoPersistencia.obtener();
            // obtener una entidad administrada
            final var persona = em.find(Persona.class, 1);
            validarPersona(persona);
            // obtener el pojo con una query nativa y usando ConstructorResult
            List<PersonaPOJO> personas = em
                    .createNativeQuery("select nombre nn, apellido aa from jpa.persona", "personapojo")
                    .getResultList();
            assertThat(personas.size()).isEqualTo(1);
            validarPersona(personas.get(0));
            // obtener el pojo con una query usando proyección
            personas = em
                    .createQuery("select new com.sebastian.pojojpa.dominio.PersonaPOJO(p.nombre, p.apellido) from Persona p")
                    .getResultList();
            validarPersona(personas.get(0));
            // realizar la transformación
            final List<Object[]> obj = em
                    .createNativeQuery("select nombre nn, apellido aa from jpa.persona")
                    .getResultList();
            validarPersona(obj.get(0));
            em.close();
        }
    }
    private void validarPersona(final Object[] persona) {
        final var pj = new PersonaPOJO();
        pj.setNn((String) persona[0]);
        pj.setAa((String) persona[1]);
        validarPersona(pj);
    }
    
    private void validarPersona(final Persona p) {
        assertThat(p).isNotNull();
        assertThat(p.getId()).isEqualTo(1);
        assertThat(p.getNombre()).isEqualTo("Sebastián");
        assertThat(p.getApellido()).isEqualTo("Ávila");
    }

    private void validarPersona(final PersonaPOJO p) {
        assertThat(p).isNotNull();
        assertThat(p.getNn()).isEqualTo("Sebastián");
        assertThat(p.getAa()).isEqualTo("Ávila");
    }

}
