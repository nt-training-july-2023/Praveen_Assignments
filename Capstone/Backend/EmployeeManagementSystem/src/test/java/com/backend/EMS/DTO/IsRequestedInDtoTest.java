package com.backend.EMS.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IsRequestedInDtoTest {

    private IsRequestedInDto isRequestedInDto;

    @BeforeEach
    public void setUp() {
        isRequestedInDto = new IsRequestedInDto();
    }

    @Test
    public void testGetterAndSetterMethods() {
        isRequestedInDto.setEmployeeId(1L);
        isRequestedInDto.setId(2L);

        assertEquals(1L, isRequestedInDto.getEmployeeId());
        assertEquals(2L, isRequestedInDto.getId());

        isRequestedInDto.setEmployeeId(null);
        isRequestedInDto.setId(null);

        assertNull(isRequestedInDto.getEmployeeId());
        assertNull(isRequestedInDto.getId());
    }
}
