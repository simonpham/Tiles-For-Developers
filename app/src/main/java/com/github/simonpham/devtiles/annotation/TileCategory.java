package com.github.simonpham.devtiles.annotation;

import androidx.annotation.IntDef;

import static com.github.simonpham.devtiles.annotation.TileCategory.MAGIC;
import static com.github.simonpham.devtiles.annotation.TileCategory.ROOT;
import static com.github.simonpham.devtiles.annotation.TileCategory.ROOTLESS;
import static com.github.simonpham.devtiles.annotation.TileCategory.SYSPROP;

/**
 * Created by Simon Pham on 6/11/18.
 * Email: simonpham.dn@gmail.com
 */

@IntDef({ROOTLESS, SYSPROP, ROOT, MAGIC})
public @interface TileCategory {
    int ROOTLESS = 0;
    int SYSPROP = 1;
    int ROOT = 2;
    int MAGIC = 3;
}