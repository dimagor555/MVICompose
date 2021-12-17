package ru.dimagor555.mvicompose.abstraction

interface Reducer<State : Any, in Message : Any> {
    fun State.reduce(msg: Message): State
}