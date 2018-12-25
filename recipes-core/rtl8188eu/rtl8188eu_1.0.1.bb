SUMMARY = "Wirless module rtl8188eu"
SECTION = "devel"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS="virtual/kernel"

inherit module

PR = "r0"
PV = "1.0.1"

#COMPATIBLE_MACHINE="beaglebone"
#FILES_${PN} =+ " ${base_libdir}/firmware/rtl8188eufw.bin"
#FILES_kernel-base += "${base_libdir}/firmware/rtl8188eufw.bin"

PACKAGES += "${PN}-rtl8188eu"
FILES_${PN}-rtl8188eu += " ${base_libdir}/firmware/rtl8188eufw.bin"

# If to be loaded automatically at kernel start 
#KERNEL_MODULE_AUTOLOAD+="rtl8188eu"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

SRCREV = "583fdd269bb796e83cf8c923d371da31f505ee2b"

SRC_URI = "git://github.com/lwfinger/rtl8188eu.git \
	   file://Makefile.patch;apply=yes"

WD = "${WORKDIR}"
RD = "${THISDIR}/${PN}_${PV}" 

S="${WORKDIR}/git"

KERNEL_SRC="${STAGING_KERNEL_DIR}"

do_compile() {
    make ARCH=${TARGET_ARCH} CROSS_COMPILE=${TARGET_PREFIX} KSRC=${KERNEL_SRC} KVER=${KERNEL_VERSION} all
}

do_install() {
    make CROSS_COMPILE=${TARGET_PREFIX} strip
    make KVER=${KERNEL_VERSION} INSTALL_PREFIX=${D} install
}

do_deploy() {
)

