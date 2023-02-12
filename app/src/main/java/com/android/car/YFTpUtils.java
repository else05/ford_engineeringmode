package com.android.car;

import android.util.Log;

/* loaded from: classes2.dex */
public class YFTpUtils {
    private String TAG = YFTpUtils.class.getSimpleName();

    public static String deCodeStringUtils(String result) {
        if (result != null) {
            Log.i("huangshuo", "deCodeStringUtils:str==" + result);
            if (result.contains("&SeM")) {
                result = deCodeStringFunc(result, "&SeM");
            }
            if (result.contains("&CoM")) {
                result = deCodeStringFunc(result, "&CoM");
            }
            if (result.contains("&CoL")) {
                result = deCodeStringFunc(result, "&CoL");
            }
            if (result.contains("&SpC")) {
                result = deCodeStringFunc(result, "&SpC");
            }
            if (result.contains("&BaC")) {
                result = deCodeStringFunc(result, "&BaC");
            }
            if (result.contains("&DeB")) {
                result = deCodeStringFunc(result, "&DeB");
            }
        }
        return decodeSpecialUtils(result);
    }

    private static String deCodeStringFunc(String result, String speStr) {
        int currindex = 0;
        int speStrLen = speStr.length();
        while (result != null && speStr != null) {
            int currindex2 = result.indexOf(speStr, currindex);
            if (currindex2 == -1) {
                break;
            }
            int count = 0;
            for (int j = currindex2 - 1; j >= 0; j--) {
                char b = result.charAt(j);
                if (b != '&') {
                    break;
                }
                count++;
            }
            int j2 = count % 2;
            if (j2 == 0) {
                speStrLen = 1;
                String tempStr = result.substring(currindex2, speStr.length() + currindex2);
                if (speStr.equals("&CoL")) {
                    tempStr = ":";
                }
                if (speStr.equals("&SeM")) {
                    tempStr = ";";
                }
                if (speStr.equals("&CoM")) {
                    tempStr = ",";
                }
                if (speStr.equals("&SpC")) {
                    tempStr = " ";
                }
                if (speStr.equals("&BaC")) {
                    tempStr = "{";
                }
                if (speStr.equals("&DeB")) {
                    tempStr = "}";
                }
                result = speStr.length() + currindex2 < result.length() ? result.substring(0, currindex2) + tempStr + result.substring(speStr.length() + currindex2, result.length()) : result.substring(0, currindex2) + tempStr;
            }
            currindex = currindex2 + speStrLen;
        }
        return result;
    }

    public static String decodeSpecialUtils(String result) {
        String tempStr;
        int count = 0;
        int startindex = 0;
        if (result != null && result.contains("&")) {
            while (true) {
                int index = result.indexOf("&", startindex);
                if (startindex < 0 || startindex > result.length()) {
                    break;
                }
                if (index != startindex) {
                    if (count % 2 == 0) {
                        String tempStr2 = "";
                        for (int j = 0; j < count / 2; j++) {
                            tempStr2 = tempStr2 + "&";
                        }
                        tempStr = tempStr2;
                    } else {
                        String tempStr3 = "";
                        for (int j2 = 0; j2 < count / 2; j2++) {
                            tempStr3 = tempStr3 + "&";
                        }
                        tempStr = tempStr3 + "&";
                    }
                    result = startindex - count > 0 ? result.substring(0, startindex - count) + tempStr + result.substring(startindex, result.length()) : tempStr + result.substring(startindex, result.length());
                    int length = startindex - tempStr.length();
                    index -= tempStr.length();
                    count = 0;
                }
                count++;
                startindex = index + 1;
            }
        }
        return result;
    }

    public static String enCodeStringUtils(String str) {
        if (str != null) {
            Log.i("huangshuo", "enCodeStringUtils:str==" + str);
            if (str.contains("&")) {
                str = str.replaceAll("&", "&&");
            }
            if (str.contains(";")) {
                str = str.replaceAll(";", "&SeM");
            }
            if (str.contains(":")) {
                str = str.replaceAll(":", "&CoL");
            }
            if (str.contains(",")) {
                str = str.replaceAll(",", "&CoM");
            }
            if (str.contains(" ")) {
                str = str.replaceAll(" ", "&SpC");
            }
            if (str.contains("{")) {
                str = str.replaceAll("\\{", "&BaC");
            }
            if (str.contains("}")) {
                return str.replaceAll("\\}", "&DeB");
            }
            return str;
        }
        return null;
    }
}
