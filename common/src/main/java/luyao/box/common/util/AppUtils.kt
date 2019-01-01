package luyao.box.common.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import android.net.Uri

/**
 * Created by luyao
 * on 2018/12/28 15:52
 */
object AppUtils {

    /**
     * 获取已安装非系统应用
     */
    fun getInstalledApp(context: Context): List<PackageInfo> {
        val packageManager = context.packageManager
        return packageManager.getInstalledPackages(0)
            .filter { (ApplicationInfo.FLAG_SYSTEM and it.applicationInfo.flags) == 0 }
    }


    fun getAppIcon(context: Context, packageInfo: PackageInfo): Drawable {
        return packageInfo.applicationInfo.loadIcon(context.packageManager)
    }

    fun getAppName(context: Context,packageInfo: PackageInfo):String{
        return packageInfo.applicationInfo.loadLabel(context.packageManager).toString()
    }

    fun uninstallApp(context: Context,packageName: String) {
        val intent = Intent(Intent.ACTION_DELETE)
        intent.data = Uri.parse("package:$packageName")
        context.startActivity(intent)
    }

    fun openApp(context: Context, packageName: String) {
        val intent=context.packageManager.getLaunchIntentForPackage(packageName)
        intent?.run { context.startActivity(this) }
    }

    fun openAppProperties(context: Context,packageName: String){
        val intent=Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName"))
        context.startActivity(intent)
    }

    fun openInStore(context: Context,packageName: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        try {
            intent.data = Uri.parse("market://details?id=$packageName")
            context.startActivity(intent)
        } catch (ifPlayStoreNotInstalled: ActivityNotFoundException) {
            intent.data =
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            context.startActivity(intent)
        }

    }
}