package com.milinda.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.EditText;

/**
 * 作者:Milinda 邮件:Milinda.Hu@gmail.com
 * 创建时间:2018/8/1
 * 描述:工具类
 */

public final class Utils {

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     *获取手机的屏幕像素密度==>即每英寸多少像素
     * @param context
     * @return
     */
    public static float getDenity(Context context){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        return dm.density;
    }

    /**
     * px转sp
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, final float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isTrimEmpty(final String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     *  处理从属性中拿到的字体大小单位为px转换成sp
     * getDimension()             返回float型px值       精确
     * getDimensionPixelOffset()  返回int型px值         直接把小数删除
     * getDimensionPixelSize()    返回int型px值         进行四舍五入
     * @param textSize 需要处理的属性
     * @param textSizeRes 相应的默认字体大小资源
     * @return
     */
    public static float handleTextSize(Context context,float textSize,int textSizeRes){
        textSize=textSize==0?context.getResources().getDimension(textSizeRes):textSize;
        textSize=Utils.px2sp(context,textSize);
        return textSize;
    }


    /**
     * 设置编辑框不可用
     * @param view
     */
    public static void forbidEdittext(EditText view){
        if(view==null){
            return;
        }
        view.setCursorVisible(false);
        view.setFocusable(false);
        view.setFocusableInTouchMode(false);
    }

    /**
     * 设置编辑框可用
     * @param view
     */
    public static void enabledEdittext(EditText view){
        if(view==null){
            return;
        }
        view.setCursorVisible(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
    }
}
