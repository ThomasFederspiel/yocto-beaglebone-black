SUMMARY = "Device Tree Rebuilder"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "dtc-native"

PR = "r0"
PV = "4.14.0"

DTS_KERNEL_DEVICETREE = "${KERNEL_DEVICETREE} am335x-boneblack-uboot.dtb"

COMPATIBLE_MACHINE="beaglebone"

inherit linux-kernel-base
KERNEL_VERSION = "${@get_kernelversion_headers('${STAGING_KERNEL_BUILDDIR}')}"

FILES_${PN} += "/boot/dtbs/${KERNEL_VERSION}/*.dtb /boot/*.dtb"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

BRANCH = "4.14.x"
GIT_URI = "git://github.com/RobertCNelson/dtb-rebuilder.git"
GIT_PROTOCOL = "https"

SRCREV = "abe8d4d09b315560ee4926a3a4a110f21920faa8"

SRC_URI = "${GIT_URI};protocol=${GIT_PROTOCOL};branch=${BRANCH} \
           file://0001-Makefile.patch;apply=yes"

S="${WORKDIR}/git"

do_compile() {	
	make DTC=dtc all_arm
}

do_install() {
	make DESTDIR=${D} KERNEL_VERSION=${KERNEL_VERSION} install_arm

	for dtb in ${DTS_KERNEL_DEVICETREE} ; do

	    dtb_file=${D}/boot/dtbs/${KERNEL_VERSION}/${dtb} 

	    if [ -e ${dtb_file} ]; then
	       ln -sf ./dtbs/${KERNEL_VERSION}/${dtb} ${D}/boot/${dtb}
	    fi
	done
}


