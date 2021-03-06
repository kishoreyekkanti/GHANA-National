package org.motechproject.openmrs.omod.validator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class MotechIdVerhoeffValidatorTest {
    MotechIdVerhoeffValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new MotechIdVerhoeffValidator();
    }

    @Test
    public void shouldGetValidIsValidIdentifier() {
        String undecoratedIdentifier = "248693";
        String identifier = validator.getValidIdentifier(undecoratedIdentifier);
        assertNotNull("Validator should not be null", identifier);
        assertEquals(7, identifier.length());
        assertTrue("Expected valid identifier with Verhoeff check digit",
                validator.isValid(identifier));
    }

    @Test
    public void shouldGetValidKnownVerhoeffIdentifier() {
        String undecoratedIdentifier = "123456";
        assertEquals("1234568", validator
                .getValidIdentifier(undecoratedIdentifier));
    }

    @Test
    public void shouldIsValidKnownVerhoeffIdentifier() {
        String identifier = "1234568";
        assertTrue("Expected valid identifier with Verhoeff check digit",
                validator.isValid(identifier));
    }
}
