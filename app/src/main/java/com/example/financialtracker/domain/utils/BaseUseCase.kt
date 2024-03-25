package com.example.financialtracker.domain.utils

abstract class BaseUseCase<out Type, in Params, in Model> where Type : Any? {

    abstract suspend fun execute(params: Params): Type
    abstract suspend fun insert(model: Model)
}

object None