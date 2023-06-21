package com.yourparkingspace.androidtechtest.viewmodels.viewassistant

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class StatedVariable<T>(startWith: T) : MutableState<T> {
    private var _value by mutableStateOf(startWith)
    override var value: T = startWith
        get() = _value
        set(value) {
            _value = value
            field = value
        }

    override fun component1(): T = value
    override fun component2(): (T) -> Unit = { value = it }
}

class StatedBoolean(startWith: Boolean) : StatedVariable<Boolean>(startWith) {
}

class StatedString(startWith: String) : StatedVariable<String>(startWith) {
}
