/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderby3;

/**
 *
 * @author Clarence
 */
public class BlankRecord {

    static String moveSpaces(int numberOfSpaces){
        StringBuilder sb1 = new StringBuilder(numberOfSpaces);
        for(int i = 0; i < numberOfSpaces; i++){
            sb1.append(" ");
        }
        return sb1.toString();
    }    
    
}
