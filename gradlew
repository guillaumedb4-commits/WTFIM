#!/bin/sh
set -eu
APP_HOME=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$JAR" ]; then
    echo "Gradle wrapper bootstrap JAR is missing: $JAR" >&2
    echo "Generate/copy the standard Gradle 9.2.1 wrapper JAR, then rerun ./gradlew." >&2
    exit 1
fi
exec java -classpath "$JAR" org.gradle.wrapper.GradleWrapperMain "$@"
