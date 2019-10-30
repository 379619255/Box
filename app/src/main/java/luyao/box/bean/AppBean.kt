package luyao.box.bean

import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable

/**
 * Created by luyao
 * on 2018/12/28 16:32
 */
data class AppBean(
    val appName: String,
    val packageName: String,
    val versionName: String?, // 存在 versionName 为 null 的情况
    val sourceDir:String,
    val icon: Drawable,
    val packageInfo: PackageInfo
)