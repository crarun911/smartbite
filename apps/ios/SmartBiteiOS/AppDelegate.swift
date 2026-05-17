import UIKit
import shared

class AppDelegate: NSObject, UIApplicationDelegate {

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {

        // If you have DI init on iOS, call it here
        // KoinKt.doInitKoin()  <-- optional based on your shared setup

        return true
    }
}
