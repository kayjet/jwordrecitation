package com.kayjet.word.recitation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ForgetCurveUtils
 *
 * @author kai.liu
 * @date 2018/08/13
 */
public class ForgetCurveUtils {

    public static double getForgetCurve(double hours) {
        double forgetCurve = 1 - 0.56 * Math.pow(hours, 0.06);
        return forgetCurve;
    }
}
