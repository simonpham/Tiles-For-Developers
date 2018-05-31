package com.github.simonpham.tiles4devs

import eu.chainfire.libsuperuser.Shell

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

fun kickSystemServices() {
    // this is called Settings app magic
    Shell.SU.run("service call activity $SYSPROPS_TRANSACTION")
}