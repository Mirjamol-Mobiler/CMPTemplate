package compose.multiplatform.template.app

import android.app.Application
import compose.multiplatform.template.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TemplateApplication)
            androidLogger()
            modules(appModules())
        }
    }

}