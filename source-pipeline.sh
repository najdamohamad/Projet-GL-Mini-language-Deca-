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
<<<<<<< HEAD
# QEMU related, otherwhise doesn't run in vm, wtf?
=======

# QEMU related, otherwhise doesn't run, wtf?
# "bug": https://gitlab.com/qemu-project/qemu/-/issues/447
>>>>>>> fa2ec88 (ci)
# https://docs.windriver.com/fr-FR/bundle/Wind_River_Linux_Release_Notes_LTS_21_tki1589820771450/page/yiz1629749231871.html
echo "vm.mmap_min_addr = 65536" > /etc/sysctl.d/mmap_min_addr.conf