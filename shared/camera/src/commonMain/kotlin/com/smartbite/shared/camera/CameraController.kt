package com.smartbite.shared.camera

/**
 * Cross‑platform camera controller.
 * 
 * Simple use:
 * val controller = CameraController()
 * val bytes: ByteArray = controller.capture()
 */
expect class CameraController() {
    suspend fun capture(): ByteArray
}
