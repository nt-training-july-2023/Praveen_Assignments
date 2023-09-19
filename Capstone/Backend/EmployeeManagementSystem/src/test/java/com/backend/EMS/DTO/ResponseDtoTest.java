package com.backend.EMS.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.EMS.Model.Role;

public class ResponseDtoTest {

    private ResponseDto responseDto;

    @BeforeEach
    public void setUp() {
        responseDto = new ResponseDto();
    }

    @Test
    public void testGetSetMessage() {
        String message = "Success";
        responseDto.setMessage(message);
        Assertions.assertEquals(message, responseDto.getMessage());
    }



    @Test
    public void testToString() {
        String message = "Success";
        

        responseDto.setMessage(message);
     

        String expectedString = "ResponseDto [message=" + message + "]";

        Assertions.assertEquals(expectedString, responseDto.toString());
    }

    @Test
    public void testHashCode() {
        String message = "Success";
        Role role = Role.Admin;

        responseDto.setMessage(message);
     

        ResponseDto other = new ResponseDto();
        other.setMessage(message);
      

        Assertions.assertEquals(responseDto.hashCode(), other.hashCode());
    }

    @Test
    public void testEquals() {
        String message = "Success";
        Role role = Role.Admin;

        responseDto.setMessage(message);
     

        ResponseDto other = new ResponseDto();
        other.setMessage(message);
    
        Assertions.assertTrue(responseDto.equals(other));
    }

    @Test
    public void testNotEquals() {
        String message1 = "Success";
        Role role1 = Role.Admin;

        responseDto.setMessage(message1);
       

        String message2 = "Failure";
        Role role2 = Role.Admin;

        ResponseDto other = new ResponseDto();
        other.setMessage(message2);
      
        Assertions.assertFalse(responseDto.equals(other));
    }
}
