package com.example.domain.interactor

import com.example.domain.model.UserDomain
import com.example.domain.repository.UserRepository
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import io.reactivex.Observable
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<UserDomain>, Unit>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Unit): Observable<List<UserDomain>> {
        return userRepository.getAllUsers()
    }
}