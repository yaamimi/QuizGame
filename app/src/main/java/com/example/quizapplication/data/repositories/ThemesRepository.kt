package com.example.quizapplication.data.repositories

import com.example.quizapplication.data.model.ThemeResponse
import io.reactivex.Single

interface ThemesRepository {
    fun getThemes(): Single<ThemeResponse>
}