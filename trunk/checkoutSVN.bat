REM The first time, create a directory in your user direcory (use 'mkdir %HOMEPATH%\Products ') then run this to get the latest version (may overwrite your own changes)

cd %HOMEPATH%\Projects
svn checkout https://playground-kathleen.googlecode.com/svn/trunk/ playground-kathleen --username robert@watkins.net
