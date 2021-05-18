package com.example.lendbook;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidarEditText {


    public ValidarEditText(){}
    public boolean Email(String email){

        boolean emailOff = false;

        if (emailOff = !Patterns.EMAIL_ADDRESS.matcher(email).matches());
            return emailOff;


    }
    public boolean CamposText(String campo){

            boolean palavra = (TextUtils.isEmpty(campo) || campo.trim().isEmpty());

            return palavra;

    }


}
