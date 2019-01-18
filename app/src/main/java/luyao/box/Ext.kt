package luyao.box

import android.content.Context
import android.widget.Toast
import luyao.box.common.util.FileUtils.deleteFile
import java.io.File

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}

fun Context.px2dp(pxValue: Int): Int {
    val scale = resources.displayMetrics.density;
    return (pxValue / scale + 0.5f).toInt()
}

fun Context.dp2px(dpValue: Int): Int {
    val scale = resources.displayMetrics.density;
    return (dpValue * scale + 0.5f).toInt()
}

fun Context.getWidth() = resources.displayMetrics.widthPixels

fun Context.getHeight() = resources.displayMetrics.heightPixels

// delete file && folder
fun File.deleteAll() {
    deleteFile(this)
}


