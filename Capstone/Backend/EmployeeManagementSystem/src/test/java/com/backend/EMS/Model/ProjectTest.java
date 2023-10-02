package com.backend.EMS.Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {

    private Project project;

    @BeforeEach
    public void setUp() {
        project = new Project();
    }

    @Test
    public void testGetAndSetId() {
        project.setId(1L);
        assertEquals(1L, project.getId());
    }

    @Test
    public void testGetAndSetProjectName() {
        project.setProjectName("Sample Project");
        assertEquals("Sample Project", project.getProjectName());
    }

    @Test
    public void testGetAndSetManagerId() {
        project.setManagerId(101L);
        assertEquals(101L, project.getManagerId());
    }

    @Test
    public void testGetAndSetStartDate() {
        project.setStartDate("2023-09-30");
        assertEquals("2023-09-30", project.getStartDate());
    }

    @Test
    public void testGetAndSetRequiredSkills() {
        List<String> skills = new ArrayList<>(Arrays.asList("Java", "Spring"));
        project.setRequiredSkills(skills);

        List<String> retrievedSkills = project.getRequiredSkills();
        assertEquals(skills, retrievedSkills);

//        // Test setting requiredSkills to null
//        
//        project.setRequiredSkills(null);
//        assertNotNull(project.getRequiredSkills());
//        assertTrue(project.getRequiredSkills().isEmpty());
    }

    @Test
    public void testGetAndSetDescription() {
        project.setDescription("This is a sample project description.");
        assertEquals("This is a sample project description.", project.getDescription());
    }

    @Test
    public void testEqualsAndHashCode() {
        Project project1 = new Project();
        project1.setId(1L);
        project1.setProjectName("Project A");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setProjectName("Project B");

        // Test equality
        assertTrue(project1.equals(project1));
        assertFalse(project1.equals(null));
        assertFalse(project1.equals(project2));

        // Test hash code
        assertEquals(project1.hashCode(), project1.hashCode());
        assertNotEquals(project1.hashCode(), project2.hashCode());
    }
}

