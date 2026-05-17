package com.smartbite.shared.camera

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.ByteArrayOutputStream
import android.graphics.Bitmap
import android.graphics.ImageFormat
import androidx.camera.core.ImageProxy
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class CameraController actual constructor() {

    private lateinit var context: Context
    private lateinit var imageCapture: ImageCapture

    fun attachContext(ctx: Context) {
        context = ctx
    }

    private suspend fun setup() {
        val cameraProvider = suspendCancellableCoroutine<ProcessCameraProvider> { cont ->
            val future = ProcessCameraProvider.getInstance(context)
            future.addListener({
                cont.resume(future.get())
            }, ContextCompat.getMainExecutor(context))
        }

        imageCapture = ImageCapture.Builder()
            .setTargetRotation(android.view.Surface.ROTATION_0)
            .build()

        val cameraSelector = androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA

        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            context as androidx.lifecycle.LifecycleOwner,
            cameraSelector,
            imageCapture
        )
    }

    actual suspend fun capture(): ByteArray {
        if (!::imageCapture.isInitialized) {
            setup()
        }

        return suspendCancellableCoroutine { cont ->
            val outputOptions = ImageCapture.OutputFileOptions.Builder(
                context.cacheDir.resolve("photo.jpg")
            ).build()

            imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        try {
                            val file = context.cacheDir.resolve("photo.jpg")
                            cont.resume(file.readBytes())
                        } catch (e: Exception) {
                            cont.resumeWithException(e)
                        }
                    }

                    override fun onError(exc: ImageCaptureException) {
                        cont.resumeWithException(exc)
                    }
                }
            )
        }
    }
}
