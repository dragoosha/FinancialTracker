package com.example.financialtracker.domain.utils

import com.example.financialtracker.domain.model.IncomeModel
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<in Params, Type: Any, in Model> {
    abstract suspend fun execute(params: Params): Single<Type>
    abstract suspend fun insert(model: Model)
}
object None