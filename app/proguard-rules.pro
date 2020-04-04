# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#### My custom rules ####

##print configuration into file
-printconfiguration full-r8-config.txt

#https://github.com/google/gson/blob/master/examples/android-proguard-example/proguard.cfg
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Application classes that will be serialized/deserialized over Gson
-keep class com.lastfmlistmusic.data.remote.model.* { <fields>; }
-keep class com.lastfmlistmusic.data.remote.api.LastFmApiResponse { <fields>; }


#keep rules only for "android.enableR8.fullMode=true"
#https://github.com/square/retrofit/issues/3005
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
