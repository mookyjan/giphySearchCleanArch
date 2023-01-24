package com.mudassir.core

interface UseCase {

    interface UseCaseWithParam<params: Any?, T: Any> : UseCase {
        suspend fun executeAsync(param: params?) : Resource<T>
    }

    interface UseCaseWithoutParam<T: Any> : UseCase {
        suspend fun executeAsync(): Resource<T>
    }

    abstract class Params
}