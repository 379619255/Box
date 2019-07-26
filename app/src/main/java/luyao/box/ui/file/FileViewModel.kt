package luyao.box.ui.file

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import luyao.box.bean.IFile
import luyao.box.moveToWithProgress
import luyao.box.util.FileUtils
import luyao.util.ktx.base.BaseViewModel
import java.io.File

/**
 * Created by luyao
 * on 2019/7/19 15:08
 */
class FileViewModel : BaseViewModel() {

    val fileListData: MutableLiveData<List<IFile>> = MutableLiveData()
    val mProgress: MutableLiveData<Int> = MutableLiveData() // 当前文件操作进度
    val mCurrentFileName: MutableLiveData<String> = MutableLiveData() // 当前操作的文件名
    val mRefreshTag: MutableLiveData<Int> = MutableLiveData()

    fun getFileListAsync(rootPath: String) {
        launch {
            val result = async(Dispatchers.IO) { FileUtils.getFileList(rootPath) }
            fileListData.value = result.await()
        }
    }

    fun pasteAsync(fileList: List<IFile>, destFolder: File, reserved: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            for (file in fileList) {
                file.getFile().moveToWithProgress(destFolder, true) { file, progress ->
                    launchOnUITryCatch({
                        mCurrentFileName.value = file.name
                        mProgress.value = progress
                    })
                }
                if (!reserved) file.delete()
            }
            launchOnUITryCatch({
                mProgress.value = -1
                mRefreshTag.value = 1
            })
        }
    }

    fun deleteAsync(file: File) {
        CoroutineScope(Dispatchers.IO).launch {
            file.deleteRecursively()
            launchOnUITryCatch({ mRefreshTag.value = 1 })
        }
    }


}