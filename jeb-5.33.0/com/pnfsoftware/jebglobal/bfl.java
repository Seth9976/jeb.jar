package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.ApkManifestHelper;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashSet;

public class bfl {
   com.pnfsoftware.jeb.corei.parsers.dex.vi pC;
   bfj A;
   boolean kS;

   public bfl(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, bfj var2) {
      this.pC = var1;
      this.A = var2;
   }

   public void pC(boolean var1) {
      this.kS = var1;
   }

   public void pC() {
      int var1 = this.pC.getClasses().size();
      int var2 = 0;

      for (bfu var4 : this.pC.getMethods()) {
         if (var4.isInternal()) {
            var2++;
         }
      }

      int var30 = 0;

      for (bft var5 : this.pC.getFields()) {
         if (var5.isInternal()) {
            var30++;
         }
      }

      String var32 = S.L("Dalvik Disassembly");
      String var33 = var1 <= 1 ? S.L("class") : S.L("classes");
      String var6 = var30 <= 1 ? S.L("field") : S.L("fields");
      String var7 = var2 <= 1 ? S.L("method") : S.L("methods");
      int var8 = this.pC.UO();
      String var9 = var8 == 0 ? "" : " " + Strings.ff(S.L("(including %d native)"), var8);
      String var10 = Strings.ff("[%s] %d %s: %d %s, %d %s%s", var32, var1, var33, var30, var6, var2, var7, var9);
      this.pC(var10, true);
      if (this.pC.getParent() instanceof IApkUnit) {
         com.pnfsoftware.jeb.corei.parsers.apk.Ws var11 = (com.pnfsoftware.jeb.corei.parsers.apk.Ws)this.pC.getParent();
         String var12 = var11.getPackageName();
         if (var12 == null) {
            this.pC("! " + S.L("No package name"), true);
         } else {
            var10 = Strings.ff(S.L("Package: %s"), var12);
            this.pC(var10, true);
         }

         if (!var11.hasApplication()) {
            this.pC("! " + S.L("No application name"), true);
         } else {
            String var13 = var11.getApplicationComponentFactory();
            if (!Strings.isBlank(var13)) {
               var10 = Strings.ff(S.L("Component Factory: %s"), var13);
               this.A.appendComment("# " + var10, this.kS);
               if (var13 != null) {
                  this.A.append(" (");
                  this.pC(var13);
                  this.A.append(")");
               }

               this.A.eol();
            }

            String var14 = var11.getApplicationName();
            var10 = Strings.ff(
               S.L("Application: %s%s"),
               Strings.safe(var14, var11.isMultiDex() ? "android.support.multidex.MultiDexApplication" : "android.app.Application"),
               var11.isDebuggable() ? " [" + S.L("debuggable") + "]" : ""
            );
            this.A.appendComment("# " + var10, this.kS);
            if (var14 != null) {
               this.A.append(" (");
               this.pC(var14);
               this.A.append(")");
            }

            this.A.eol();
            int var15 = var11.getActivities().size();
            int var16 = var11.getServices().size();
            int var17 = var11.getProviders().size();
            int var18 = var11.getReceivers().size();
            String var19 = S.L("Components");
            String var20 = var15 <= 1 ? S.L("activity") : S.L("activities");
            String var21 = var16 <= 1 ? S.L("service") : S.L("services");
            String var22 = var16 <= 1 ? S.L("provider") : S.L("providers");
            String var23 = var18 <= 1 ? S.L("receiver") : S.L("receivers");
            var10 = Strings.ff("%s: %d %s, %d %s, %d %s, %d %s", var19, var15, var20, var16, var21, var17, var22, var18, var23);
            this.pC(var10, true);

            ApkManifestHelper var24;
            try {
               var24 = new ApkManifestHelper(var11);
            } catch (Exception var29) {
               var24 = null;
            }

            if (var24 != null) {
               for (ApkManifestHelper.ActivityDescription var26 : var24.getActivityDescriptions()) {
                  if (var26.isEnabled()
                     && var26.isExported()
                     && var26.getIntentFilters().check("android.intent.action.MAIN", "android.intent.category.LAUNCHER")) {
                     String var27 = var26.getFullyQualifiedName();
                     String var28;
                     if (!var26.isAlias()) {
                        this.A.appendComment(Strings.ff("# %s: %s", S.L("Main Activity"), var27), this.kS);
                        var28 = var27;
                     } else {
                        this.A.appendComment(Strings.ff("# %s: %s -> %s: %s", S.L("Main Activity"), var27, S.L("alias for"), var26.getAliasForName()), this.kS);
                        var28 = var26.getAliasForName();
                     }

                     this.A.append(" (");
                     this.pC(var28);
                     this.A.append(")");
                     this.A.eol();
                  }
               }

               ApkManifestHelper.AndroidSystemType var43 = var24.getIntendedSystemType();
               if (var43 != null && var43 != ApkManifestHelper.AndroidSystemType.ANDROID) {
                  this.A.appendComment("# ! " + Strings.ff(S.L("App designed to run on %s"), var43));
                  this.A.eol();
               }
            }
         }

         HashSet var39 = new HashSet();

         for (String var41 : var11.getPermissions()) {
            com.pnfsoftware.jeb.corei.parsers.apk.zp var42 = com.pnfsoftware.jeb.corei.parsers.apk.RC.pC(var41);
            if (var42 != null && var42.A() == com.pnfsoftware.jeb.corei.parsers.apk.yt.A) {
               var39.add(var42.kS());
            }
         }

         if (!var39.isEmpty()) {
            var10 = Strings.ff("! " + S.L("Dangerous permissions: %s"), Strings.join(", ", var39));
            this.pC(var10, true);
         }
      }
   }

   private void pC(String var1, boolean var2) {
      this.A.appendComment("# " + var1, this.kS);
      if (var2) {
         this.A.eol();
      }
   }

   private void pC(String var1) {
      String var2 = "L" + var1.replace('.', '/') + ";";
      bfs var3 = this.pC.gp(var2);
      if (var3 != null) {
         this.A.UT(this.pC, var3.getClassTypeIndex());
      } else {
         this.A.append("?");
      }
   }
}
