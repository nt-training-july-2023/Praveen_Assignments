package com.backend.EMS.DTO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IsRequestedOutDtoTest {


    @Test
    public void testIsRequested() {
        IsRequestedOutDto dto1 = new IsRequestedOutDto();
        dto1.setRequested(true);

        IsRequestedOutDto dto2 = new IsRequestedOutDto();
        dto2.setRequested(true);

        IsRequestedOutDto dto3 = new IsRequestedOutDto();
        dto3.setRequested(false);

        // Test equality
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    public void testToString() {
        IsRequestedOutDto dto = new IsRequestedOutDto();
        dto.setRequested(true);

        String expectedToString = "IsRequestedOutDto [isRequested=true]";
        assertEquals(expectedToString, dto.toString());
    }
}
