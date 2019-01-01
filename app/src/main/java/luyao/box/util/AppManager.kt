package luyao.box.util

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import luyao.box.bean.AppBean
import luyao.box.common.util.AppUtils.getAppIcon
import luyao.box.common.util.AppUtils.getAppName
import luyao.box.common.util.AppUtils.getInstalledApp
import java.io.File

/**
 * Created by luyao
 * on 2018/12/29 13:42
 */
object AppManager {

    fun getInstalledAppBean(context: Context): List<AppBean> {
        val installedAppBeanList = mutableListOf<AppBean>()
        getInstalledApp(context).forEach {
            val appBean = AppBean(
                getAppName(context, it),
                it.packageName,
                it.versionName,
                getAppIcon(context, it)
            )
            installedAppBeanList.add(appBean)
        }
        return installedAppBeanList
    }

//    fun getApkFile(context: Context,appBean: AppBean):File{
//
//    }


}