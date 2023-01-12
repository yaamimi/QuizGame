package com.example.quizapplication.ui

import android.content.Context
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapplication.data.repositories.ThemesRepository
import com.example.quizapplication.data.model.ThemeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.logger.Level

class MainViewModel : ViewModel(), KoinComponent {
    private val repository: ThemesRepository by inject()
    private val context : Context by inject()
    private val liveThemes: MutableLiveData<Resource<ThemeResponse>> by lazy(
        LazyThreadSafetyMode.NONE,
        initializer = { MutableLiveData<Resource<ThemeResponse>>() })

    val disposables = CompositeDisposable()

    fun launch(job: () -> Disposable) {
        disposables.add(job())
    }

    fun getThemes(): LiveData<Resource<ThemeResponse>> {
        disposables.add(repository.getThemes()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveThemes.value = Resource(Status.LOADING, null, null)
            }
            .doOnError { t ->
                liveThemes.value = Resource(Status.ERROR, null, t.message)
                getKoin().logger.log(Level.ERROR, t.message.toString())
            }
            .subscribe(
                { liveThemes.value = Resource(Status.SUCCESS, it, null) },
                { getKoin().logger.log(Level.ERROR, it.message.toString()) }
            )
        )
        return liveThemes
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
        companion object {
            fun <T> success(data: T?): Resource<T> {
                return Resource(Status.SUCCESS, data, null)
            }

            fun <T> error(msg: String): Resource<T> {
                return Resource(Status.ERROR, null, msg)
            }

            fun <T> loading(): Resource<T> {
                return Resource(Status.LOADING, null, null)
            }
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}