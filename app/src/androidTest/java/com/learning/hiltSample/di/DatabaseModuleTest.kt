package com.learning.hiltSample.di

import android.content.Context
import androidx.room.Room
import com.learning.hiltSample.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
@Module
class DatabaseModuleTest {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            MoviesDatabase::class.java
        ).allowMainThreadQueries().build()
    }
}