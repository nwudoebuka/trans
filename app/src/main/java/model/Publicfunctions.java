package model;

import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Publicfunctions {

    public boolean isEmailValid(String email)
    {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void don(EditText input){

        input.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }

}
