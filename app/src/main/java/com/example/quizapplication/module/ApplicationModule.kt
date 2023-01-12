package com.example.quizapplication.module


import com.example.quizapplication.data.repositories.ThemesRepository
import com.example.quizapplication.data.repositories.ThemesRepositoryImpl
import com.example.quizapplication.R
import com.example.quizapplication.ui.MainViewModel
import com.example.quizapplication.ws.QuizzAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object ApplicationModule {
    val applicationModule : Module = module {

        single {
            provideRetrofit(get (),androidContext().getString(R.string.api_url))
        }

        single<ThemesRepository> {
            provideThemesRepository(get())
        }

        factory {
            provideQuizzApi(get())
        }

        single {
            provideHttpClient()
        }

        viewModel {
            provideMainViewModel(get())
        }
    }

    fun provideThemesRepository(api : QuizzAPI) : ThemesRepositoryImpl = ThemesRepositoryImpl(api)

    fun provideMainViewModel(repository : ThemesRepository) : MainViewModel = MainViewModel()

    fun provideRetrofit(client: OkHttpClient, api : String ) :  Retrofit {
        return Retrofit.Builder()
            .baseUrl(api)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun provideHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun provideQuizzApi(retrofit : Retrofit) : QuizzAPI = retrofit.create(QuizzAPI::class.java)

}