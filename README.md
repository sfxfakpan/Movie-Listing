# MOVIE LISTING

## Introduction
Build an Android App displaying a list of items and their detail when clicking on it.

## Technologies & Architecture
* Android, Kotlin
* [The Movie Database](https://www.themoviedb.org/)
* [Retrofit HTTP Client](https://square.github.io/retrofit/)
* [ROOM](https://developer.android.com/training/data-storage/room)
* [OkHttp](https://square.github.io/okhttp/) - Interceptor
* [Coil](https://coil-kt.github.io/coil/getting_started/) - Loading images, caching
* [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)

#### Architecture
Model-View-ViewModel (MVVM)

## Features
**Movies:** Discover movies
**Movie details:** Title, genres, rating, overview, date, runtime, language, videos, and cast

## Setup

#### Requirements
* Basic knowledge about Android Studio

#### The Movie Database API
1. Sign up and retrieve a API key from [The Movie Database API](https://www.themoviedb.org/documentation/api)

#### Project
1. Download and open the project in Android Studio
2. Edit the file local.properties and add the line: API_KEY="YOUR_KEY"
3. Connect your Android phone or use the emulator to start the application
4. Alternatively, you can download the build artifact from github actions.