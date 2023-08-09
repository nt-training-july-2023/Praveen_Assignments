package Exceptions;
import java.io.*;
import java.util.*;

public class Q5_file_copy {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the address of the file to read:");
        String inputR = input.next();
        System.out.println("Enter the address of the file to write:");
        String inputW = input.next();
        
        try (FileReader fr = new FileReader(inputR);
             FileWriter fw = new FileWriter(inputW)) {

            int i;
            while ((i = fr.read()) != -1) {
                fw.write(i); // Writing data to the destination file
            }
            System.out.println("Copied successfully");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Source file not found.");
        } catch (IOException e) {
            System.out.println("Error: Unable to read/write file.");
        }
    }
}

