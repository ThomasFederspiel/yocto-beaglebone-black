SUMMARY = "U-Boot environment"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "linux-custom-rt"

PR = "r0"
PV = "4.9.0"

COMPATIBLE_MACHINE="beaglebone"

KERNEL_IMAGEDEST = "boot"

inherit linux-kernel-base
KERNEL_VERSION = "${@get_kernelversion_headers('${STAGING_KERNEL_BUILDDIR}')}"

FILES_${PN} += "/${KERNEL_IMAGEDEST}/uEnv.txt"

FILESDIR := "${THISDIR}/${PN}_${PV}"
FILESEXTRAPATHS_prepend := "${FILESDIR}:"

do_install() {
	install -d ${D}/${KERNEL_IMAGEDEST}/
	install -m 0644 ${FILESDIR}/uEnv.txt ${D}/${KERNEL_IMAGEDEST}/
	echo "uname_r=${KERNEL_VERSION}" >> ${D}/${KERNEL_IMAGEDEST}/uEnv.txt
}

