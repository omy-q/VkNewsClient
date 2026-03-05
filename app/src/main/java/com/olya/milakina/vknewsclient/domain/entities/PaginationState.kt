package com.olya.milakina.vknewsclient.domain.entities

sealed interface PaginationState<out T : Any> {
    data class FailureLoading<out T : Any>(val error: Throwable) : PaginationState<T>
    class NextPageLoading<out T : Any> : PaginationState<T>
    class FirstPageLoading<out T : Any> : PaginationState<T>
    data class PageLoaded<out T : Any>(val data: List<T>) : PaginationState<T>
    data class AllPagesLoaded<out T : Any>(val data: List<T>) : PaginationState<T>
}
