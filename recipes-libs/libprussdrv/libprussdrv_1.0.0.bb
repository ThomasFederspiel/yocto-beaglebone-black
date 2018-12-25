SUMMARY = "Am335c pru package"
SECTION = "libs"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"
PV = "1.0.0"

#PACKAGES = "${PN}"
#CLEANBROKEN = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

# As to disable GNU hash error
INSANE_SKIP_${PN} := "ldflags"

SRCREV = "7d473fa0e9ff8c63c798d290fed83e68fd59f417"
SRC_URI = "git://github.com/beagleboard/am335x_pru_package.git;protocol=https \
	   file://app_loader_makefile.patch;apply=yes \
	   file://so_link.patch;apply=yes \
	   file://prussdrv.h.patch;apply=yes \
	   file://0001-Better-fault-handling.patch;apply=yes"

#	   file://prussdrv.c.patch;apply=yes"

S="${WORKDIR}/git"

#do_configure() {
#}

do_compile() {
cd ./pru_sw/app_loader/interface 
make CROSS_COMPILE=arm-poky-linux-gnueabi- all
}

do_install() {
cd ./pru_sw/app_loader/interface 
make DESTDIR=${D} PREFIX=${prefix} install
}

#do_deploy() {
#)
