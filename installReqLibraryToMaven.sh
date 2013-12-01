#!/bin/sh
## This script installs the required library (ttorrent 1.3 w/dep.)

mvn install:install-file -Dfile=ttorrent-1.3-SNAPSHOT-shaded.jar -DgroupId=com.turn -DartifactId=ttorrent -Dversion=1.3 -Dpackaging=jar