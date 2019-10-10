package utils.fieldchecker;

import utils.fieldchecker.error.ErrorCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthFieldChecker {

    public ErrorCode loginEmptyFieldsCheck(String login, String password) {
        if (login == null || password == null || login.equals("") || password.equals("")) {
            return ErrorCodes.INCORRECT_LOGINFORM_FIELDS;
        }
        return ErrorCodes.CHECK_PASSED;
    }

    public ErrorCodes checkLogin(String login) {

        if (login.length() < 4 || login.length() > 20) {
            return ErrorCodes.INCORRECT_LOGIN_LENGTH;
        }

        String regExp = "[A-Za-z0-9_]+";
        Pattern correctLoginPattern = Pattern.compile(regExp);
        Matcher matcher = correctLoginPattern.matcher(login);

        if (matcher.matches() == false) {
            return ErrorCodes.INCORRECT_LOGIN_PATTERN;
        }

        return ErrorCodes.CHECK_PASSED;
    }

    public ErrorCodes checkEmail(String email) {
        Pattern correctEmailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        if (correctEmailPattern.matcher(email).matches() == false) {
            return ErrorCodes.INCORRECT_EMAIL_PATTERN;
        }

        return ErrorCodes.CHECK_PASSED;
    }

    public ErrorCodes checkPassword(String password, String passwordRepeat) {
        if (!password.equals(passwordRepeat)) {
            return ErrorCodes.INCORRECT_REPEAT_PASSWORD;
        }

        if (password.length() < 4 || password.length() > 20) {
            return ErrorCodes.INCORRECT_PASSWORD_LENGTH;
        }

        String regExp = "[A-Za-z0-9_.,/*!#]+";
        Pattern correctLoginPattern = Pattern.compile(regExp);
        Matcher matcher = correctLoginPattern.matcher(password);

        if (matcher.matches() == false) {
            return ErrorCodes.INCORRECT_PASSWORD_PATTERN;
        }

        return ErrorCodes.CHECK_PASSED;
    }


    public enum ErrorCodes implements ErrorCode {

        CHECK_PASSED {
            @Override
            public String getMessage() {
                return "successful operation";
            }
        },
        INCORRECT_LOGINFORM_FIELDS {
            @Override
            public String getMessage() {
                return "login and password cannot be empty";
            }
        },
        INCORRECT_PASSWORD_LENGTH {
            @Override
            public String getMessage() {
                return "password must be longer than 4 and shorter than 20 characters!";
            }
        },
        INCORRECT_PASSWORD_PATTERN{
            @Override
            public String getMessage() {
                return "password can contain only A-Z, a-z, 0-9 and symbols '_.,/*!#'";
            }
        },
        INCORRECT_REPEAT_PASSWORD{
            @Override
            public String getMessage() {
                return "repeat password doesn't match original password!";
            }
        },
        INCORRECT_LOGIN_LENGTH {
            @Override
            public String getMessage() {
                return "login must be longer than 4 and shorter than 20 characters!";
            }
        },
        INCORRECT_EMAIL_PATTERN {
            @Override
            public String getMessage() {
                return "incorrect email";
            }
        },
        INCORRECT_LOGIN_PATTERN{
            @Override
            public String getMessage() {
                return "login can contain only A-Z, a-z, 0-9 and '_'";
            }
        };

        public abstract String getMessage();

        public boolean isError() {
            return !(this == CHECK_PASSED);
        }

    }
}
