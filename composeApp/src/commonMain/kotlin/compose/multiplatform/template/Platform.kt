package compose.multiplatform.template

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform