SUMMARY = "U-boot overlays for beaglebone black"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "dtc-native"

PR = "r0"
PV = "1.0.0"

COMPATIBLE_MACHINE="beaglebone"

FILES_${PN} += "${base_libdir}/firmware/*.dtbo"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

BRANCH = "master"
GIT_URI = "git://github.com/beagleboard/bb.org-overlays.git"
GIT_PROTOCOL = "https"

SRCREV = "7cf50ad4d45f4d62057fbe0274c0a89e3e10e564"

SRC_URI = "${GIT_URI};protocol=${GIT_PROTOCOL};branch=${BRANCH}"

S="${WORKDIR}/git"

do_compile() {
make DTC=dtc all_arm
}

do_install() {
make DESTDIR=${D} install_arm
}


