SUMMARY = "ltrace intercepts and records dynamic library calls"

DESCRIPTION = "ltrace intercepts and records dynamic library calls \
which are called by an executed process and the signals received by that process. \
It can also intercept and print the system calls executed by the program.\
"
HOMEPAGE = "http://ltrace.org/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

PE = "1"
PV = "7.91+git${SRCPV}"
SRCREV = "c22d359433b333937ee3d803450dc41998115685"

DEPENDS = "elfutils"
RDEPENDS_${PN} = "elfutils"
SRC_URI = "git://anonscm.debian.org/collab-maint/ltrace.git;branch=master \
           file://configure-allow-to-disable-selinux-support.patch \
           file://0001-replace-readdir_r-with-readdir.patch \
           file://0001-Use-correct-enum-type.patch \
           file://0002-Fix-const-qualifier-error.patch \
          "
S = "${WORKDIR}/git"

inherit autotools

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'selinux', d)}"
PACKAGECONFIG[unwind] = "--with-libunwind,--without-libunwind,libunwind"
PACKAGECONFIG[selinux] = "--enable-selinux,--disable-selinux,libselinux,libselinux"

do_configure_prepend () {
    ( cd ${S}; ./autogen.sh )
}
