package com.we.apiautomation.apitest.utils;

import com.we.apiautomation.apitest.model.po.Assert;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiUtil {
    public static String getObjRealType(Object object) {
        if (object == null) {
            return "null";
        } else {
            if (object instanceof Boolean) {
                return "boolean";
            } else if (object instanceof Byte || object instanceof Short || object instanceof Integer
                    || object instanceof Long || object instanceof Float || object instanceof Double) {
                return "number";
            } else {
                return "string";
            }
        }
    }

    public static Object getRealObj(String object, Integer type) {
        if (type.equals(1)) {
            return object;
        } else if (type.equals(2)) {
            Double aDouble = new Double(0);
            try {
                aDouble = Double.valueOf(object);
            } catch (Exception e) {
            }
            return aDouble;
        } else if (type.equals(3)) {
            if (object.toLowerCase().equals("true")) {
                return true;
            } else {
                return false;
            }
        } else if (type.equals(4)) {
            return null;
        } else {
            return object;
        }
    }

    public static boolean getAssertionResult(Assert assertion, String realType, String realValue) {
        if (!assertion.getExtractType().equals(realType)) {
            return false;
        }
        switch (assertion.getExpectRelation()) {
            case "等于":
                if (assertion.getExpectValue().equals(realValue)) {
                    return true;
                }
                break;
            case "大于":
                if (realType.equals("number")) {
                    Double aDouble = Double.valueOf(assertion.getExpectValue());
                    Double aDouble1 = Double.valueOf(realValue);
                    if (aDouble < aDouble1) {
                        return true;
                    }
                }
                break;
            case "大于等于":
                if (realType.equals("number")) {
                    Double aDouble = Double.valueOf(assertion.getExpectValue());
                    Double aDouble1 = Double.valueOf(realValue);
                    if (aDouble <= aDouble1) {
                        return true;
                    }
                }
                break;
            case "小于":
                if (realType.equals("number")) {
                    Double aDouble = Double.valueOf(assertion.getExpectValue());
                    Double aDouble1 = Double.valueOf(realValue);
                    if (aDouble > aDouble1) {
                        return true;
                    }
                }
                break;
            case "小于等于":
                if (realType.equals("number")) {
                    Double aDouble = Double.valueOf(assertion.getExpectValue());
                    Double aDouble1 = Double.valueOf(realValue);
                    if (aDouble >= aDouble1) {
                        return true;
                    }
                }
                break;
            case "包含":
                if (realValue.contains(assertion.getExpectValue())) {
                    return true;
                }
                break;
            case "不包含":
                if (!realValue.contains(assertion.getExpectValue())) {
                    return true;
                }
                break;
            case "长度等于":
                if (assertion.getExpectValue().length() == realValue.length()) {
                    return true;
                }
                break;
            case "长度大于":
                if (realValue.length() > assertion.getExpectValue().length()) {
                    return true;
                }
                break;
            case "长度大于等于":
                if (realValue.length() >= assertion.getExpectValue().length()) {
                    return true;
                }
                break;
            case "长度小于":
                if (realValue.length() < assertion.getExpectValue().length()) {
                    return true;
                }
                break;
            case "长度小等于":
                if (realValue.length() <= assertion.getExpectValue().length()) {
                    return true;
                }
                break;
            case "开始于":
                if (realValue.startsWith(assertion.getExpectValue())) {
                    return true;
                }
                break;
            case "结束于":
                if (realValue.endsWith(assertion.getExpectValue())) {
                    return true;
                }
                break;
            default:
        }
        return false;
    }

}
