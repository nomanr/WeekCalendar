
<br/><br/><img src="https://raw.githubusercontent.com/nomanr/WeekCalendar/master/images/cover.png">
<p><b>WeekCalendar</b> is a library which provides a weekly calendar. </p>
The sample project includes the usage of the library.
Support for Android 4.0 and up.

Feel free to fork or issue pull requests on github. Issues can be reported on the github issue tracker.

<a href="https://play.google.com/store/apps/details?id=noman.weekcalendar" target="_blank"><img src="https://raw.githubusercontent.com/nomanr/WeekCalendar/master/images/google_play.png" width="250" target="_blank"/></a>

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-WeekCalendar-green.svg?style=true)](https://android-arsenal.com/details/1/2905)


<h3>Demo</h3>

<img src="https://raw.githubusercontent.com/nomanr/WeekCalendar/master/images/gif.gif"width="400">


<h3>Setup</h3>


----------


<h5>Gradle</h5>

    dependencies {
       compile 'noman.weekcalendar:weekcalendar:1.0.6'
    }

 <h5>Maven</h5>

    <dependency>
      <groupId>noman.weekcalendar</groupId>
      <artifactId>weekcalendar</artifactId>
      <version>1.0.6</version>
    </dependency>

<h3>Sample Usage</h3>


----------

     <noman.weekcalendar.WeekCalendar
        android:id="@+id/weekCalendar"
        android:layout_width="match_parent"
        android:layout_height="65dp"
        android:background="@color/colorPrimary"/>
<h4>Theme the calendar</h4>
There are a few xml attributes to customise the calendar. If you feel that any customization option is missing, let me know.


----------

 - `numOfPages` 
 - `daysTextSize`
 - `daysTextColor`
 - `daysBackgroundColor`
 - `weekTextSize`
 - `weekTextColor`
 - `weekBackgroundColor`
 - `selectedBgColor`
 - `todaysDateBgColor`
 - `dayNameLength`
 - `hideNames`

----------

<h5>Example</h5>

     <noman.weekcalendar.WeekCalendar
       android:id="@+id/weekCalendar"
       android:layout_width="match_parent"
       android:layout_height="65dp"
       android:background="@color/colorPrimary"
       app:numOfPages="150"
       app:dayNameLength="threeLetters"
       app:todaysDateBgColor="#ffffff"/>

<h5>Explained</h5>

 - `numOfPages`  by default, calendar has 100 pages. You can scroll 49 to left and 49 to right. Using this attribute you can set number of pages. You can send it to 1000, it depends on requirements. 
 - `daysTextSize` day means day of the month. By default text size is `17sp`.
 - `daysTextColor` by default the day text color is set to be white.
 - `daysBackgroundColor` if you have `colorPrimary` attribute in `color.xml`, then the backgroud color will be that one. Otherwise the purple color shown in the demo.
 - `weekTextSize` week means day of the week,i.e (S,M,T ..). By default text size is `17sp`.
 - `weekTextColor` by default the week day text color is set to be white.
 - `weekBackgroundColor`  same as `daysBackgroundColor`
 - `selectedBgColor` By default, its color is set to be `colorAccent`, if you've that attribute in attribute in `color.xml`, then the backgroud color will be that one. Otherwise the pink color shown in the demo.
 - `todaysDateBgColor` todays date background color, same as `selectedBgColor`.
 - `dayNameLength` week day name length, `singleLetter` means (S,M,T..) and `threeLetters` means (Sun, Mun, Tue..)
 - `hideNames` , set this attribute to hide name of week days.


----------
<h3>Impelement Listener </h3>
`OnDateClickListener` returns `DateTime` object. `DateTime` is class available in <a href="http://www.joda.org/joda-time/" target="_blank">Joda Time</a>. I will recommend using this library if you are playing with date and time.

    weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(DateTime dateTime) {
                Toast.makeText(MainActivity.this, 
                "You Selected " + dateTime.toString(), Toast.LENGTH_SHORT).show();
            }

        });
  See the sample project for usage of methods like 
  - `reset()` 
  - `moveToNext()` 
  - `moveToPrevious()`
  - `setSelectedDate(DateTime)`
  - `setStartDate(DateTime)`


<h3>Libraries Used</h3>


----------
 - <a href="http://www.joda.org/joda-time/" target="_blank">Joda Time</a>
 - <a href="https://github.com/square/otto" target="_blank">Otto</a>

<h3>License</h3>


----------

    Copyright (c) 2015 Noman Rafique

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    

