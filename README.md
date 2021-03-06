# Kotlin AWS Lambda Function
This repository serves as the demo app for my presentation,
"AWS Java Lambda Functions with Kotlin"

## Implementation Technologies
* **Kotlin** - https://www.google.com
* **Gradle** - https://gradle.org
* **Maven** - https://maven.apache.org 

## Directory Structure
* **/** - Root project configuration files
  * **src/main/kotlin** - Kotlin source
  * **src/test/kotlin** - Kotlin tests
  
## Build Deployment Package
### Gradle
Outputs `kotlinawslambda-dist-1.0-SNAPSHOT.zip` to build/distributions subdirectory
```bash
./gradlew clean build
```

### Maven
Outputs `kotlinawslambda-dist-1.0-SNAPSHOT.zip` to target/distributions subdirectory
```bash
mvn clean package
```

## AWS CLI Commands

### Upload/update function
Replace `<distribution zip>` below with the output of one of the build commands above.
```bash
aws lambda update-function-code \
  --function-name KotlinAWSLambda \
  --zip-file fileb://<distribution zip> \
  --profile default
```

### Execute function
```bash
aws lambda invoke \
  --invocation-type RequestResponse \
  --function-name KotlinAWSLambda \
  --log-type Tail \
  --payload '{"name":"New Task Request", "assignedTo": "me"}' \
  --profile default \
  outputfile.txt
```