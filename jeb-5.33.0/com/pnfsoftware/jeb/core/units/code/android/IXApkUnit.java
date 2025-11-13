package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.IJsonUnit;
import com.pnfsoftware.jeb.core.units.IUnit;

public interface IXApkUnit extends IUnit {
   String getPackageName();

   IApkUnit getApk();

   IJsonUnit getManifest();
}
