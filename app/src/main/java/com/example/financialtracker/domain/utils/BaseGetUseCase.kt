package com.example.financialtracker.domain.utils

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<in Params, Type: Any, in Model> {
    abstract suspend fun execute(params: Params): Flow<Type>
    abstract suspend fun insert(model: Model)
}
object None