# Jobs

This is an Android application built as part of an internship assignment. The app allows users to browse and bookmark job listings, with offline viewing support for bookmarked jobs. It implements modern Android development best practices with a focus on clean architecture, UI/UX, and performance.

## Features
- **Bottom Navigation**: The app provides a bottom navigation UI with two sections:
  - **Jobs**: Displays job listings fetched from an API.
  - **Bookmarks**: Displays all bookmarked jobs, which are stored locally for offline access.

- **Jobs Screen**: 
  - Fetches jobs data from the [API](https://testapi.getlokalapp.com/common/jobs?page=1) in a paginated manner.
  - Each job card displays the job title, location, salary, and phone number.
  - Clicking on a job opens a detailed view of the job.

- **Bookmarks Screen**:
  - Users can bookmark jobs from the job listings.
  - All bookmarked jobs are stored in a local database, ensuring they are available offline.

- **State Management**:
  - Proper handling of different states like loading, error, and empty data views.

## Tech Stack
- **Programming Language**: Kotlin
- **UI**: Jetpack Compose
- **Network**: Retrofit for API calls with pagination handling
- **Local Storage**: Room for storing bookmarked jobs offline
- **Dependency Injection**: Dagger Hilt

## Setup and Installation

  1. Clone the repository
  2. Open the project in Android Studio.
  3. Build and run the app on an emulator or a physical device.

## Video Demo

Watch the video demo of the app:

[Video-Demo](https://drive.google.com/file/d/1P2EyeNhYM0_iXslAjDiUHsm3uJv1WMmx/view?usp=sharing)
