call runcrud.bat
if %ERRORLEVEL% == 0 goto runchrome
echo.
echo runcrud has encountered errors - breaking work
goto fail

:runchrome
start chrome http://localhost:8080/crud/v1/task/getTasks
if %ERRORLEVEL% == 0 goto showtasks
echo.
echo failed starting Chrome - breaking work
goto fail

:showtasks
start http://localhost:8080/crud/v1/task/getTasksheroku login
if %ERRORLEVEL% == 0 goto end
echo.
echo failed loading tasks - breaking work
goto fail

:fail
echo.
echo Errors were found.

:end
echo Ending work