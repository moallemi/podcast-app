# Podcast App using Jetpack Compose

## Overview

The Android Podcast App is an open-source mobile application designed for Android devices that allows users to discover, listen to, and subscribe to their favorite podcasts. This README provides an overview of the project, setup instructions, and key features.

## Features

- Browse and search for podcasts by title, category, or keyword.
- Play, pause, and skip through podcast episodes.
- User-friendly and intuitive interface for a seamless listening experience.

## Technologies Used

- **Kotlin**: The app is built using the Kotlin programming language.
- **Jetpack Compose**: The user interface is built using Jetpack Compose, a modern Android UI toolkit.


## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Android Studio installed on your development machine.
- Android device or emulator for testing.

### Installation

1. Clone the repository to your local machine:
   ```shell
   git clone https://github.com/moallemi/podcast-app.git
   ```
2. Open the project in Android Studio.
3. Build and run the app on your Android device or emulator.

## Development setup

First off, you require the latest [Android Studio Flamingo](https://developer.android.com/studio/preview) (or newer) to be able to build the app.

### Code style

This project uses [ktlint](https://github.com/pinterest/ktlint), provided via
the [spotless](https://github.com/diffplug/spotless) gradle plugin, and the bundled project IntelliJ codestyle.

If you find that one of your pull reviews does not pass the CI server check due to a code style conflict, you can
easily fix it by running: `./gradlew spotlessApply`.


Do not forget to restart Android Studio to apply changes to your environment.

## Contributions 🙌

This project is open for contributions. We encourage all developers to contribute and help improve the app. If you're looking to contribute, please follow the standard GitHub pull request process.

1. Fork the repo.
2. Create a new branch.
3. Make your changes and submit a pull request!