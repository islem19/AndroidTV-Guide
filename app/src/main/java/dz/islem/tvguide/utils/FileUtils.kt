package dz.islem.tvguide.utils

import android.content.Context
import java.io.ByteArrayOutputStream
import java.io.IOException

object FileUtils {

    fun getJsonRaw(context: Context, resId: Int) : String {
        val inputStream = context.resources.openRawResource(resId)
        val outputStream = ByteArrayOutputStream()
        val bufferByte = ByteArray(1024)
        var length: Int
        try {
            while (inputStream.read(bufferByte).also { length = it } != -1) {
                outputStream.write(bufferByte, 0, length)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return outputStream.toString()
    }
}
