package com.example.quizapplication.ws

import com.example.quizapplication.data.model.ThemeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface QuizzAPI {

    @GET("369a0b8f-1b07-49cb-9119-74793f8926dd")
    fun getThemes(): Single<ThemeResponse>
}