package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IXmlUnit;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import java.util.List;

public interface IApkUnit extends IUnit {
   String getPackageName();

   List getPermissions();

   boolean hasApplication();

   String getApplicationName();

   boolean isDebuggable();

   List getActivities();

   String getMainActivity();

   List getServices();

   List getReceivers();

   List getProviders();

   boolean isMultiDex();

   IDexUnit getDex();

   IXmlUnit getManifest();

   IUnit getLibraries();

   List getLibrariesForArch(AndroidPlatformABI var1);

   IUnit getAssets();

   IUnit getResources();

   int getSignatureSchemeVersionFlags();

   APKSigSchemeV2Block getSignatureSchemeV2Block();

   APKSigSchemeV3Block getSignatureSchemeV3Block();

   IDexDynamic dynamic();

   IEmulatedAndroid createEmulatedAndroid();

   IGenericUnpacker createGenericUnpacker();
}
