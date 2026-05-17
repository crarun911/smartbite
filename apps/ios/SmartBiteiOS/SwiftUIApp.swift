import SwiftUI

@main
struct SmartBiteiOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    var body: some Scene {
        WindowGroup {
            ComposeViewControllerRepresentable()
                .edgesIgnoringSafeArea(.all)
        }
    }
}
