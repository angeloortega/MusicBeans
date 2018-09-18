package beans.dreamteam.musicbeans.utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;
import beans.dreamteam.musicbeans.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class CustomValidator {

        public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

        public static final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{6,20})");//(?=.*[A-Z]) - Mayusculas

        public CustomValidator() {
            // Empty constructor
        }

        public static boolean basicValidation(Context nContext, EditText aEditText, int minZise) {
            boolean error = false;
            try {
                if (aEditText.getText().toString().length() == 0) {
                    error = true;
                    showMessage(nContext, nContext.getString(R.string.validator_incomplete_data_message));
                }
                if (aEditText.getText().toString().length() > 0 && aEditText.getText().toString().length() < minZise) {
                    error = true;
                    aEditText.setError(nContext.getString(R.string.validator_size_error_1) + " " + minZise + " "
                            + nContext.getString(R.string.validator_size_error_2));
                }
            } catch (Exception ex){
                error = true;
            }
            return error;
        }

        public static boolean noInputValidation(Context nContext, EditText aEditText) {
            boolean error = false;
            try {
                if (aEditText.getText().toString().length() == 0) {
                    error = true;
                    showMessage(nContext, nContext.getString(R.string.validator_incomplete_data_message));
                }
            } catch (Exception ex){
                error = true;
            }
            return error;
        }

        public static boolean validateEmail(Context nContext, EditText email, int minZise) {
            boolean error = false;
            try {
                boolean basicValidationError = basicValidation(nContext, email, minZise);
                if (!basicValidationError) {
                    if (!EMAIL_ADDRESS_PATTERN.matcher(email.getText().toString()).matches()) {
                        email.setError("example@mail.com");
                        error = true;
                    }
                } else {
                    error = true;
                }
            } catch (Exception ex){
                error = true;
            }
            return error;
        }

        public static boolean validatePasswordStructure(Context nContext, EditText password, int minZise) {
            boolean error = false;
            try {
                boolean basicValidationError = basicValidation(nContext, password, minZise);
                if (!basicValidationError) {
                    if (!PASSWORD_PATTERN.matcher(password.getText().toString()).matches()) {
                        password.setError(nContext.getString(R.string.validator_password_structure));
                        error = true;
                    }
                } else {
                    error = true;
                }
            } catch (Exception ex){
                error = true;
            }
            return error;
        }

        public static Toast showMessage(Context nContext, String Message) {
            Toast.makeText(nContext, Message, Toast.LENGTH_SHORT).show();
            return null;
        }

        public static boolean validateExpDate(EditText edDate) {
            boolean Error = false;
            int inMonth = 0, inYear = 0;
            try {
                inMonth = Integer.valueOf(edDate.getText().toString()
                        .substring(0, 2));
                inYear = Integer.valueOf(edDate.getText().toString()
                        .substring(2, 4));
            } catch (Exception e) {
                Error = true;
                edDate.setError("The input date is incorrect must be MMYY");
            }
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMyy");
            String currentDate;
            int CurrentMonth, CurrentYear;
            currentDate = dateFormat.format(calendar.getTime());
            CurrentMonth = Integer.valueOf(currentDate.substring(0, 2));
            CurrentYear = Integer.valueOf(currentDate.substring(2, 4));
            if (inYear < CurrentYear || inMonth > 12 || (inYear == CurrentYear
                    && inMonth < CurrentMonth)) {
                Error = true;
                edDate.setError("The input date is incorrect must be MMYY");
            }
            return Error;
        }

        public static boolean validateExpDate(Context nContext, String month, String year) {
            boolean Error = false;
            try {
                int inMonth = 0, inYear = 0;
                try {
                    inMonth = Integer.valueOf(month.toString()
                            .substring(0, 2));
                    inYear = Integer.valueOf(year.toString()
                            .substring(2, 4));
                } catch (Exception e) {
                    Error = true;
                    showMessage(nContext, nContext.getString(R.string.validator_incorrect_date));
                }
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMyy");
                String currentDate;
                int CurrentMonth, CurrentYear;
                currentDate = dateFormat.format(calendar.getTime());
                CurrentMonth = Integer.valueOf(currentDate.substring(0, 2));
                CurrentYear = Integer.valueOf(currentDate.substring(2, 4));
                if (inYear < CurrentYear || inMonth > 12 || (inYear == CurrentYear
                        && inMonth < CurrentMonth)) {
                    Error = true;
                    showMessage(nContext, nContext.getString(R.string.validator_incorrect_date));
                }
            } catch (Exception ex){
                Error = true;
            }
            return Error;
        }
}
