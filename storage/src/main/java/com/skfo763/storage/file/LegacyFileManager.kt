package com.skfo763.storage.file

import android.content.Context
import android.os.Environment
import android.util.Log
import com.skfo763.storage.file.FileManager.Companion.TAG
import java.io.File

class LegacyFileManager(private val context: Context) : FileManager {

    override val isExternalStorageWritable: Boolean
        get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

    override val isExternalStorageReadable: Boolean
        get() = Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)

    override fun getExternalDocumentsDir(): File? {
        val file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        if(!file.mkdirs()) {
            Log.e(TAG, "Directory not created")
        }
        return file
    }

    override fun getPrivateAlbumStorageDir(context: Context, name: String): File? {
        val file = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        if(file?.mkdir() != true) {
            Log.e(TAG, "Directory not created")
        }
        return file
    }

    override fun saveFile(filename: String) {





    }



}