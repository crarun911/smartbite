import UIKit
import shared
import SwiftUI
import ComposeApp

class ComposeRootViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()

        let root = ComposeUIViewControllerKt.ComposeRootController()
        addChild(root)
        root.view.frame = view.bounds
        view.addSubview(root.view)
        root.didMove(toParent: self)
    }
}

struct ComposeViewControllerRepresentable: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return ComposeRootViewController()
    }
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
