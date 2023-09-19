package com.backend.EMS.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectDtoTest {

    private ProjectDto projectDto;

    @BeforeEach
    public void setUp() {
        projectDto = new ProjectDto();
    }

    @Test
    public void testGetSetId() {
        long id = 1L;
        projectDto.setId(id);
        Assertions.assertEquals(id, projectDto.getId());
    }

    @Test
    public void testGetSetProjectName() {
        String projectName = "Project A";
        projectDto.setProjectName(projectName);
        Assertions.assertEquals(projectName, projectDto.getProjectName());
    }

    @Test
    public void testGetSetManagerId() {
        long managerId = 2L;
        projectDto.setManagerId(managerId);
        Assertions.assertEquals(managerId, projectDto.getManagerId());
    }

    @Test
    public void testGetSetStartDate() {
        String startDate = "2023-09-20";
        projectDto.setStartDate(startDate);
        Assertions.assertEquals(startDate, projectDto.getStartDate());
    }

    @Test
    public void testGetSetRequiredSkills() {
        List<String> requiredSkills = new ArrayList<>();
        requiredSkills.add("Java");
        requiredSkills.add("SQL");
        projectDto.setRequiredSkills(requiredSkills);
        Assertions.assertEquals(requiredSkills, projectDto.getRequiredSkills());
    }

    @Test
    public void testGetSetDescription() {
        String description = "This is a sample project.";
        projectDto.setDescription(description);
        Assertions.assertEquals(description, projectDto.getDescription());
    }

    @Test
    public void testGetSetHead() {
        String head = "John Doe";
        projectDto.setHead(head);
        Assertions.assertEquals(head, projectDto.getHead());
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

        projectDto.setId(id);
        projectDto.setProjectName(projectName);
        projectDto.setManagerId(managerId);
        projectDto.setStartDate(startDate);
        projectDto.setRequiredSkills(requiredSkills);
        projectDto.setDescription(description);
        projectDto.setHead(head);

        String expectedString = "ProjectDto [id=" + id + ", projectName=" + projectName + ", managerId=" + managerId
                + ", startDate=" + startDate + ", requiredSkills=" + requiredSkills + ", description=" + description
                + ", head=" + head + "]";

        Assertions.assertEquals(expectedString, projectDto.toString());
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

        projectDto.setId(id);
        projectDto.setProjectName(projectName);
        projectDto.setManagerId(managerId);
        projectDto.setStartDate(startDate);
        projectDto.setRequiredSkills(requiredSkills);
        projectDto.setDescription(description);
        projectDto.setHead(head);

        ProjectDto other = new ProjectDto();
        other.setId(id);
        other.setProjectName(projectName);
        other.setManagerId(managerId);
        other.setStartDate(startDate);
        other.setRequiredSkills(requiredSkills);
        other.setDescription(description);
        other.setHead(head);

        Assertions.assertEquals(projectDto.hashCode(), other.hashCode());
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

        projectDto.setId(id);
        projectDto.setProjectName(projectName);
        projectDto.setManagerId(managerId);
        projectDto.setStartDate(startDate);
        projectDto.setRequiredSkills(requiredSkills);
        projectDto.setDescription(description);
        projectDto.setHead(head);

        ProjectDto other = new ProjectDto();
        other.setId(id);
        other.setProjectName(projectName);
        other.setManagerId(managerId);
        other.setStartDate(startDate);
        other.setRequiredSkills(requiredSkills);
        other.setDescription(description);
        other.setHead(head);

        Assertions.assertTrue(projectDto.equals(other));
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

        projectDto.setId(id1);
        projectDto.setProjectName(projectName1);
        projectDto.setManagerId(managerId1);
        projectDto.setStartDate(startDate1);
        projectDto.setRequiredSkills(requiredSkills1);
        projectDto.setDescription(description1);
        projectDto.setHead(head1);

        long id2 = 2L;
        String projectName2 = "Project B";
        long managerId2 = 3L;
        String startDate2 = "2023-09-25";
        List<String> requiredSkills2 = new ArrayList<>();
        requiredSkills2.add("Python");
        requiredSkills2.add("SQL");
        String description2 = "This is another project.";
        String head2 = "Jane Smith";

        ProjectDto other = new ProjectDto();
        other.setId(id2);
        other.setProjectName(projectName2);
        other.setManagerId(managerId2);
        other.setStartDate(startDate2);
        other.setRequiredSkills(requiredSkills2);
        other.setDescription(description2);
        other.setHead(head2);

        Assertions.assertFalse(projectDto.equals(other));
    }
}
