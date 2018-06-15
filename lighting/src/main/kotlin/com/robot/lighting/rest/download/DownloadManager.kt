package com.robot.lighting.rest.download

import com.robot.lighting.rest.DownloadService
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.util.concurrent.TimeUnit

/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/05/10
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
class DownloadManager() {

    private lateinit var downloadListener: DownloadListener

    fun startDownload(downloadUrl: String, filePath: String, downloadListener: DownloadListener) {
        this.downloadListener = downloadListener
        val apiService = createRestClient().create(DownloadService::class.java)
        apiService.dowloadApk(downloadUrl)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map {
                    return@map it.byteStream()
                }
                .observeOn(Schedulers.computation())
                .doOnNext {
                    writeFile(it, filePath)
                    downloadListener.onFinishDownload()
                }
                .subscribeBy(
                        onError = {
                            downloadListener.onFail(it.message ?: "未知异常")
                        }
                )
    }

    private fun createRestClient(): Retrofit {
        val logging = HttpLoggingInterceptor()
        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)
                .addInterceptor(DownloadInterceptor(downloadListener))
                .build()

        return Retrofit.Builder()
                .baseUrl("http://www.baidu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    /**
     * 将输入流写入文件
     *
     * @param inputString
     * @param filePath
     */
    private fun writeFile(inputString: InputStream, filePath: String) {

        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)

            val b = ByteArray(1024)

            var len = inputString.read(b)
            while (len != -1) {
                fos.write(b, 0, len)
                len = inputString.read(b)
            }
            inputString.close()
            fos.close()

        } catch (e: FileNotFoundException) {
            if (fos != null)
                fos.close()
            downloadListener.onFail("FileNotFoundException")
        } catch (e: IOException) {
            if (fos != null)
                fos.close()
            downloadListener.onFail("IOException")
        } catch (e: Exception) {
            if (fos != null)
                fos.close()
            downloadListener.onFail("下载超时")
        }

    }

    companion object {
        /**
         * 连接超时 15秒
         */
        private const val CONNECTION_TIME_OUT = 15L
        /**
         * 读取超时15秒
         */
        private const val READ_TIME_OUT = 15L
        /**
         * 写出超时15秒
         */
        private const val WRITE_TIME_OUT = 15L

        fun getInstance(): DownloadManager {
            return DownloadManager()
        }
    }

}