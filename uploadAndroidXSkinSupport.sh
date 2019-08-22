#!/usr/bin/env bash
./gradlew \
androidx:skin-support:bintrayUpload --stacktrace \
androidx:skin-support-appcompat:bintrayUpload --stacktrace \
androidx:skin-support-design:bintrayUpload --stacktrace \
androidx:skin-support-cardview:bintrayUpload --stacktrace \
androidx:skin-support-constraint-layout:bintrayUpload --stacktrace
