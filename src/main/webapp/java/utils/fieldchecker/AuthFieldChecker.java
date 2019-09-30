package utils.fieldchecker;

public class AuthFieldChecker {

    public ErrorCodes checkLogin(String login) {

        if (login.length() < 4 || login.length() > 20) {
            return ErrorCodes.INCORRECT_LOGIN_LENGTH;
        }

        return ErrorCodes.CHECK_PASSED;
    }


    public enum ErrorCodes {

        CHECK_PASSED {
            @Override
            public String getMessage() {
                return "successful operation";
            }
        },
        INCORRECT_LOGIN_LENGTH {
            @Override
            public String getMessage() {
                return "login must be longer than 4 and shorter than 20 characters!";
            }
        };

        public abstract String getMessage();

    }
}
