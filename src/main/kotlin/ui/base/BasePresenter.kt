package ui.base

interface BasePresenter<A, R> {

    fun action(action: A): R
}