@REM ----------------------------------------------------------------------------
@REM Maven Wrapper startup batch script
@REM ----------------------------------------------------------------------------
@IF "%__MVNW_ARG0_NAME__%"=="" (SET "BASE_DIR=%~dp0") ELSE (SET "BASE_DIR=%__MVNW_ARG0_NAME__%")
@SET MAVEN_PROJECTBASEDIR=%BASE_DIR%
@SET MAVEN_HOME=%USERPROFILE%\.m2\wrapper\dists\apache-maven-3.9.6-bin\apache-maven-3.9.6
@SET MVNW_REPOURL=https://repo.maven.apache.org/maven2

IF NOT EXIST "%MAVEN_HOME%\bin\mvn.cmd" (
    echo Downloading Maven 3.9.6...
    powershell -Command "& { [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $dest='%USERPROFILE%\.m2\wrapper\dists\apache-maven-3.9.6-bin'; New-Item -ItemType Directory -Force $dest | Out-Null; Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip' -OutFile '$dest\maven.zip'; Expand-Archive -Path '$dest\maven.zip' -DestinationPath '$dest' -Force; Remove-Item '$dest\maven.zip' }"
)

"%MAVEN_HOME%\bin\mvn.cmd" %*
