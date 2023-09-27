package com.backend.EMS.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectInDtoTest {

    private ProjectInDto projectInDto;

    @BeforeEach
    public void setUp() {
        projectInDto = new ProjectInDto();
    }

    @Test
    public void testGetSetId() {
        long id = 1L;
        projectInDto.setId(id);
        Assertions.assertEquals(id, projectInDto.getId());
    }

    @Test
    public void testGetSetProjectName() {
        String projectName = "Project A";
        projectInDto.setProjectName(projectName);
        Assertions.assertEquals(projectName, projectInDto.getProjectName());
    }

    @Test
    public void testGetSetManagerId() {
        long managerId = 2L;
        projectInDto.setManagerId(managerId);
        Assertions.assertEquals(managerId, projectInDto.getManagerId());
    }

    @Test
    public void testGetSetStartDate() {
        String startDate = "2023-09-20";
        projectInDto.setStartDate(startDate);
        Assertions.assertEquals(startDate, projectInDto.getStartDate());
    }

    @Test
    public void testGetSetRequiredSkills() {
        List<String> requiredSkills = new ArrayList<>();
        requiredSkills.add("Java");
        requiredSkills.add("SQL");
        projectInDto.setRequiredSkills(requiredSkills);
        Assertions.assertEquals(requiredSkills, projectInDto.getRequiredSkills());
    }

    @Test
    public void testGetSetDescription() {
        String description = "This is a sample project.";
        projectInDto.setDescription(description);
        Assertions.assertEquals(description, projectInDto.getDescription());
    }

    @Test
    public void testGetSetHead() {
        String head = "John Doe";
        projectInDto.setHead(head);
        Assertions.assertEquals(head, projectInDto.getHead());
    }

    @Test
    public void testToString() {
        long id = 1L;
        String projectName = "Project A";
        long managerId = 2L;
        String startDate = "2023-09-20";
        List<String> requiredSkills = new ArrayList<>();
        requiredSkills.add("Java");
        requiredSkills.add("SQL");
        String description = "This is a sample project.";
        String head = "John Doe";

        projectInDto.setId(id);
        projectInDto.setProjectName(projectName);
        projectInDto.setManagerId(managerId);
        projectInDto.setStartDate(startDate);
        projectInDto.setRequiredSkills(requiredSkills);
        projectInDto.setDescription(description);
        projectInDto.setHead(head);

        String expectedString = "ProjectInDto [id=" + id + ", projectName=" + projectName + ", managerId=" + managerId
                + ", startDate=" + startDate + ", requiredSkills=" + requiredSkills + ", description=" + description
                + ", head=" + head + "]";

        Assertions.assertEquals(expectedString, projectInDto.toString());
    }

    @Test
    public void testHashCode() {
        long id = 1L;
        String projectName = "Project A";
        long managerId = 2L;
        String startDate = "2023-09-20";
        List<String> requiredSkills = new ArrayList<>();
        requiredSkills.add("Java");
        requiredSkills.add("SQL");
        String description = "This is a sample project.";
        String head = "John Doe";

        projectInDto.setId(id);
        projectInDto.setProjectName(projectName);
        projectInDto.setManagerId(managerId);
        projectInDto.setStartDate(startDate);
        projectInDto.setRequiredSkills(requiredSkills);
        projectInDto.setDescription(description);
        projectInDto.setHead(head);

        ProjectInDto other = new ProjectInDto();
        other.setId(id);
        other.setProjectName(projectName);
        other.setManagerId(managerId);
        other.setStartDate(startDate);
        other.setRequiredSkills(requiredSkills);
        other.setDescription(description);
        other.setHead(head);

        Assertions.assertEquals(projectInDto.hashCode(), other.hashCode());
    }

    @Test
    public void testEquals() {
        long id = 1L;
        String projectName = "Project A";
        long managerId = 2L;
        String startDate = "2023-09-20";
        List<String> requiredSkills = new ArrayList<>();
        requiredSkills.add("Java");
        requiredSkills.add("SQL");
        String description = "This is a sample project.";
        String head = "John Doe";

        projectInDto.setId(id);
        projectInDto.setProjectName(projectName);
        projectInDto.setManagerId(managerId);
        projectInDto.setStartDate(startDate);
        projectInDto.setRequiredSkills(requiredSkills);
        projectInDto.setDescription(description);
        projectInDto.setHead(head);

        ProjectInDto other = new ProjectInDto();
        other.setId(id);
        other.setProjectName(projectName);
        other.setManagerId(managerId);
        other.setStartDate(startDate);
        other.setRequiredSkills(requiredSkills);
        other.setDescription(description);
        other.setHead(head);

        Assertions.assertTrue(projectInDto.equals(other));
    }

    @Test
    public void testNotEquals() {
        long id1 = 1L;
        String projectName1 = "Project A";
        long managerId1 = 2L;
        String startDate1 = "2023-09-20";
        List<String> requiredSkills1 = new ArrayList<>();
        requiredSkills1.add("Java");
        requiredSkills1.add("SQL");
        String description1 = "This is a sample project.";
        String head1 = "John Doe";

        projectInDto.setId(id1);
        projectInDto.setProjectName(projectName1);
        projectInDto.setManagerId(managerId1);
        projectInDto.setStartDate(startDate1);
        projectInDto.setRequiredSkills(requiredSkills1);
        projectInDto.setDescription(description1);
        projectInDto.setHead(head1);

        long id2 = 2L;
        String projectName2 = "Project B";
        long managerId2 = 3L;
        String startDate2 = "2023-09-25";
        List<String> requiredSkills2 = new ArrayList<>();
        requiredSkills2.add("Python");
        requiredSkills2.add("SQL");
        String description2 = "This is another project.";
        String head2 = "Jane Smith";

        ProjectInDto other = new ProjectInDto();
        other.setId(id2);
        other.setProjectName(projectName2);
        other.setManagerId(managerId2);
        other.setStartDate(startDate2);
        other.setRequiredSkills(requiredSkills2);
        other.setDescription(description2);
        other.setHead(head2);

        Assertions.assertFalse(projectInDto.equals(other));
    }
}
