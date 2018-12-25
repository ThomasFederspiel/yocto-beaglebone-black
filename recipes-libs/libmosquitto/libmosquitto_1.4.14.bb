SUMMARY = "MQTT C/C++ mosquitto library"
SECTION = "libs"
DEPENDS = ""
LICENSE = "EDL-1.0 | EPL-1.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"
PV = "1.4.14"

#PACKAGES = "${PN}-libmosquitto"
#CLEANBROKEN = "1"

#FILES_${PN} += "${prefix}/local/*"


FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

SRCREV = "d72ec39d79effae08011e13faf5870fa7e80fa54" 
SRC_URI = "git://github.com/eclipse/mosquitto.git;protocol=https \
	   file://config.mk.patch;apply=yes \
	   file://lib/Makefile-lib.patch;apply=yes \
	   file://lib/cpp/Makefile-cpp.patch;apply=yes"


S="${WORKDIR}/git"

#do_configure() {
#}

do_compile() {
cd ./lib 
#make CROSS_COMPILE=arm-poky-linux-gnueabi- CC=gcc all
make all
}

do_install() {
cd ./lib 
make DESTDIR=${D} install
}

#do_deploy() {
#)
