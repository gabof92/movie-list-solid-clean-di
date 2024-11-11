# Popular Movies App 🎬

A robust Android app designed to showcase knowledge of **SOLID principles**, **Clean Architecture**, and **Dependency Injection**. This app leverages TheMovieDatabase (TMDb) API to display a list of popular movies in your region, view movie details, and manage a list of favorites.

## Key Architecture and Design Principles
- **SOLID Principles**: Follows best practices in software design to ensure a modular, flexible, and maintainable codebase.
- **Clean Architecture**: Layers the app into presentation, domain, and data layers to maintain separation of concerns and enhance testability.
- **Dependency Injection**: Utilizes Dagger/Hilt to handle dependencies, making the app easier to scale and modify.

## Features
- **Browse Popular Movies**: Discover the latest trending movies in your region.
- **View Movie Details**: Access detailed movie information, including synopsis, release date, rating, and genres.
- **Mark as Favorite**: Keep track of movies you love by adding them to a personal favorites list.

## Technology Stack
- **Kotlin**: Built with Kotlin for modern, expressive code.
- **TMDb API**: Uses the TMDb API to fetch movie data.
- **Jetpack Components**: Incorporates ViewModel, LiveData, and other Jetpack tools for lifecycle-aware and responsive UI.

## Getting Started
1. Clone this repository.
2. Obtain a TMDb API key from [TheMovieDatabase](https://www.themoviedb.org/).
3. Add your API key to the appropriate file.

This project is an example of how to structure an Android app with solid design principles, ensuring clean, scalable, and maintainable code.
