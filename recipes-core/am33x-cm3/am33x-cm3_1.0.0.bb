SUMMARY = "am33x PM firmware"
SECTION = "devel"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"
PV = "1.0.0"

COMPATIBLE_MACHINE="beaglebone"

FILES_${PN} += "/lib/firmware/*"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

BRANCH = "ti-v4.1.y"
GIT_URI = "git://git.ti.com/processor-firmware/ti-amx3-cm3-pm-firmware.git"
SRCREV = "fb484c5e54f2e31cf0a338d2927a06a2870bcc2c"

#BRANCH = "master"
#GIT_URI = "git://github.com/beagleboard/am33x-cm3.git"
#SRCREV = "f4163300590ae782cdbf9a764dc2456924114c91"

GIT_PROTOCOL = "https"


SRC_URI = "${GIT_URI};protocol=${GIT_PROTOCOL};branch=${BRANCH}"

S="${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d ${D}/lib/firmware
	install -m 0755 ${S}/bin/am335x-pm-firmware.elf ${D}/lib/firmware
}


