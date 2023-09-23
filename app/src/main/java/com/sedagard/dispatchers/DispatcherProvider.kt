package com.sedagard.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatcherProvider {
  fun main(): CoroutineDispatcher
  fun io(): CoroutineDispatcher
}

class DefaultDispatcherProvider @Inject constructor() : DispatcherProvider {
  override fun main(): CoroutineDispatcher = Dispatchers.Main
  override fun io(): CoroutineDispatcher = Dispatchers.IO
}
