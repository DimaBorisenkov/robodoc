package com.example.dima.robodoc.utils;

import com.example.dima.robodoc.data.models.Patient;

import java.util.Comparator;

public class ComporatorByState  implements Comparator<Patient> {
        @Override
        public int compare(Patient o1, Patient o2) {
            if(o1.getState() == true){
                return 1;
            }else{
                if(o2.getState() == true){
                    return -1;
                }else{
                    return 1;
                }
            }
        }
}
