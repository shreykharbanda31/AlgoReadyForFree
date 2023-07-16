# AlgoReady For Free

Introducing the ultimate Android app for all aspiring tech enthusiasts: a one-stop hub for the finest Data Structures and Algorithms content! Seamlessly curated and effortlessly accessible, this app revolutionizes the way you learn and grow in the world of programming.

Unlocking a treasure trove of knowledge, our app scours the vast expanse of YouTube to extract the absolute best video resources. And the best part? It's completely free! Dive deep into an extensive library of top-notch tutorials, engaging lectures, and insightful discussions that will elevate your understanding of Data Structures and Algorithms to new heights.

This app is committed to supporting underrepresented communities and believe in providing equal opportunities for career advancement. That's why we're proud to offer this app as a free resource, empowering individuals from all backgrounds to access high-quality educational content and propel their careers forward.

Imagine having a virtual mentor guiding you through every step of the way. With this app, you'll gain the confidence to conquer technical interviews with ease. Arm yourself with the essential skills and problem-solving techniques sought after by leading tech companies, propelling you towards your dream internship or job opportunity.

No more overwhelming searches or scattered learning materials. This app offers a seamless and intuitive interface, empowering you to navigate through a wealth of knowledge effortlessly. Whether you're a beginner embarking on your programming journey or a seasoned professional seeking to sharpen your skills, this app is tailored to meet your needs at every stage.

Embrace the power of comprehensive and accessible learning. Install this app today and unlock a world of possibilities. Your programming prowess awaits—let's embark on this transformative learning adventure together!

## AlgoReady
Published by Shrey Kharbanda on July 15, 2023

This app demonstrates the following views and techniques:

* [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service
* [Moshi](https://github.com/square/moshi) which handles the deserialization of the returned JSON to Kotlin data objects
* [Glide](https://bumptech.github.io/glide/) to load and cache images by URL.
* [AndroidYoutubePlayer](https://github.com/PierfrancescoSoffritti/android-youtube-player) to load and display YouTube videos
* [Firebase](https://firebase.google.com/) for user login/registration authentication
* [Google MS Play-Services](https://developers.google.com/android/guides/client-auth) for Google Sign-In

It leverages the following components from the Jetpack library:

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding adapters
* [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) with the SafeArgs plugin for parameter passing between fragments

It connects to a self-designed API server, hosted on Netlify:

* [APIAlgoReady](https://github.com/shreykharbanda31/APIAlgoReady) which returns a JSON collection of YouTube Data via the GET request at the "/youtubedata" endpoint

## Video Demonstration

![VideoDemo](videos/My%20video%20-%20Date%20(online-video-cutter.com).mp4)

## How to use this repo to install the app

1. Clone the repo.
2. Sync Gradle files and Build Project
3. Start Upgrade Assistant, if you face compatibility issues (preferred SDK: 33)
4. Build and run the app on a physical Android device or an EVD
5. Login/Register using a Valid E-mail Address and Strong Password
6. Enjoy the app's functionalities!

## Contributing
Contributions to the AlgoReady For Free App are welcome! If you would like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch:
```bash
git checkout -b feature/your-feature
```
3. Make your changes and commit them: 
```bash
git commit -m "Add your feature"
```
4. Push to the branch: 
```bash
git push origin feature/your-feature
```
5. Submit a pull request.

## License
The AlgoReady For Free App is released under the [MIT License](https://opensource.org/licenses/MIT).

## Report Issues
Notice any issues with a repository? Please file a github issue in the repository.

## Contact
If you have any questions, suggestions, or feedback, please feel free to reach out at [shrey.kharbanda@nyu.edu](mailto:shrey.kharbanda@nyu.edu).
