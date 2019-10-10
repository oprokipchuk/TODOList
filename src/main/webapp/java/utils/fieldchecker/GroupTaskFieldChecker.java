package utils.fieldchecker;

import utils.fieldchecker.error.ErrorCode;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupTaskFieldChecker {

    public ArrayList<String> taskStatusValues = new ArrayList<String>() {
        {
            add("not started");
            add("in process");
            add("done");
        }
    };

    public ErrorCode chechNewGroupName(String groupName) {

        if (groupName.length() < 3 || groupName.length() > 30) {
            return ErrorCodes.INCORRECT_GROUP_LENGTH;
        }

        String regExp = "[A-Za-z0-9_]+";
        Pattern correctGroupNamePattern = Pattern.compile(regExp);
        Matcher matcher = correctGroupNamePattern.matcher(groupName);

        if (matcher.matches() == false) {
            return ErrorCodes.INCORRECT_GROUP_PATTERN;
        }

        return ErrorCodes.CHECK_PASSED;
    }

    public ErrorCode checkTaskName(String taskName) {
        if (taskName.length() < 3 || taskName.length() > 45) {
            return ErrorCodes.INCORRECT_TASK_NAME_LENGTH;
        }

        String regExp = "[A-Za-z0-9_]+";
        Pattern correctTaskNamePattern = Pattern.compile(regExp);
        Matcher matcher = correctTaskNamePattern.matcher(taskName);

        if (matcher.matches() == false) {
            return ErrorCodes.INCORRECT_TASK_NAME_PATTERN;
        }

        return ErrorCodes.CHECK_PASSED;
    }

    public ErrorCode checkTaskStatus(String taskStatus) {
        if (!taskStatusValues.contains(taskStatus)) return ErrorCodes.INCORRECT_TASK_STATUS;
        return ErrorCodes.CHECK_PASSED;
    }

    public ErrorCode checkTaskDescription(String taskDescription) {
        if (taskDescription.length() > 1000) return ErrorCodes.INCORRECT_TASK_DESCRIPTION_LENGTH;
        return ErrorCodes.CHECK_PASSED;
    }

    public enum ErrorCodes implements ErrorCode {

        CHECK_PASSED {
            @Override
            public String getMessage() {
                return "successful operation";
            }
        },
        INCORRECT_GROUP_LENGTH {
            @Override
            public String getMessage() {
                return "group name length must be between 3 and 30 symbols";
            }
        },
        INCORRECT_GROUP_PATTERN {
            @Override
            public String getMessage() {
                return "group name can contain only A-Z, a-z, 0-9 and '_'";
            }
        },
        INCORRECT_TASK_NAME_LENGTH {
            @Override
            public String getMessage() {
                return "task name length must be between 3 and 45 symbols";
            }
        },
        INCORRECT_TASK_NAME_PATTERN {
            @Override
            public String getMessage() {
                return "task name can contain only A-Z, a-z, 0-9 and '_'";
            }
        },
        INCORRECT_TASK_STATUS {
            @Override
            public String getMessage() {
                return "incorrect task status";
            }
        },
        INCORRECT_TASK_DESCRIPTION_LENGTH {
            @Override
            public String getMessage() {
                return "task description cannot be greater than 1000";
            }
        },
        ;

        public abstract String getMessage();

        public boolean isError() {
            return !(this == CHECK_PASSED);
        }

    }

}
