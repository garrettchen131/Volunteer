package cn.sicnu.common.model.enums;

import cn.sicnu.common.exception.ParameterException;

public enum Status {

    INVALID(-2, "invalid"),
    PREPARE(0, "prepare"),
    EXECUTE(2, "execute"),
    COMPLETED(4, "completed"),
    ;

    private final int code;
    private final String name;

    Status(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(int code) {
        for (Status value : values()) {
            if (code == value.code) {
                return value.name;
            }
        }
        throw new ParameterException(code + " : THIS STATUS CODE NOT FOUND");
    }


    public static int getCodeByName(String name) {
        for (Status value : values()) {
            if (value.name.equalsIgnoreCase(name)) {
                return value.code;
            }
        }
        throw new ParameterException(name + " : THIS STATUS Name NOT FOUND");
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static boolean validStatus(int code) {
        if (PREPARE.code == code || EXECUTE.code == code) {
            return true;
        }
        return false;
    }
}
