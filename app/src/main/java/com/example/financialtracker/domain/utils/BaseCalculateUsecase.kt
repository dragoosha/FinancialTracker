package com.example.financialtracker.domain.utils

import kotlinx.coroutines.flow.Flow

abstract class BaseCalculateUsecase<Type: Any> {
    abstract suspend fun execute() : Flow<Type>
}
