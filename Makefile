run-dist:
	./app/build/install/app/bin/app

build:
	./app/gradlew clean
	./app/gradlew installDist
	./app/gradlew test
	./app/gradlew checkstyleMain

report:
	./app/gradlew jacocoTestReport

.PHONY: build
