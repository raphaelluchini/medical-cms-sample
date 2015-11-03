package com.medicalcms.handlers;


import com.medicalcms.Validable;

public class EmptyPayload implements Validable {
    @Override
    public boolean isValid() {
        return true;
    }
}
