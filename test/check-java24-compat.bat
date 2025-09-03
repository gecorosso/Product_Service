@echo off
setlocal enabledelayedexpansion

echo ========================================
echo  [1] Controllo aggiornamenti dipendenze
echo ========================================
mvn versions:display-dependency-updates
echo.

echo ========================================
echo  [2] Compilazione del progetto
echo ========================================
mvn clean package -DskipTests
echo.

echo ========================================
echo  [3] Scansione API deprecate con jdeprscan
echo ========================================
for /f "delims=" %%i in ('dir /b /o-d target\*.jar') do (
    set JAR_FILE=target\%%i
    goto :found
)

:found
echo Analizzo: %JAR_FILE%
jdeprscan --release 24 --class-path "%JAR_FILE%"

echo.
echo ✅ Analisi completata.
endlocal
pause
