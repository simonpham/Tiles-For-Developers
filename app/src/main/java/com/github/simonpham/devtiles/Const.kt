package com.github.simonpham.devtiles

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
const val GLOBAL_ANIMATOR_DURATION_SCALE = "animator_duration_scale"
const val GLOBAL_ADB_ENABLED = "adb_enabled"
const val GLOBAL_DEMO_MODE_ALLOWED = "sysui_demo_allowed"
const val GLOBAL_DEMO_MODE_ON = "sysui_tuner_demo_on"
const val SYSTEM_SHOW_TAPS = "show_touches"

const val PAGE_COUNT = 4
const val PAGE_WELCOME = 0
const val PAGE_REQUEST_PERMISSION = 1
const val PAGE_ADD_TILE_GUIDE = 2
const val PAGE_START_DEVELOPING = 3

const val KEY_SU_AVAILABILITY = "KEY.SU_AVAILABILITY"
const val KEY_MAGIC_AVAILABILITY = "KEY.MAGIC_AVAILABILITY"

const val PACKAGE_NAME = "com.github.simonpham.devtiles"
const val DEVETTER_PACKAGE_NAME = "com.github.simonpham.devetter"
const val GITHUB_REPO = "https://github.com/simonpham/Tiles-For-Developers"

const val CHANGELOG_URL = "file:///android_asset/changelog.html"

const val ACTION_PUT_GLOBAL = "com.github.simonpham.devetter.action.PUT_GLOBAL"
const val ACTION_PUT_SECURE = "com.github.simonpham.devetter.action.PUT_SECURE"
const val ACTION_PUT_SYSTEM = "com.github.simonpham.devetter.action.PUT_SYSTEM"

const val EXTRA_KEY = "com.github.simonpham.devetter.extra.KEY"
const val EXTRA_VALUE = "com.github.simonpham.devetter.extra.VALUE"
const val EXTRA_VALUE_TYPE = "com.github.simonpham.devetter.extra.VALUE_TYPE"

const val TYPE_GLOBAL = 0
const val TYPE_SECURE = 1
const val TYPE_SYSTEM = 2

const val DATA_TYPE_INT = 0
const val DATA_TYPE_LONG = 1
const val DATA_TYPE_FLOAT = 2
const val DATA_TYPE_STRING = 3
