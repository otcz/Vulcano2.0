@echo off
rem
rem Runs sqlite3 with the SpatiaLite extension loaded.
rem Accepts an optional argument specifying a database file to load.

./sqlite3.exe -init sqlite.config %1
