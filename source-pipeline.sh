#!/bin/sh
# Script a utiliser uniquement pour la pipeline.
# Ajoute ima au path.

get_abs_filename() {
  # $1 : relative filename
  if [ -d "$(dirname "$1")" ]; then
    echo "$(cd "$(dirname "$1")" && pwd)/$(basename "$1")"
  fi
}

echo $(get_abs_filename './ima')
echo $(get_abs_filename '')
PATH="$PATH:$(get_abs_filename './ima')"
chmod +x ./ima/ima-x86_64-Linux
PATH="$PATH:$(get_abs_filename '')"