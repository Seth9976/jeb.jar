package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.ir.DEmuExternalPolicy;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.IniFileEditor;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

public class btr {
   private static final ILogger E = GlobalLog.getLogger(btr.class);
   public static final int pC = 100000;
   public static final long A = 10000L;
   private btp sY;
   private int ys = 100000;
   private long ld = 10000L;
   static AtomicBoolean kS = new AtomicBoolean();
   static AtomicBoolean wS = new AtomicBoolean();
   static AtomicBoolean UT = new AtomicBoolean();

   public static synchronized btr pC(btp var0) {
      Assert.a(var0 != null);
      btr var1 = (btr)var0.getData("helper_object");
      if (var1 == null) {
         var1 = new btr(var0);
         var0.setData("helper_object", var1);
      }

      return var1;
   }

   private btr(btp var1) {
      this.sY = var1;
   }

   public void pC(boolean var1, boolean var2, boolean var3) {
      if (var1 && !this.sY.kS()) {
         this.sY.wS();
         this.sY.setMaxIterationCount(100000);
         this.sY.setMaxDuration(10000L);
         this.sY.setExceptionHandlingEnabled(true);
         this.sY.enableEmulator(true);
         this.sY.enableNativeCodeEmulator(false);
         this.sY.setSubRoutineInvocationPolicy(255);
         this.sY.setFieldAccessPolicy(255);
         this.sY.setExternalPolicy(new DEmuExternalPolicy());
         if (this.sY.enableSandbox(true)) {
            baj var4 = ((com.pnfsoftware.jeb.corei.parsers.dex.vi)this.sY.getDex()).Ek();

            for (File var6 : var4.pC()) {
               try {
                  this.sY.ys().pC(var6);
               } catch (IOException var8) {
                  E.warn(S.L("Cannot register file to classloader: %s - Error: %s"), var6, var8.getMessage());
               }
            }
         }

         this.sY.pC((Collection)btt.pC);
      }

      if (var2) {
         IEmulatedAndroid var9 = bhg.pC((IDState)this.sY);
         if (var9 == null && Licensing.isDebugBuild()) {
            this.sY.registerSandboxHooks(new bts(this));
         }
      }

      if (var3) {
         this.A(this.sY);
      }
   }

   private void A(btp var1) {
      try {
         File var2 = ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var1.getDecompiler()).UT();
         if (var2 != null && var2.isFile() && var2.canRead()) {
            Long var3 = (Long)var1.getData("dexdec_emu_cfgfile_lastmodtime");
            long var4 = var2.lastModified();
            if (var4 != 0L && (var3 == null || var4 != var3)) {
               var1.setData("dexdec_emu_cfgfile_lastmodtime", var4);
               this.pC(var2, var1, true);
            }
         }
      } catch (Exception var6) {
         com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var6);
      }
   }

   private boolean pC(File var1, btp var2, boolean var3) {
      try {
         Boolean var4 = null;
         Boolean var5 = null;
         Boolean var6 = null;
         Long var7 = null;
         Integer var8 = null;
         boolean var9 = false;
         boolean var10 = false;
         boolean var11 = false;
         String var12 = null;
         String var13 = null;
         String var14 = null;
         boolean var15 = true;
         IniFileEditor var16 = new IniFileEditor(var1);
         var16.setCommentChar('#');
         Map var17 = var16.getSectionKeyValues("general");

         for (Entry var19 : var17.entrySet()) {
            String var20 = ((String)var19.getKey()).trim();
            String var21 = ((String)var19.getValue()).trim();
            String var22 = var20.toLowerCase();
            switch (var22) {
               case "enable_emulator":
                  var4 = Boolean.parseBoolean(var21);
                  break;
               case "enable_native_code_emulator":
                  var5 = Boolean.parseBoolean(var21);
                  break;
               case "enable_sandbox":
                  var6 = Boolean.parseBoolean(var21);
                  break;
               default:
                  E.warning("Unknown key in %s: '%s'", var1, var20);
            }
         }

         if (var4 != null) {
            pC(var4, kS, S.L("The dexdec emulator is disabled in the emulator's configuration file! Some optimizers and deobfuscators will be disabled."));
            var2.enableEmulator(var4);
         }

         if (var5 != null) {
            pC(
               var5,
               wS,
               S.L(
                  "The dexdec emulator for native code is disabled in the emulator's configuration file! Some optimizers and deobfuscators will run with limited capabilities."
               )
            );
            var2.enableNativeCodeEmulator(var5);
         }

         if (var6 != null) {
            pC(
               var6,
               UT,
               S.L(
                  "The dexdec emulator sandbox is disabled in the emulator's configuration file! Some optimizers and deobfuscators will run with limited capabilities."
               )
            );
            var2.enableSandbox(var6);
         }

         var17 = var16.getSectionKeyValues("string_decryption");

         for (Entry var29 : var17.entrySet()) {
            String var33 = ((String)var29.getKey()).trim();
            String var37 = ((String)var29.getValue()).trim();
            String var41 = var33.toLowerCase();
            switch (var41) {
               case "emu_max_duration":
                  int var46 = Conversion.stringToInt(var37, 0);
                  if (var46 > 0) {
                     var7 = var46 * 1000L;
                  }
                  break;
               case "emu_max_itercount":
                  int var24 = Conversion.stringToInt(var37, 0);
                  if (var24 > 0) {
                     var8 = var24;
                  }
                  break;
               case "ext_policy_restrict_random":
                  var9 = Boolean.parseBoolean(var37);
                  break;
               case "ext_policy_restrict_time":
                  var10 = Boolean.parseBoolean(var37);
                  break;
               case "ext_policy_restrict_env":
                  var11 = Boolean.parseBoolean(var37);
                  break;
               case "ext_policy_restrict_custom":
                  var12 = var37;
                  break;
               case "ext_policy_whitelist":
                  var13 = var37;
                  break;
               case "ext_policy_blacklist":
                  var14 = var37;
                  break;
               case "ext_policy_default_allow":
                  var15 = Boolean.parseBoolean(var37);
                  break;
               default:
                  E.warning("Unknown key in %s: '%s'", var1, var33);
            }
         }

         if (var7 != null) {
            this.ld = var7;
            var2.setMaxDuration(var7);
         }

         if (var8 != null) {
            this.ys = var8;
            var2.setMaxIterationCount(var8);
         }

         DEmuExternalPolicy var28 = new DEmuExternalPolicy(var9, var10, var11);
         if (var12 != null) {
            for (String var42 : var12.split(",")) {
               var28.addRestricted(var42.trim());
            }
         }

         if (var13 != null) {
            for (String var43 : var13.split(",")) {
               var28.addWhitelisted(var43.trim());
            }
         }

         if (var14 != null) {
            for (String var44 : var14.split(",")) {
               var28.addBlacklisted(var44.trim());
            }
         }

         var28.setDefaultAllow(var15);
         var2.setExternalPolicy(var28);
         if (var3) {
            var2.getGlobalContext().resetDeobfuscatorCounters();
         }
      } catch (IOException var25) {
         E.catching(var25);
      }

      return false;
   }

   public void pC() {
      this.sY.UT();
      this.sY.setMaxIterationCount(this.ys);
      this.sY.setMaxDuration(this.ld);
   }

   private static void pC(boolean var0, AtomicBoolean var1, String var2) {
      if (!var0 && var1.compareAndSet(false, true)) {
         E.warn(var2);
      }
   }
}
