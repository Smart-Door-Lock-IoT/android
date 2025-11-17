package id.my.smartdoorlock.core.di

import id.my.smartdoorlock.BuildConfig
import id.my.smartdoorlock.openapi.apis.ConfigurationApi
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import org.koin.dsl.module

private const val API_BASE_URL = BuildConfig.API_BASE_URL

val serviceModule = module {
    single<((HttpClientConfig<*>) -> Unit)> {
        {
            it.expectSuccess = true

            it.install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

//            it.install(Auth) {
//                bearer {
//                    loadTokens {
//                        val tokenManager = get<TokenManager>()
//                        val token = tokenManager.get()
//
//                        BearerTokens(
//                            accessToken = token.accessToken,
//                            refreshToken = null
//                        )
//                    }
//                }
//            }
        }
    }

    // api services
    single { ConfigurationApi(API_BASE_URL, httpClientConfig = get()) }
}