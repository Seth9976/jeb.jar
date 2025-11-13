package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface IEmulatedAndroid {
   int getAndroidVersion();

   int getAndroidApiLevel();

   AndroidPlatformABI getAndroidPlatformABI();

   IDState getState();

   IApkUnit getApk();

   IDexUnit getDex();

   IDexDecompilerUnit getDecompiler();

   String getPackageName();

   String getMainActivity();

   String getAppFolder();

   String getApkPath();

   String getAppDataFolder();

   File getLocalApkFile();

   File getDropboxFolder();

   IDImm invokeMethod(DInvokeType var1, String var2, List var3) throws DexDecEvaluationException;

   Properties getJavaProperties();

   Map getSystemProperties();

   Object getInternalInfo();
}
