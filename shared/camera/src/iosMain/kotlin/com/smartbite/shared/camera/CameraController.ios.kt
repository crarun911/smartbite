package com.smartbite.shared.camera

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.AVFoundation.*
import platform.Foundation.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class CameraController actual constructor() {

    private val session = AVCaptureSession()
    private val output = AVCapturePhotoOutput()

    init {
        val device = AVCaptureDevice.defaultDeviceWithMediaType(AVMediaTypeVideo)
        val input = AVCaptureDeviceInput.deviceInputWithDevice(device, error = null)

        if (session.canAddInput(input)) session.addInput(input)
        if (session.canAddOutput(output)) session.addOutput(output)

        session.startRunning()
    }

    actual suspend fun capture(): ByteArray {
        return suspendCancellableCoroutine { cont ->

            val settings = AVCapturePhotoSettings.photoSettings()
            output.capturePhotoWithSettings(settings, object : NSObject(), AVCapturePhotoCaptureDelegateProtocol {

                override fun captureOutput(
                    output: AVCapturePhotoOutput,
                    didFinishProcessingPhoto: AVCapturePhoto,
                    error: NSError?
                ) {
                    if (error != null) {
                        cont.resumeWithException(Exception(error.localizedDescription))
                        return
                    }

                    val data = didFinishProcessingPhoto.fileDataRepresentation()
                    if (data != null) cont.resume(data.toByteArray())
                    else cont.resumeWithException(Exception("No image data"))
                }
            })
        }
    }
}

fun NSData.toByteArray(): ByteArray {
    val bytes = ByteArray(this.length.toInt())
    this.getBytes(bytes.refTo(0), this.length)
    return bytes
}
