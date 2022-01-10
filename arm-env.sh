#!/bin/sh

if [ $# -lt 1 ]; then
    usage
    echo "ERROR: no subcommand is provided"
    exit 1
fi

usage() {
    echo "Usage: ./arm-env.sh install | run <program>.s"
}

TOOLCHAIN_URL="https://developer.arm.com/-/media/Files/downloads/gnu-a/10.3-2021.07/binrel/gcc-arm-10.3-2021.07-x86_64-arm-none-linux-gnueabihf.tar.xz"

TOOLCHAIN_PATH="$PWD/gcc-arm-none-linux-gnueabihf.tar.xz"
XTOOLCHAIN_PATH="$PWD/arm-toolchain"

ARM_CPU_MODEL="cortex-a15"

install () {
    # Download arm toolchain
    if command -v wget &> /dev/null
    then
        wget $TOOLCHAIN_URL -O $TOOLCHAIN_PATH
    else
        echo "arm-env: wget not found, falling back to curl"
        curl -L $TOOLCHAIN_URL -o $TOOLCHAIN_PATH
    fi

    # Extract archive
    mkdir $XTOOLCHAIN_PATH
    tar xvf $TOOLCHAIN_PATH -C $XTOOLCHAIN_PATH --strip-components=1
    rm $TOOLCHAIN_PATH
}

SUBCOMMAND=$1

case "$SUBCOMMAND" in
    "install")
        install
        ;;
    "run")
        if [ $# -lt 2 ]; then
            usage
            echo "ERROR: no assembly file is provided"
            exit 1
        fi
        ARGUMENT=$2
        AS="${XTOOLCHAIN_PATH}/bin/arm-none-linux-gnueabihf-as"
        LD="${XTOOLCHAIN_PATH}/bin/arm-none-linux-gnueabihf-ld"
        $AS "${ARGUMENT}" -o "${ARGUMENT%.*}.o"
        $LD "${ARGUMENT%.*}.o" -o "${ARGUMENT%.*}"
        qemu-arm -cpu $ARM_CPU_MODEL "${ARGUMENT%.*}"
        ;;
    *)
        set +x
        usage
        echo "ERROR: unknown subcommand $SUBCOMMAND"
        exit 1
        ;;
esac
