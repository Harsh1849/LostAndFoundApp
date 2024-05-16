package com.example.foundandlost;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Extensions {
    static String ItemDATA = "itemData";

    /**
     * This Extension has been using for showing Toast messages
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This Extension has been using for hide keyboard
     */
    public static void hideKeyboard(Activity activity, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * This method is used to check if a string is empty or null.
     */
    public static boolean checkEmptyString(Object obj) {
        if (obj == null)
            return true;
        else
            return TextUtils.isEmpty(obj.toString().trim());
    }

    /**
     * This method is used for show validation errors
     */
    public static void showError(TextInputLayout inputLayout, String errorMessageRes) {
        inputLayout.setErrorEnabled(true);
        inputLayout.setError(errorMessageRes);
    }

    /**
     * This method is used for remove validation errors
     */
    public static void clearError(TextInputLayout inputLayout) {
        inputLayout.setErrorEnabled(false);
        inputLayout.setError(null);
    }
}