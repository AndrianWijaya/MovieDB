package dependencies

import dependencies.retrofit_okhttp.*
import dependencies.retrofit_okhttp.retrofit
import dependencies.android.*
import dependencies.kotlin.*

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.appLibraries() {
    androidCore()
    materialDesign()
    testUnit()
    androidX()
    gander()
    vmLifeCycle()
    coroutine()
    gson()
    okHttp()
    retrofit()
    glide()
    dagger()
    androidPaging()
    youtube()
}