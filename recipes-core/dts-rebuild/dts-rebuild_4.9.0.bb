SUMMARY = "Device Tree Rebuilder"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "dtc-native linux-custom-rt"

PR = "r0"
PV = "4.9.0"

DTS_KERNEL_DEVICETREE = "${KERNEL_DEVICETREE} am335x-boneblack-emmc-overlay.dtb am335x-boneblack-hdmi-overlay.dtb \
			 am335x-boneblack-nhdmi-overlay.dtb am335x-boneblack-overlay.dtb am335x-boneblack-wireless-emmc-overlay.dtb"

COMPATIBLE_MACHINE="beaglebone"

inherit linux-kernel-base
KERNEL_VERSION = "${@get_kernelversion_headers('${STAGING_KERNEL_BUILDDIR}')}"

FILES_${PN} += "/boot/dtbs/${KERNEL_VERSION}/*.dtb /boot/*.dtb"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

BRANCH = "4.9.x"
GIT_URI = "git://github.com/RobertCNelson/dtb-rebuilder.git"
GIT_PROTOCOL = "https"

SRCREV = "38279518471b8a2ef5b5fc080fce6fcc98cb6b64"

SRC_URI = "${GIT_URI};protocol=${GIT_PROTOCOL};branch=${BRANCH} \
           file://0001-Makefile.patch;apply=yes"

S="${WORKDIR}/git"

do_compile() {	
	make DTC=dtc all_arm
}

do_install() {
	make DESTDIR=${D} KERNEL_VERSION=${KERNEL_VERSION} DTC=dtc install_arm

	for dtb in ${DTS_KERNEL_DEVICETREE} ; do

	    dtb_file=${D}/boot/dtbs/${KERNEL_VERSION}/${dtb} 

	    if [ -e ${dtb_file} ]; then
	       ln -sf ./dtbs/${KERNEL_VERSION}/${dtb} ${D}/boot/${dtb}
	    fi
	done
}


