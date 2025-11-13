package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.ApkManifestHelper;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashSet;

public class bjg {
   com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   bje RF;
   boolean xK;

   public bjg(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, bje var2) {
      this.q = var1;
      this.RF = var2;
   }

   public void q(boolean var1) {
      this.xK = var1;
   }

   public void q() {
      int var1 = this.q.getClasses().size();
      int var2 = 0;

      for (bjp var4 : this.q.getMethods()) {
         if (var4.isInternal()) {
            var2++;
         }
      }

      int var29 = 0;

      for (bjo var5 : this.q.getFields()) {
         if (var5.isInternal()) {
            var29++;
         }
      }

      String var31 = S.L("Dalvik Disassembly");
      String var32 = var1 <= 1 ? S.L("class") : S.L("classes");
      String var6 = var29 <= 1 ? S.L("field") : S.L("fields");
      String var7 = var2 <= 1 ? S.L("method") : S.L("methods");
      int var8 = this.q.If();
      String var9 = var8 == 0 ? "" : " " + Strings.ff(S.L("(including %d native)"), var8);
      String var10 = Strings.ff("[%s] %d %s: %d %s, %d %s%s", var31, var1, var32, var29, var6, var2, var7, var9);
      this.q(var10, true);
      if (this.q.getParent() instanceof IApkUnit) {
         com.pnfsoftware.jeb.corei.parsers.apk.ej var11 = (com.pnfsoftware.jeb.corei.parsers.apk.ej)this.q.getParent();
         String var12 = var11.getPackageName();
         if (var12 == null) {
            this.q("! " + S.L("No package name"), true);
         } else {
            var10 = Strings.ff(S.L("Package: %s"), var12);
            this.q(var10, true);
         }

         if (!var11.hasApplication()) {
            this.q("! " + S.L("No application name"), true);
         } else {
            String var13 = var11.getApplicationName();
            var10 = Strings.ff(
               S.L("Application: %s%s"),
               Strings.safe(var13, var11.isMultiDex() ? "android.support.multidex.MultiDexApplication" : "android.app.Application"),
               var11.isDebuggable() ? " [" + S.L("debuggable") + "]" : ""
            );
            this.RF.appendComment("# " + var10, this.xK);
            if (var13 != null) {
               this.RF.append(" (");
               this.q(var13);
               this.RF.append(")");
            }

            this.RF.eol();
            int var14 = var11.getActivities().size();
            int var15 = var11.getServices().size();
            int var16 = var11.getProviders().size();
            int var17 = var11.getReceivers().size();
            String var18 = S.L("Components");
            String var19 = var14 <= 1 ? S.L("activity") : S.L("activities");
            String var20 = var15 <= 1 ? S.L("service") : S.L("services");
            String var21 = var15 <= 1 ? S.L("provider") : S.L("providers");
            String var22 = var17 <= 1 ? S.L("receiver") : S.L("receivers");
            var10 = Strings.ff("%s: %d %s, %d %s, %d %s, %d %s", var18, var14, var19, var15, var20, var16, var21, var17, var22);
            this.q(var10, true);

            ApkManifestHelper var23;
            try {
               var23 = new ApkManifestHelper(var11);
            } catch (Exception var28) {
               var23 = null;
            }

            if (var23 != null) {
               for (ApkManifestHelper.ActivityDescription var25 : var23.getActivityDescriptions()) {
                  if (var25.isEnabled()
                     && var25.isExported()
                     && var25.getIntentFilters().check("android.intent.action.MAIN", "android.intent.category.LAUNCHER")) {
                     String var26 = var25.getFullyQualifiedName();
                     String var27;
                     if (!var25.isAlias()) {
                        this.RF.appendComment(Strings.ff("# %s: %s", S.L("Main Activity"), var26), this.xK);
                        var27 = var26;
                     } else {
                        this.RF
                           .appendComment(Strings.ff("# %s: %s -> %s: %s", S.L("Main Activity"), var26, S.L("alias for"), var25.getAliasForName()), this.xK);
                        var27 = var25.getAliasForName();
                     }

                     this.RF.append(" (");
                     this.q(var27);
                     this.RF.append(")");
                     this.RF.eol();
                  }
               }

               ApkManifestHelper.AndroidSystemType var41 = var23.getIntendedSystemType();
               if (var41 != null && var41 != ApkManifestHelper.AndroidSystemType.ANDROID) {
                  this.RF.appendComment("# ! " + Strings.ff(S.L("App designed to run on %s"), var41));
                  this.RF.eol();
               }
            }
         }

         HashSet var37 = new HashSet();

         for (String var39 : var11.getPermissions()) {
            com.pnfsoftware.jeb.corei.parsers.apk.qV var40 = com.pnfsoftware.jeb.corei.parsers.apk.vb.q(var39);
            if (var40 != null && var40.RF() == com.pnfsoftware.jeb.corei.parsers.apk.PY.RF) {
               var37.add(var40.xK());
            }
         }

         if (!var37.isEmpty()) {
            var10 = Strings.ff("! " + S.L("Dangerous permissions: %s"), Strings.join(", ", var37));
            this.q(var10, true);
         }
      }
   }

   private void q(String var1, boolean var2) {
      this.RF.appendComment("# " + var1, this.xK);
      if (var2) {
         this.RF.eol();
      }
   }

   private void q(String var1) {
      String var2 = "L" + var1.replace('.', '/') + ";";
      bjn var3 = this.q.za(var2);
      if (var3 != null) {
         this.RF.Uv(this.q, var3.getClassTypeIndex());
      } else {
         this.RF.append("?");
      }
   }
}
