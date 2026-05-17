import Foundation
import AVFoundation
import shared

class IOSCameraBridge: NSObject, AVCapturePhotoCaptureDelegate {

    private let session = AVCaptureSession()
    private let output = AVCapturePhotoOutput()
    private var onCapture: ((KotlinByteArray) -> Void)?

    override init() {
        super.init()

        let device = AVCaptureDevice.default(for: .video)
        let input = try! AVCaptureDeviceInput(device: device!)
        if session.canAddInput(input) { session.addInput(input) }
        if session.canAddOutput(output) { session.addOutput(output) }

        session.startRunning()
    }

    func startCapture(onCapture: @escaping (KotlinByteArray) -> Void) {
        self.onCapture = onCapture
        let settings = AVCapturePhotoSettings()
        output.capturePhoto(with: settings, delegate: self)
    }

    func photoOutput(
        _ output: AVCapturePhotoOutput,
        didFinishProcessingPhoto photo: AVCapturePhoto,
        error: Error?
    ) {
        if let data = photo.fileDataRepresentation() {
            let kb = KotlinByteArray(size: Int32(data.count))
            data.copyBytes(to: kb.asByteArray())
            onCapture?(kb)
        }
    }
}

extension KotlinByteArray {
    func asByteArray() -> UnsafeMutablePointer<UInt8> {
        let count = Int(self.size)
        let pointer = UnsafeMutablePointer<UInt8>.allocate(capacity: count)
        for i in 0..<count {
            pointer[i] = self.get(index: Int32(i))!
        }
        return pointer
    }
}
