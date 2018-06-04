package com.github.simonpham.tiles4devs

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

// rootless settings
const val SYSPROP_ADB_PORT = "service.adb.tcp.port"
const val SYSPROP_DEBUG_FORCE_RTL = "debug.force_rtl"
const val SYSPROP_DEBUG_GPU_OVERDRAW = "debug.hwui.overdraw"
const val SYSPROP_DEBUG_GPU_PROFILE = "debug.hwui.profile"
const val SYSPROP_DEBUG_LAYOUT = "debug.layout"
const val SYSPROP_STRICT_MODE_DISABLE = "persist.sys.strictmode.disable"
const val SYSPROP_STRICT_MODE_VISUAL = "persist.sys.strictmode.visual"

// settings require write secure settings permission
const val SYS_ANIMATOR_DURATION_SCALE = "animator_duration_scale"
const val SYS_DEMO_MODE_ALLOWED = "sysui_demo_allowed"
const val SYS_DEMO_MODE_ON = "sysui_tuner_demo_on"
const val SYS_SHOW_TAPS = "show_touches"

const val PAGE_COUNT = 4
const val PAGE_WELCOME = 0
const val PAGE_REQUEST_PERMISSION = 1
const val PAGE_ADD_TILE_GUIDE = 2
const val PAGE_START_DEVELOPING = 3

const val KEY_SU_AVAILABILITY = "KEY.SU_AVAILABILITY"
const val KEY_MAGIC_AVAILABILITY = "KEY.MAGIC_AVAILABILITY"

const val PACKAGE_NAME = "com.github.simonpham.tiles4devs"
const val GITHUB_REPO = "https://github.com/simonpham/Tiles-For-Developers"
