# Photo Gallery App
Onramp Android Take Home Project   

## Table of Context
 - [Application Overview](#Application-Overview)
 - [Architectural Overview](#Architectural-Overview)
 - [Project Requirements Met](#Project-Requirements)
 - [Installation](#Installation)
 - [Application Creation Process](#Application-Creation-Process)
 - [Technologies Used](#Technologies-Used)
 - [Resources Used](#Resources-Used)
 
## Application Overview
This photo gallery application lets users browse randomly selected photos from Unsplash.com. Users can save their favorite photos to the application by tapping the photo and tapping the heart image button. Users can also download photos to their internal storage by selecting 'download' from the overflow menu in the 'top app bar', switching on toggle for the photos they're interested in, and tapping 'START DOWNLOAD'. Downloaded photos can be found in Internal_storage>Android>data>com.onramp.android.takehome>files>Pictures>galleryApp.
"<insert favorites gif here>"

## Architectural Overview
(High level overview of e.g. names, relationships and purposes of all components, including Activities, Services, Content Providers, Broadcast Receivers, etc.)   
(description of design patterns leveraged)

## Project Requirements
This section goes over how I addressed the requirements below:
- [Activities and Fragment Components](#Activities-and-Fragment-Components)
- [Service Component](#Service-Component)
- [MVP Architecture Pattern](#MVP-Architecture-Pattern)
- [REST API](#REST-API)
- [UI Components from Android Material Design Component Library](#UI-Components-from-Android-Material-Design-Component-Library)
- [Data Persistence (Android Room)](#Data-Persistence-(Android-Room))

### Activities and Fragment Components
This application has "Explore" and "Favorites" activities. The 'top app bar' and 'bottom navigation' are implemented as fragments.

### Service Component
This application's photo downloading feature is a background service. I chose this feature as such because file downloads should continue even when users leave the application.
"<insert download features gif>"
### MVP Architecture Pattern

### REST API
This application uses the Retrofit API interface to easily make GET requests from the Unsplash API. I chose the random photos API route so that users could see different photos every time they open the 'Explore Activity'. I also planned to use the search route so that users can search through keywords.
### UI Components from Android Material Design Component Library
"<insert components png>"
UI Components Used:   
- App Bar: Top
- Switches
- Cards
- Button
- Bottom Navigation
- Snackbar

### Data Persistence (Android Room)

## Installation
Take these steps to run this application:   
   Using your phone or emulator: [APK Link]()  
````
        Phone -> Install .apk file on your phone and enable "download from unknown sources"
        Emulator -> Drag file to emulator
````
   Using this repo:   
````
        git clone <this repo>
        connect device (phone/emulator)
        Press 'Run app' (Android Studio)
````

## Application Creation Process
[Figma Link]()

## Technologies Used
- Android Studio (IDE)
- Kotlin (Language)
- Retrofit (REST Client built on OkHttp)
- Glide (Image Loading Framework)
- Android Room (Data Persistence, Local Database)
- Unsplash API (Third Party API)

## Resources Used

[Android Dev Docs on App Architecture](https://developer.android.com/jetpack/guide)   
[MVP Architecture Code Example](https://github.com/android/architecture-samples/tree/todo-mvp-kotlin)   
[MVP Architecture Video](https://www.youtube.com/watch?v=h0Y1R2mL7Ys&list=PLEVlop6sMHCqragWmqo7JF92QXQoidRmu&index=2)   
[MVP Architecture Tutorial](https://www.raywenderlich.com/7026-getting-started-with-mvp-model-view-presenter-on-android)    
[Logcat Video Tutorial](https://www.youtube.com/watch?v=pqH69LcD2Kk)   
[Material Design](https://material.io/)   
[Retrofit](https://square.github.io/retrofit/)   
[Retrofit Video Tutorial](https://www.youtube.com/watch?v=rAk1j2CmPJs&t=235s)   
[Android Room Documentation](https://developer.android.com/training/data-storage/room)   
[Android Room Video Tutorial](https://www.youtube.com/watch?v=lwAvI3WDXBY&t=728s)   
[Image Loading Library - Glide](https://github.com/bumptech/glide)   
[Using "setTag" Method](https://www.codota.com/code/java/methods/android.widget.ImageView/setTag)   
[Using GridView and BaseAdapter](https://www.youtube.com/watch?v=ReUBY5i9ojY&t=1002s)  
[Coroutine](https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#coroutine-context-and-dispatchers)   
[Coroutine Beginner Video Tutorial](https://www.youtube.com/watch?v=F63mhZk-1-Y)   
[Unsplash API](https://unsplash.com/documentation#get-a-random-photo)   
[Guide to Store Secret Keys](https://guides.codepath.com/android/Storing-Secret-Keys-in-Android)   
[Adding Fragments Basic Video Tutorial](https://www.youtube.com/watch?v=-vAI7RSPxOA)   
[Extracting APK](https://www.educative.io/edpresso/extracting-an-apk-file-from-android-studio)   
[Stack Overflow](https://stackoverflow.com/questions/7871430/increase-the-grid-spacing-in-android)   