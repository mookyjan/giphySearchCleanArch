package com.mudassir.core

interface UseCase {

    interface UseCaseWithParam<params: Any?, T: Any> : UseCase {
        suspend fun executeAsync(param: params?) : Resource<T>
    }

    interface UseCaseWithoutParam<T: Any> : UseCase {
        suspend fun executeAsync(): Resource<T>
    }

    interface UseCaseWithoutResource<params: Any?, T: Any> : UseCase {
        fun executeAsync(param: params?): T
    }

    interface UseCaseWithoutParamResource<T: Any> : UseCase {
        fun execute(): T
    }

    abstract class Params
}