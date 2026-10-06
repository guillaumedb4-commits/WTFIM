@echo off
setlocal
set APP_HOME=%~dp0
set WRAPPER_JAR=%APP_HOME%gradle\wrapper\gradle-wrapper.jar
if not exist "%WRAPPER_JAR%" (
  echo Gradle wrapper bootstrap JAR is missing: %WRAPPER_JAR% 1>&2
  echo Generate/copy the standard Gradle 9.2.1 wrapper JAR, then rerun gradlew.bat. 1>&2
  exit /b 1
)
java -classpath "%WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*
exit /b %ERRORLEVEL%
