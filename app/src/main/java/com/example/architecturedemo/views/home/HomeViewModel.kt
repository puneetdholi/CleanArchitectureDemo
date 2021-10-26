package com.example.architecturedemo.views.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.GetAllUsersUseCase
import com.example.domain.model.UserDomain
import com.example.architecturedemo.utils.Resource
import com.example.architecturedemo.utils.ResourceState
import io.reactivex.observers.DisposableObserver

class HomeViewModel constructor(private val getAllUsersUseCase: GetAllUsersUseCase) : ViewModel() {

    private var navigator: HomeNavigator? = null
    var getAllUsersLiveData: MutableLiveData<Resource<List<UserDomain>>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        getAllUsersUseCase.dispose()
    }

    fun getAllUsers() {
        getAllUsersLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getAllUsersUseCase.execute(object : DisposableObserver<List<UserDomain>>() {
            override fun onComplete() {
                Log.d("api_response", "getAllUsers onComplete called")
            }

            override fun onNext(data: List<UserDomain>) {
                getAllUsersLiveData.postValue(Resource(ResourceState.SUCCESS, data, null))
                Log.d("api_response", "getAllUsers onNext called")
            }

            override fun onError(e: Throwable) {
                Log.d("api_response", "getAllUsers onError called")
                getAllUsersLiveData.postValue(Resource(ResourceState.ERROR, null, e.message))
                e.message?.let { navigator?.showErrorMessage(it) }
            }
        }, Unit)
    }

    fun setNavigator(homeNavigator: HomeNavigator) {
        navigator = homeNavigator
    }
}