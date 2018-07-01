# Github Repository Search Overview

# Disclaimer
My goal was to not take much longer than 4 hours to finish this, 
because of that i had to take some decisions on where i would focus my energy. Below i will detail my reasoning.

## Architecture
I decide to use Clean Architecture + MVVM

## Presentation
I've decided to go with MVVM + LiveData, i had studied before and thought would be a good place to use it again. Usually at work i use MVP.

## Dependency Injection
I went with Dagger 2, not necessarily needed for such a small project but since i had setup from a previous project
i went with it.

## Domain
UseCases, responsible for holding the domain logic. In this example theres no business logic, except for some state handling.

## Network
I went with Retrofit2 and OkHttp which is something i'm familiar with. One thing to note here, is that i've added a `CacheInterceptor` and the reason for that is that the Github public API has very strong rate limit policies, so to avoid it breaking after 2 or 3 refresh i did that. Another thing is that the parameters for pagination / limiting result per pages didn't work for the no auth API, so for that i've limited the max result by 10. This is not something i'm really proud of but didn't want to take more time dealing with OAuth2, even though is not a big deal.  
## Test
Given my goals of not over spending time, i focused on testing the UseCase and the RemoteRepository. I tried to test the ViewModel too, but had some problems regarding the LiveData, so i left that out for now. Given the time, i didn't do any Espresso tests but would like to state that i've done before for Custom UI components.

## UI / UX
I feel this is the weaker part of my project, since it was only about displaying a list i went with a recycler view displaying data in a simple and clean way.

## Changes / Improvements
* Tests - Dig a bit deeper on why the livedata was giving problems and add coverage to it, add MockWebServer to test a bit better the network layer, including the interceptors.
* UI / UX - I think maybe coverting into a bit more detailed card layout could make the look and feel a bit better.
* Domain - I've done in a way that the Domain knows what is happening in the process of deliverying data, some may like this some may not. Another way would be just passing data to the ViewModel and it could propagate the error in a differente `LiveData` object.
* Network - Use the Authorized API where i could limit the result items and implement pagination.