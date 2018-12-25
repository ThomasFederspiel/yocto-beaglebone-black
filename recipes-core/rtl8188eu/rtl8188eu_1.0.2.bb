SUMMARY = "Wirless module rtl8188eu"
SECTION = "devel"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# make-mod-scripts will populate linux-build-artifacts with kernel includes
DEPENDS += "virtual/kernel make-mod-scripts"

inherit module

PR = "r0"
PV = "1.0.2"

#COMPATIBLE_MACHINE="beaglebone"
#FILES_${PN} =+ " ${base_libdir}/firmware/rtl8188eufw.bin"
#FILES_kernel-base += "${base_libdir}/firmware/rtl8188eufw.bin"

PACKAGES += "${PN}-rtl8188eu"
FILES_${PN}-rtl8188eu += " ${base_libdir}/firmware/rtl8188eufw.bin"

# If to be loaded automatically at kernel start 
#KERNEL_MODULE_AUTOLOAD += "rtl8188eu"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

SRCREV = "77471b4361aca8cb44ecd2ce27545dd526c6c118"
BRANCH = "v4.1.8_9499"
KERNEL_GIT_PROTOCOL = "git"

SRC_URI = "git://github.com/lwfinger/rtl8188eu.git;protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
	   file://0001-Makefile.patch;apply=yes"

WD = "${WORKDIR}"
RD = "${THISDIR}/${PN}_${PV}" 

S="${WORKDIR}/git"

KERNEL_SRC="${STAGING_KERNEL_DIR}"


do_configure[depends] += "virtual/kernel:do_shared_workdir"

#do_compile_append() {
#    make ARCH=${TARGET_ARCH} CROSS_COMPILE=${TARGET_PREFIX} KSRC=${KERNEL_SRC} KVER=${KERNEL_VERSION} all
#}

do_install() {
    make CROSS_COMPILE=${TARGET_PREFIX} strip
    make KVER=${KERNEL_VERSION} INSTALL_PREFIX=${D} install
}



