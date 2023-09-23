package com.sedagard

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import java.io.InputStream

interface AppAssetManager {

  fun openFile(fileName: String): InputStream
}

class AndroidAppAssetManager(
  private val context: Context,
) : AppAssetManager {

  override fun openFile(fileName: String): InputStream {
    return context.assets.open(fileName)
  }
}

@Module
@InstallIn(SingletonComponent::class)
object AppAssetModule {

  @Provides
  fun provideAppAssetManager(
    @ApplicationContext context: Context,
  ): AppAssetManager = AndroidAppAssetManager(context)

  @Provides
  fun providesJson(): Json = Json {
    ignoreUnknownKeys = true
  }
}
