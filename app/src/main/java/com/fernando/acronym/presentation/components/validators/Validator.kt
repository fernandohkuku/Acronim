package com.fernando.acronym.presentation.components.validators

interface Validator<T> {
    fun isInvalid(input: T) = !isValid(input)

    fun isValid(input: T): Boolean

    fun reset()
}
