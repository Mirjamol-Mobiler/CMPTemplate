import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init(){
        KoinProviderKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
