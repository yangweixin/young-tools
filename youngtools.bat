@echo off

set str=
set allparam=
set /p str=command:
if "%str%" == "" (
	goto end
)

set allparam=%str%
set cnt = 0
:param
set str=
set cnt = cnt + 1
set /p str=param%cnt%:
if "%str%"=="" (
	goto end
)
set allparam=%allparam% %str%
goto param

:end
java -Dfile.encoding=utf-8 -jar -Xms4000m -Xmx4000m -Xmn2000m youngtools.jar %allparam%

:eof
@pause