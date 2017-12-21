/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderbysorteditgui;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 *
 * @author hgontarz
 */
public class MethodComparator implements Comparator<Method> {
    public int compare(Method method1, Method method2) {
        return method1.getName().compareTo(method2.getName());
    }
}
