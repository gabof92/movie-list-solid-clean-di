package com.example.movies.di

import javax.inject.Qualifier

/**
 * This class replaces the @Named annotation for "apiKey"
 * to avoid typos and make it easier to refactor.
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey