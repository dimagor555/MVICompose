package ru.dimagor555.mvicompose.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.dimagor555.mvicompose.abstraction.Store

abstract class MviViewModel<in Action : Any, out State : Any, out SideEffect : Any>(
    private val store: Store<Action, State, SideEffect>
) : ViewModel() {
    val state = store.state
    val sideEffects = store.sideEffects

    init {
        store.init(viewModelScope)
    }

    fun sendAction(action: Action) = store.sendAction(action)
}