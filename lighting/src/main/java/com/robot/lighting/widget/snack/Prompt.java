package com.robot.lighting.widget.snack;


import com.robot.lighting.R;

/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2017/10/31
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 *
 * @author majingze
 */
public enum Prompt {
    /**
     * 红色,错误
     */
    ERROR(R.drawable.common_bounced_icon_error, R.color.prompt_error),

    /**
     * 红色,警告
     */
    WARNING(R.drawable.common_bounced_icon_warning, R.color.prompt_warning),

    /**
     * 绿色,成功
     */
    SUCCESS(R.drawable.common_bounced_icon_successful, R.color.prompt_success);

    private int resIcon;
    private int backgroundColor;

    Prompt(int resIcon, int backgroundColor) {
        this.resIcon = resIcon;
        this.backgroundColor = backgroundColor;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
