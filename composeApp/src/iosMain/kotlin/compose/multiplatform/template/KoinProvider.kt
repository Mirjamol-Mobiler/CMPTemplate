package compose.multiplatform.template

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModules())
    }
}
