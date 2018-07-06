package com.robot.lighting.utils;

import android.app.Application;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;

import com.blankj.utilcode.util.StringUtils;

/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/7/6
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 *
 * @author majingze
 */
public class ResUtils {

    private static Resources mResource;

    private ResUtils() {

    }

    public static void init(Application application) {
        mResource = application.getResources();
    }

    public static int getColor(@ColorRes int colorRes) {
        return mResource.getColor(colorRes);
    }

    public static float getDimen(@DimenRes int dimenRes) {
        return mResource.getDimension(dimenRes);
    }

    public static String getString(@StringRes int stringRes) {
        return mResource.getString(stringRes);
    }
}
