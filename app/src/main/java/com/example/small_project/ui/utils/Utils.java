package com.example.small_project.ui.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Utils {

    public static String formatAmountWithSpace(long amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("#,###", symbols);
        return df.format(amount) + " сум";
    }

    public static TextWatcher formatAsSum(final EditText editText, final TextView button) {
        return new TextWatcher() {
            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    editText.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("[,\\sсум]", "");
                    if (!cleanString.isEmpty()) {
                        try {
                            long parsed = Long.parseLong(cleanString);
                            DecimalFormat formatter = new DecimalFormat("#,###");
                            current = formatter.format(parsed) + " сум";
                            editText.setText(current);
                            editText.setSelection(current.length() - 4);
                            button.setEnabled(true);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            button.setEnabled(false);
                        }
                    } else {
                        current = "";
                        button.setEnabled(false);
                    }
                    editText.addTextChangedListener(this);
                }
            }
        };
    }

    public static long parseSumString(String formattedString) {
        if (formattedString == null || formattedString.isEmpty()) return 0;
        String clean = formattedString.replaceAll("[,\\sсум]", "");
        try {
            return Long.parseLong(clean);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
