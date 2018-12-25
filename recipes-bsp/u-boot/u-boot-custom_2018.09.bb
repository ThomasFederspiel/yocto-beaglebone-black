DEPENDS += "bison-native"

# must be done before include below as it changes ${PV}
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

require u-boot-custom.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

PR = "r01"

BRANCH = "master"
UBOOT_GIT_URI = "git://github.com/u-boot/u-boot.git"
UBOOT_GIT_PROTOCOL = "https"

SRCREV = "f88b6facb87dc4a52f1630eb9d858c0f54c6628e"

#SRC_URI += "file://0001-am335x_evm-uEnv.txt-bootz-n-fixes.patch;apply=yes"

SRC_URI += "file://0001-am335x_evm-uEnv.txt-bootz-n-fixes.patch;apply=yes \
            file://0002-U-Boot-BeagleBone-Cape-Manager.patch;apply=yes"
