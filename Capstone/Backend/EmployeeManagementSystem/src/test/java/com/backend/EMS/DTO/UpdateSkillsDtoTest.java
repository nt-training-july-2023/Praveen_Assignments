package com.backend.EMS.DTO;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdateSkillsDtoTest {
    private UpdateSkillsDto updateSkillsDto;

    @BeforeEach
    public void setUp() {
        updateSkillsDto = new UpdateSkillsDto();
    }

    @Test
    public void testGettersAndSetters() {
        // Test setters and getters
        List<String> skillsList = new ArrayList<>();
        skillsList.add("Java");
        skillsList.add("Python");
        skillsList.add("SQL");

        updateSkillsDto.setSkills(skillsList);

        List<String> retrievedSkills = updateSkillsDto.getSkills();

        assertNotNull(retrievedSkills);
        assertEquals(skillsList, retrievedSkills);

        // Test null values for setters and getters
        List<String> nullSkillsList = new ArrayList<>();
        updateSkillsDto.setSkills(nullSkillsList);

        List<String> retrievedNullSkills = updateSkillsDto.getSkills();

        assertNull(retrievedNullSkills);
    }

    @Test
    public void testHashCode() {
        // Test hashCode with non-null values
        List<String> skillsList1 = new ArrayList<>();
        skillsList1.add("Java");
        skillsList1.add("Python");

        List<String> skillsList2 = new ArrayList<>();
        skillsList2.add("Java");
        skillsList2.add("Python");

        updateSkillsDto.setSkills(skillsList1);

        UpdateSkillsDto anotherUpdateSkillsDto = new UpdateSkillsDto();
        anotherUpdateSkillsDto.setSkills(skillsList2);

        assertEquals(updateSkillsDto.hashCode(), anotherUpdateSkillsDto.hashCode());

        // Test hashCode with null values
        List<String> nullSkillsList = new ArrayList<>();
        updateSkillsDto.setSkills(nullSkillsList);

        UpdateSkillsDto nullSkillsDto = new UpdateSkillsDto();
        nullSkillsDto.setSkills(nullSkillsList);

        assertEquals(updateSkillsDto.hashCode(), nullSkillsDto.hashCode());
    }

    @Test
    public void testEquals() {
        // Test equals with non-null values
        List<String> skillsList1 = new ArrayList<>();
        skillsList1.add("Java");
        skillsList1.add("Python");

        List<String> skillsList2 = new ArrayList<>();
        skillsList2.add("Java");
        skillsList2.add("Python");

        updateSkillsDto.setSkills(skillsList1);

        UpdateSkillsDto anotherUpdateSkillsDto = new UpdateSkillsDto();
        anotherUpdateSkillsDto.setSkills(skillsList2);

        assertTrue(updateSkillsDto.equals(anotherUpdateSkillsDto));

        // Test equals with null values
        List<String> nullSkillsList = new ArrayList<>();
        updateSkillsDto.setSkills(nullSkillsList);

        UpdateSkillsDto nullSkillsDto = new UpdateSkillsDto();
        
        nullSkillsDto.setSkills(nullSkillsList);

        assertTrue(updateSkillsDto.equals(nullSkillsDto));
    }

    @Test
    public void testToString() {
        // Test toString with non-null values
        List<String> skillsList = new ArrayList<>();
        skillsList.add("Java");
        skillsList.add("Python");

        updateSkillsDto.setSkills(skillsList);

        String expectedToString = "UpdateSkillsDto [skills=[Java, Python]]";
        assertEquals(expectedToString, updateSkillsDto.toString());

        // Test toString with null values
        List<String> nullSkillsList = new ArrayList<>();
        updateSkillsDto.setSkills(nullSkillsList);

        String expectedToStringWithNulls = "UpdateSkillsDto [skills=null]";
        assertEquals(expectedToStringWithNulls, updateSkillsDto.toString());
    }
}
