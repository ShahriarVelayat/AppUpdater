<h1 align="center">AppUpdater</h1>
<h4 align="center">Android Library</h4>

<p align="center">
  <a target="_blank" href="https://android-arsenal.com/api?level=8"><img src="https://img.shields.io/badge/API-8%2B-orange.svg"></a>
  <a target="_blank" href="http://android-arsenal.com/details/1/3094"><img src="https://img.shields.io/badge/Android%20Arsenal-AppUpdater-blue.svg"></a>
  <a target="_blank" href="https://travis-ci.org/javiersantos/AppUpdater"><img src="https://travis-ci.org/javiersantos/AppUpdater.svg?branch=master"></a>
  <a target="_blank" href="https://www.paypal.me/javiersantos" title="Donate using PayPal"><img src="https://img.shields.io/badge/paypal-donate-yellow.svg" /></a>
  <a target="_blank" href="http://patreon.com/javiersantos" title="Donate using Patreon"><img src="https://img.shields.io/badge/patreon-donate-yellow.svg" /></a>
</p>

<p align="center">Android Library that checks for updates on Google Play, GitHub, Amazon or F-Droid. This library notifies your apps' updates by showing a Material dialog, Snackbar or notification.</p>

## How to include
Add the repository to your project **build.gradle**:
```Javascript
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```

And add the library to your module **build.gradle**:
```Javascript
dependencies {
    compile 'com.github.javiersantos:AppUpdater:1.0.2'
}
```

## Usage
Add **INTERNET** and **ACCESS_NETWORK_STATE** permissions to your app's Manifest:
```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

### Activity
```Java
AppUpdater appUpdater = new AppUpdater(this);
appUpdater.init();
```

## Customization

Use the builder and add following:
```Java
// (Optional) Provide a Display mode (DIALOG, SNACKBAR, NOTIFICATION). Default: DIALOG.
.setDisplay(Display.DIALOG)
// (Optional) Provide a duration for the Snackbars (NORMAL, INDEFINITE). Default: NORMAL.
.setDuration(Duration.NORMAL)
// (Optional) Provide a source for the updates (GOOGLE_PLAY, GITHUB, AMAZON, FDROID). Default: GOOGLE_PLAY.
.setUpdateFrom(UpdateFrom.GOOGLE_PLAY)
// (Required for GITHUB, optional otherwise) Provide the GitHub user and repo where releases are available
.setGitHubUserAndRepo("javiersantos", "AppUpdater")
// (Optional) Updates will be displayed only every X times the app ascertains that a new update is available. Default: 5.
.showEvery(5)
// (Optional) Show dialog, snackbar or notification although there aren't updates. Default: false.
.showAppUpdated(false)
```

![ML Manager](https://raw.githubusercontent.com/javiersantos/AppUpdater/master/Screenshots/banner.png)

## License
	Copyright 2016 Javier Santos
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	   http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
