# Compose Sample App

A modern Android application demonstrating best practices using Jetpack Compose, MVVM architecture, and offline-first capabilities.

## Architecture

The project follows the **MVVM (Model-View-ViewModel)** pattern with a **Layered Architecture**:

- **UI Layer**: Built entirely with **Jetpack Compose**. It uses `Flow` and `State` to observe data changes from ViewModels.
- **Data Layer**: Responsible for data operations. It includes:
    - **Repositories**: Coordinating data between different sources.
    - **Local DB**: Using **Room** for persistent storage.
    - **Network**: Using **Retrofit** for API communication.
- **Domain Layer**: (Optional/Implied) Handles business logic.

## Key Features & Tech Stack

- **Jetpack Compose**: Modern declarative UI toolkit.
- **Hilt (Dependency Injection)**: For decoupled and testable code.
- **Paging 3**: Implementation of **Offline-First** support using `RemoteMediator` to synchronize network data with the local **RoomDB**.
- **Room Persistence**: Local database for caching and offline access.
- **Retrofit & OkHttp**: Networking stack for REST API consumption.
- **Coroutines & Flow**: For reactive and asynchronous programming.
- **Firebase**: Integrated with Analytics, Crashlytics, and Authentication.
- **Glide (Compose)**: For efficient image loading.
- **Lottie**: For high-quality animations.

## Project Structure

- `com.sample.compose.ui`: Composable screens, themes, and UI logic.
- `com.sample.compose.data`: Repository implementations, Room entities/DAOs, and API DTOs.
- `com.sample.compose.data.repository`: Contains `BreedRepository` and `BreedRemoteMediator` showcasing the Paging 3 logic.
- `com.sample.compose.di`: Hilt modules for providing dependencies.

## Setup

1. Clone the repository.
2. Open in Android Studio (Ladybug or newer recommended).
3. Build and run the `:app` module.
