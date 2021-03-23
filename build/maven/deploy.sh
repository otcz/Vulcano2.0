#!/bin/bash

# readlink isn't available on Mac OS, so use this POSIX-compliant alternative
absolute_path() {
  cd "$(dirname "$1")"
  echo "$PWD/$(basename "$1")"
}

export ANT_HOME="$(absolute_path "$(dirname "$0")/../ant")"

"$ANT_HOME/bin/ant" -nouserlib -f deploy.xml "$@"
