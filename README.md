# Giphy-App



### Important  ###
There are two branches for this project
1) [Develop](https://github.com/mookyjan/giphySearchCleanArch/tree/develop) -> In this branch api is call only once data is loaded and user can search and make favourite/ UnFavourite
2) [develop_pagination](https://github.com/mookyjan/giphySearchCleanArch/tree/develop_pagination) -> this branch have additional setup for pagination so user can scroll unlimited number of gifs


### Structure of the code ###
Simple Android Application written in Kotlin.
This project follows Clean Architecture with MVVM with Clean Architecture Design

Project consist of One Activity and Two Fragments
1) Home Fragment -> When use launch the app Trending list of Gif is shown , there is search option for the user when user enter any search query based on it the latest result will be shown to it.
2) Favourite Fragment -> In this screen user can see their favourites GiF, can add and remove the Gif from list.

# Main libraries used

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Kotlin Coroutine](https://kotlinlang.org/docs/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - Used it for the navigation from one fragment to another fragments
  - [Room](https://developer.android.com/reference/androidx/room/package-summary) -Used for local data storage
  - [Paging 3.0](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) -to load large amount of data in chunks instead to load all the records in one api call. Paging 3.0 will automatically call the api to laod more data when user reached to end of the screen.
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Dagger2](https://dagger.dev/) - Standard library to incorporate Dagger dependency injection into an Android application. 
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Glide](https://bumptech.github.io/glide/) - Used for loading images
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Timber](https://github.com/JakeWharton/timber) -Used for loggging 
- [Junit](https://junit.org/) - For Unit Testing
- [Mockk](https://mockk.io/) - For mocking in Unit Testing


# Architecture Design
![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/diagram/clean%20architecture%20design.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/diagram/clean-mvvm.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/diagram/project%20structure.png)


# Modules

* `core/` : contains the common functions
* `data/` : contains the code to access to the data (repository pattern)
* `domain/` : contains the business logic and the usecases
* `app` : Presentation layer, contains the UI 

this project consist of Two screen. on the first screen showing the list of movies list and on clicking the item
go to the details of the movie.
Swipe to Refresh can be used to refresh data and when user scroll new list of movies will be automatically
and for the simplicity of this project many things have been kept simple
like 
* ErrorHandling, 
* Internet connectivity and 
* Design of the app is also kept sample and can be improved much more

comments are written with the function that what it will do.

also TODO are given in the area which we can improve more.

# Screenshots
![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230128_123452.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230128_123527.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230128_123556.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230128_123622.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230128_123809.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230129_121518.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230129_121558.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230129_195746.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230129_202819.png)

![alt text](https://github.com/mookyjan/giphySearchCleanArch/blob/develop_pagination/screenshots/Screenshot_20230129_211827.png)


##TODO

need to make offline mode fist app

adding more unit test cases

setup for CI/CD

improving the design





