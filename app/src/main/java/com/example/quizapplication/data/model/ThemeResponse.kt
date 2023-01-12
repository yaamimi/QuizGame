package com.example.quizapplication.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThemeResponse (
    @SerializedName("theme")
    val themes: List<Theme>,
    @SerializedName("error")
    val error: String?
): Parcelable

@Parcelize
data class Theme(
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("question")
    val questions: List<Question>
): Parcelable

@Parcelize
data class Question(
    @SerializedName("label")
    val label: String,
    @SerializedName("answer")
    val answers: List<Answer>
): Parcelable

@Parcelize
data class Answer(
    @SerializedName("label")
    val label: String,
    @SerializedName("correct")
    val correct: Boolean
): Parcelable