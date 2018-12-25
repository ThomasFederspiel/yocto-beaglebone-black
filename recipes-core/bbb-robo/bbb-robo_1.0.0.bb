SUMMARY = "Robo framework"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "dtc-native linux-custom-rt"

PR = "r0"
PV = "1.0.0"

COMPATIBLE_MACHINE="beaglebone"

FILES_${PN} += "${base_libdir}/firmware/*.dtbo"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

SRC_URI = "file://source/*"

S="${WORKDIR}/source"

do_compile() {
      make DTC=dtc all
}

do_install() {
      make DESTDIR=${D} install
}


