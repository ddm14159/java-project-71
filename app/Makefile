.PHONY: build

run-dist:
	./build/install/app/bin/app

build:
	./gradlew clean
	./gradlew installDist
	./gradlew test
	./gradlew checkstyleMain

report:
	./gradlew jacocoTestReport