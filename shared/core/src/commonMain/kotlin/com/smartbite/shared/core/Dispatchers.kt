package com.smartbite.shared.core

import kotlinx.coroutines.CoroutineDispatcher import kotlinx.coroutines.Dispatchers

object AppDispatchers { val io: CoroutineDispatcher = Dispatchers.Default val main: CoroutineDispatcher = Dispatchers.Main }