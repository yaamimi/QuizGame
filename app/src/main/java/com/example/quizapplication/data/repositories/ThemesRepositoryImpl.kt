package com.example.quizapplication.data.repositories


import com.example.quizapplication.data.model.ThemeResponse
import com.example.quizapplication.ws.QuizzAPI
import io.reactivex.Single

class ThemesRepositoryImpl(
    private val api: QuizzAPI
) : ThemesRepository {
    override fun getThemes(): Single<ThemeResponse> {
        return api.getThemes()
    }

}