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
Outputs `kotlinawslambda-1.0-SNAPSHOT-all.jar` to build/libs subdirectory
```bash
./gradlew clean build
```

### Maven
Outputs `kotlinawslambda-1.0-SNAPSHOT.jar` to target subdirectory
```bash
mvn clean package
```

## AWS CLI Commands

### Update function
Replace `<deployment package>` below with the output of the appropriate build
deployment package command above.
```bash
aws lambda update-function-code \
  --function-name KotlinAWSLambda \
  --zip-file fileb:<deployment package> \
  --profile default
```

### Execute function
```bash
aws lambda invoke \
   --invocation-type RequestResponse \
   --function-name KotlinAWSLambda \
   --log-type Tail \
   --payload '{"id":"1"}' \
   --profile default \
   outputfile.txt
```