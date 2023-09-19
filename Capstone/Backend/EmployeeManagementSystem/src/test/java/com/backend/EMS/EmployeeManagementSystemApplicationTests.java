package com.backend.EMS;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeManagementSystemApplicationTests {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testModelMapperBean() {
        assertNotNull(modelMapper);
        // You can add more assertions or tests related to the ModelMapper bean here
    }
}
