/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.Command;

import BackEnd.Read.Student.ReadGroups;
import BackEnd.Read.Student.StudentReader;

/**
 *
 * @author darcy
 */
public class Main {
    public static void main(String[] args){
        StudentReader studentReader = new ReadGroups(10);
        System.out.print(studentReader.read());
    }
}
