# linux-custom-rt_4.14.bb:
#
#   An example kernel recipe that uses the linux-yocto and oe-core
#   kernel classes to apply a subset of yocto kernel management to git
#   managed kernel repositories.
#
#   To use linux-yocto-custom in your layer, copy this recipe (optionally
#   rename it as well) and modify it appropriately for your machine. i.e.:
#
#     COMPATIBLE_MACHINE_yourmachine = "yourmachine"
#
#   You must also provide a Linux kernel configuration. The most direct
#   method is to copy your .config to files/defconfig in your layer,
#   in the same directory as the bbappend and add file://defconfig to
#   your SRC_URI.
#
#   To use the yocto kernel tooling to generate a BSP configuration
#   using modular configuration fragments, see the yocto-bsp and
#   yocto-kernel tools documentation.
#
# Warning:
#
#   Building this example without providing a defconfig or BSP
#   configuration will result in build or boot errors. This is not a
#   bug.
#
#
# Notes:
#
#   patches: patches can be merged into to the source git tree itself,
#            added via the SRC_URI, or controlled via a BSP
#            configuration.
#
#   defconfig: When a defconfig is provided, the linux-yocto configuration
#              uses the filename as a trigger to use a 'allnoconfig' baseline
#              before merging the defconfig into the build. 
#
#              If the defconfig file was created with make_savedefconfig, 
#              not all options are specified, and should be restored with their
#              defaults, not set to 'n'. To properly expand a defconfig like
#              this, specify: KCONFIG_MODE="--alldefconfig" in the kernel
#              recipe.
#   
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition (for kernel v3.4 only):
#            SRC_URI += "file://0001-linux-version-tweak.patch
#   example feature addition (for kernel v3.4 only):
#            SRC_URI += "file://feature.scc"
#

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

PACKAGES += " ${KERNEL_PACKAGE_NAME}-version-devicetree ${KERNEL_PACKAGE_NAME}-boot-uenv ${KERNEL_PACKAGE_NAME}-image-vmlinuz"

# Override SRC_URI in a bbappend file to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://github.com/beagleboard/linux.git;protocol=https;nocheckout=1;branch=4.14-rt;name=beaglebone \
           file://defconfig \
           file://spidev.patch;apply=yes"


LINUX_VERSION ?= "4.14"
LINUX_SUBLEVEL ?= ".79"
LINUX_VERSION_EXTENSION ?= "-custom-rt"
 			
# 
FILESDIR := "${THISDIR}/${PN}_${LINUX_VERSION}"

# Avoid error not packaging module files
#INSANE_SKIP_${PN} += "installed-vs-shipped"

#
FILES_${KERNEL_PACKAGE_NAME}-version-devicetree += "/${KERNEL_IMAGEDEST}/dtbs/${KERNEL_VERSION}/*"
FILES_${KERNEL_PACKAGE_NAME}-boot-uenv += "/${KERNEL_IMAGEDEST}/uEnv.txt"
FILES_${KERNEL_PACKAGE_NAME}-image-vmlinuz += "/${KERNEL_IMAGEDEST}/vmlinuz*"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${FILESDIR}:"

# Override SRCREV to point to a different commit in a bbappend file to
# build a different release of the Linux kernel.
# tag: v3.4 76e10d158efb6d4516018846f60c2ab5501900bc
SRCREV_beaglebone="43dc4909c72684edb3f260041cd458aa7c1efd5e"

PR = "r1"
PV = "${LINUX_VERSION}${LINUX_SUBLEVEL}"

# Override COMPATIBLE_MACHINE to include your machine in a bbappend
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE = "beaglebone"

kernel_do_install_append() {
	ln -sf zImage-${KERNEL_VERSION} ${D}/${KERNEL_IMAGEDEST}/vmlinuz-${KERNEL_VERSION}
	ln -sf vmlinuz-${KERNEL_VERSION} ${D}/${KERNEL_IMAGEDEST}/vmlinuz

	install -d ${D}/${KERNEL_IMAGEDEST}/dtbs/${KERNEL_VERSION}

	for dtb in ${KERNEL_DEVICETREE} ; do
	    ln -sf ../../${dtb} ${D}/${KERNEL_IMAGEDEST}/dtbs/${KERNEL_VERSION}/${dtb}
	done
}


