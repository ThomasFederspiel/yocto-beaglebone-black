SUMMARY = "Install uEnv.txt to rootfs"
SECTION = "devel"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

#RPROVIDES_${PN} += "rootfs-files-uenv" 
PR = "r0"
PV = "1.0.0"
 
UENV_FILE = "uEnv.txt"

PACKAGES += "${PN}-rootfs-files-uenv"
FILES_${PN} += "/boot/${UENV_FILE}"

WD = "${WORKDIR}"
DD = "${DEPLOYDIR}"
RD = "${THISDIR}/${PN}_${PV}" 

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
install -d ${D}/boot
install -m 0644 ${RD}/${UENV_FILE} ${D}/boot
}

