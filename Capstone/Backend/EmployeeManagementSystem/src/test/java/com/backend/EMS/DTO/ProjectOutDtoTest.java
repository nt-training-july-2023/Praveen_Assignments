package com.backend.EMS.DTO;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectOutDtoTest {
    private ProjectOutDto dto;

    @BeforeEach
    public void setUp() {
        dto = new ProjectOutDto();
    }

    @Test
    public void testSetterGetter() {
        // Test setId and getId
        dto.setId(1L);
        assertEquals(1L, dto.getId());

        // Test setProjectName and getProjectName
        dto.setProjectName("Project A");
        assertEquals("Project A", dto.getProjectName());

        // Test setManagerId and getManagerId
        dto.setManagerId(2L);
        assertEquals(2L, dto.getManagerId());

        // Test setStartDate and getStartDate
        dto.setStartDate("2023-09-29");
        assertEquals("2023-09-29", dto.getStartDate());

        // Test setRequiredSkills and getRequiredSkills
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("SQL");
        dto.setRequiredSkills(skills);
        assertEquals(skills, dto.getRequiredSkills());

        // Test setDescription and getDescription
        dto.setDescription("Project description");
        assertEquals("Project description", dto.getDescription());

        // Test setHead and getHead
        dto.setHead("Project Head");
        assertEquals("Project Head", dto.getHead());

        // Test setTeam and getTeam
        List<String> teamMembers = new ArrayList<>();
        teamMembers.add("John");
        teamMembers.add("Jane");
        dto.setTeam(teamMembers);
        assertEquals(teamMembers, dto.getTeam());
    }

    @Test
    public void testNullValues() {
        // Test getting null values for all fields
//        assertNull(dto.getId());
//        assertNull(dto.getProjectName());
//        assertNull(dto.getManagerId());
//        assertNull(dto.getStartDate());
//        assertNull(dto.getRequiredSkills());
//        assertNull(dto.getDescription());
//        assertNull(dto.getHead());
//        assertNull(dto.getTeam());

        // Test setting null values for all fields
        dto.setId(1L);
        dto.setProjectName(null);
        dto.setManagerId(2L);
        dto.setStartDate(null);
//        dto.setRequiredSkills(null);
        dto.setDescription(null);
        dto.setHead(null);
//        dto.setTeam(null);

        assertNull(dto.getProjectName());
        assertNull(dto.getStartDate());
//        assertNull(dto.getRequiredSkills());
        assertNull(dto.getDescription());
        assertNull(dto.getHead());
//        assertNull(dto.getTeam());
    }

    @Test
    public void testHashCode() {
        ProjectOutDto dto1 = new ProjectOutDto();
        dto1.setId(1L);
        dto1.setProjectName("Project A");
        dto1.setManagerId(2L);
        dto1.setStartDate("2023-09-29");
        List<String> skills1 = new ArrayList<>();
        skills1.add("Java");
        skills1.add("SQL");
        dto1.setRequiredSkills(skills1);
        dto1.setDescription("Project description");
        dto1.setHead("Project Head");
        List<String> teamMembers1 = new ArrayList<>();
        teamMembers1.add("John");
        teamMembers1.add("Jane");
        dto1.setTeam(teamMembers1);

        ProjectOutDto dto2 = new ProjectOutDto();
        dto2.setId(1L);
        dto2.setProjectName("Project A");
        dto2.setManagerId(2L);
        dto2.setStartDate("2023-09-29");
        List<String> skills2 = new ArrayList<>();
        skills2.add("Java");
        skills2.add("SQL");
        dto2.setRequiredSkills(skills2);
        dto2.setDescription("Project description");
        dto2.setHead("Project Head");
        List<String> teamMembers2 = new ArrayList<>();
        teamMembers2.add("John");
        teamMembers2.add("Jane");
        dto2.setTeam(teamMembers2);

        // HashCode should be the same for equal objects
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        ProjectOutDto dto1 = new ProjectOutDto();
        dto1.setId(1L);
        dto1.setProjectName("Project A");
        dto1.setManagerId(2L);
        dto1.setStartDate("2023-09-29");
        List<String> skills1 = new ArrayList<>();
        skills1.add("Java");
        skills1.add("SQL");
        dto1.setRequiredSkills(skills1);
        dto1.setDescription("Project description");
        dto1.setHead("Project Head");
        List<String> teamMembers1 = new ArrayList<>();
        teamMembers1.add("John");
        teamMembers1.add("Jane");
        dto1.setTeam(teamMembers1);

        ProjectOutDto dto2 = new ProjectOutDto();
        dto2.setId(1L);
        dto2.setProjectName("Project A");
        dto2.setManagerId(2L);
        dto2.setStartDate("2023-09-29");
        List<String> skills2 = new ArrayList<>();
        skills2.add("Java");
        skills2.add("SQL");
        dto2.setRequiredSkills(skills2);
        dto2.setDescription("Project description");
        dto2.setHead("Project Head");
        List<String> teamMembers2 = new ArrayList<>();
        teamMembers2.add("John");
        teamMembers2.add("Jane");
        dto2.setTeam(teamMembers2);

        ProjectOutDto dto3 = new ProjectOutDto();
        dto3.setId(3L);
        dto3.setProjectName("Project B");
        dto3.setManagerId(4L);
        dto3.setStartDate("2023-10-01");
        List<String> skills3 = new ArrayList<>();
        skills3.add("Python");
        skills3.add("SQL");
        dto3.setRequiredSkills(skills3);
        dto3.setDescription("Another project description");
        dto3.setHead("Project Head");
        List<String> teamMembers3 = new ArrayList<>();
        teamMembers3.add("Alice");
        teamMembers3.add("Bob");
        dto3.setTeam(teamMembers3);

        // Test equality with equal objects
        assertEquals(dto1, dto2);

        // Test inequality with different objects
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        ProjectOutDto dto = new ProjectOutDto();
        dto.setId(1L);
        dto.setProjectName("Project A");
        dto.setManagerId(2L);
        dto.setStartDate("2023-09-29");
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("SQL");
        dto.setRequiredSkills(skills);
        dto.setDescription("Project description");
        dto.setHead("Project Head");
        List<String> teamMembers = new ArrayList<>();
        teamMembers.add("John");
        teamMembers.add("Jane");
        dto.setTeam(teamMembers);

        String expectedToString = "ProjectOutDto [id=1, projectName=Project A, managerId=2, startDate=2023-09-29, requiredSkills=[Java, SQL], description=Project description, head=Project Head, team=[John, Jane]]";
        assertEquals(expectedToString, dto.toString());
    }
}
