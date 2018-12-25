SUMMARY = "Beaglebone backlib"
SECTION = "libs"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"
PV = "1.0.0"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

SRCREV = "f722948929036d492819dcd2daed373e66c1d25e"
SRC_URI = "git://github.com/yigityuce/BlackLib.git \
	   file://makefile.patch;apply=yes"

SRC_URI[md5sum] = "2af12923677abaca9c8ec94ee476329d"
SRC_URI[sha256sum] = "a269419f34fd50e7657e9c50b5123034ad7c1fb1801d4afe254f70b2deb88cf8"

WD = "${WORKDIR}"
RD = "${THISDIR}/${PN}_${PV}" 

S="${WORKDIR}/git"

LIB_VERSION="v3_0"

do_compile() {
cd ./${LIB_VERSION}
make ARCH=arm-poky-linux-gnueabi- lib
}

do_install() {
cd ./${LIB_VERSION}
make PREFIX=${D}${prefix} install-lib
}

do_deploy() {
}