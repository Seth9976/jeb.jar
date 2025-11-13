package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AbstractEnginesPlugin;
import com.pnfsoftware.jeb.core.EnginesContextUtil;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.ListOptionDefinition;
import com.pnfsoftware.jeb.core.OptionDefinition;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.INativeLibrary;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ckl extends AbstractEnginesPlugin {
   private static final ILogger pC = GlobalLog.getLogger(ckl.class);
   private static final PluginInformation A = new PluginInformation(
      S.L("Recover RegisterNatives methods"),
      S.L("Attempt to retrieve JNI routines that were statically-registered using RegisterNatives (aarch64 only)."),
      "Nicolas Falliere",
      Version.create(0, 2),
      Version.create(4, 31)
   );
   private IEnginesContext kS;
   private IEventListener wS;

   @Override
   public IPluginInformation getPluginInformation() {
      return A;
   }

   @Override
   public List getExecutionOptionDefinitions(IEnginesContext var1) {
      if (!var1.hasProjects()) {
         return super.getExecutionOptionDefinitions(var1);
      } else {
         IRuntimeProject var2 = var1.getMainProject();
         List var3 = var2.findUnits(IApkUnit.class);
         ArrayList var4 = new ArrayList();

         for (IApkUnit var6 : var3) {
            var4.add(UnitUtil.buildFullyQualifiedUnitPath(var6));
         }

         return Arrays.asList(
            new ListOptionDefinition("apkPath", (String)Lists.getFirst(var4), S.L("Target APK:"), (String[])var4.toArray(new String[var4.size()])),
            new OptionDefinition("libnameFilter", "*", S.L("Library name filter:"))
         );
      }
   }

   private boolean pC() {
      return true;
   }

   @Override
   public void load(IEnginesContext var1) {
      this.kS = var1;
      this.wS = new ckm(this);
      var1.addListener(this.wS);
   }

   @Override
   public void dispose() {
      if (this.wS != null) {
         this.kS.removeListener(this.wS);
         this.wS = null;
      }
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      if (!this.pC()) {
         throw new RuntimeException("This plugin cannot be instantiated with this license type!");
      } else {
         IRuntimeProject var3 = EnginesContextUtil.getMainProject(var1);
         if (var3 == null) {
            throw new RuntimeException("No opened project! Create or open a project and load an APK.");
         } else {
            String var4 = (String)var2.get("apkPath");
            String var5 = (String)var2.get("libnameFilter");
            IApkUnit var6 = null;

            for (IApkUnit var8 : var3.findUnits(IApkUnit.class)) {
               if (UnitUtil.buildFullyQualifiedUnitPath(var8).equals(var4)) {
                  var6 = var8;
                  break;
               }
            }

            if (var6 == null) {
               throw new RuntimeException("No APK unit found!");
            } else {
               IEmulatedAndroid var24 = var6.createEmulatedAndroid();
               if (var24 != null) {
                  IDState var25 = var24.getState();
                  List var9 = var6.getLibrariesForArch(AndroidPlatformABI.ARM64);
                  HashSet var10 = new HashSet();
                  Iterator var11 = var9.iterator();

                  while (true) {
                     IELFUnit var12;
                     INativeLibrary var14;
                     while (true) {
                        if (!var11.hasNext()) {
                           return;
                        }

                        var12 = (IELFUnit)var11.next();
                        String var13 = var12.getName();
                        if (var5.length() <= 0 || var5.equals("*") || Strings.starMatches(var13, var5)) {
                           pC.info(S.L("Processing library: %s"), UnitUtil.buildFullyQualifiedUnitPath(var12));

                           try {
                              var14 = var25.loadNativeLibrary(var12);
                              break;
                           } catch (Exception var23) {
                              pC.catching(var23);
                           }
                        }
                     }

                     long var15 = var14.getMappingAddress();
                     Map var17 = var25.getRegisteredNatives();
                     int var18 = 0;

                     for (String var20 : var17.keySet()) {
                        if (var10.add(var20)) {
                           long var21 = (Long)var17.get(var20);
                           pC.info(S.L("Registered by %s: method= %s @ RVA: 0x%X"), var12.getName(), var20, var21 - var15);
                           var18++;
                        }
                     }

                     if (var18 == 0) {
                        pC.info(S.L("Nothing found"));
                     }
                  }
               }
            }
         }
      }
   }
}
