package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.IGenericUnpacker;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalStubException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Arrays;
import java.util.Collection;

public class ble implements IGenericUnpacker {
   private static final ILogger q = GlobalLog.getLogger(ble.class);
   private IApkUnit RF;
   private IEmulatedAndroid xK;
   private boolean Dw;
   private boolean Uv;
   private boolean oW;
   private blf gO;

   public ble(IDexDecompilerUnit var1) {
      this(DexUtil.findParentApk(var1.getCodeUnit()));
   }

   public ble(IApkUnit var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
      }
   }

   @Override
   public IEmulatedAndroid getEmulatedAndroid() {
      if (this.xK == null) {
         this.xK = this.RF.createEmulatedAndroid();
         IDState var1 = this.xK.getState();
         ((bye)var1).setLazyJNIOnLoadExec(false);
      }

      return this.xK;
   }

   @Override
   public void setMonitorEvents(boolean var1) {
      this.oW = var1;
   }

   @Override
   public void setMaxExecutionTime(long var1) {
      this.getEmulatedAndroid().getState().setMaxDuration(var1);
   }

   @Override
   public void setIntegrateRecoveredDexFiles(boolean var1) {
      ((bld)this.getEmulatedAndroid()).q(var1);
   }

   @Override
   public void setUseRecoveredSoFiles(boolean var1) {
      ((bld)this.getEmulatedAndroid()).RF(var1);
   }

   @Override
   public Collection getRecoveredDexHashes() {
      return ((bld)this.getEmulatedAndroid()).nf();
   }

   @Override
   public Collection getRecoveredSoHashes() {
      return ((bld)this.getEmulatedAndroid()).oW();
   }

   @Override
   public boolean shouldAttemptUnpack() {
      IDexUnit var1 = this.RF.getDex();
      String var2 = this.RF.getApplicationName();
      if (var2 != null) {
         String var3 = "L" + var2.replace('.', '/') + ";";
         IDexClass var4 = var1.getClass(var3);
         if (var4 != null
            && DexUtil.findInternalVirtualMethodTarget(
                  var1,
                  var4,
                  cvm.q(new byte[]{-64, 21, 0, 21, 2, 11, 42, 35, 18, 22, 38, 44, 1, 26, 17, 29, 12}, 1, 161),
                  cvm.q(new byte[]{41, 45, 15, 10, 22, 29, 6, 13, 75, 76, 12, 1, 26, 17, 11, 26, 91, 108, 44, 1, 26, 17, 29, 12, 79}, 1, 101)
               )
               != null) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void attemptUnpack() throws DexDecEvaluationException {
      if (this.Dw) {
         throw new IllegalStateException();
      } else {
         this.Dw = true;
         System.currentTimeMillis();

         try {
            this.getEmulatedAndroid();
            IDexUnit var1 = this.RF.getDex();
            IDState var2 = this.xK.getState();
            if (this.oW) {
               this.gO = new blf(this);
               this.gO.q();
            }

            String var4 = this.RF.getApplicationName();
            String var3;
            if (var4 == null) {
               var3 = "Landroid/app/Application;";
            } else {
               var3 = "L" + var4.replace('.', '/') + ";";
               IDexClass var5 = var1.getClass(var3);
               if (var5 == null) {
                  throw new RuntimeException("The application class was not found: " + var3);
               }

               if (DexUtil.findInternalVirtualMethodTarget(
                     var1,
                     var5,
                     cvm.q(new byte[]{34, 27, 4, 24, 17, 1, 37, 9, 7, 69, 107, 12, 71, 84, 84, 65, 77}, 2, 168),
                     cvm.q(new byte[]{118, 45, 15, 10, 22, 29, 6, 13, 75, 76, 12, 1, 26, 17, 11, 26, 91, 108, 44, 1, 26, 17, 29, 12, 79}, 1, 58)
                  )
                  != null) {
                  this.Uv = true;
               }
            }

            Object[] var10000 = new Object[0];
            var2.loadClass(var3);
            var2.pushContext("gu");
            var10000 = new Object[0];
            IDImm var24 = var2.createNewInstance(var3);
            ((bld)this.xK).q(var2.getObject(var24, true));
            IDImm var6 = var2.registerObject(((bld)this.xK).LK);
            String var7 = cvm.q(
               new byte[]{
                  15,
                  14,
                  30,
                  29,
                  0,
                  6,
                  14,
                  12,
                  91,
                  67,
                  71,
                  13,
                  93,
                  69,
                  95,
                  77,
                  22,
                  112,
                  67,
                  78,
                  70,
                  85,
                  74,
                  64,
                  123,
                  82,
                  46,
                  2,
                  17,
                  6,
                  30,
                  94,
                  13,
                  95,
                  15,
                  16,
                  91,
                  14,
                  17,
                  72,
                  43,
                  21,
                  0,
                  69,
                  34,
                  9,
                  8,
                  29,
                  9,
                  17,
                  21,
                  92,
                  41,
                  18,
                  64,
                  68,
                  51,
                  3,
                  5,
                  68,
                  93,
                  10,
                  8,
                  6,
                  0,
                  22,
                  78,
                  6,
                  74,
                  48,
                  10,
                  28,
                  2,
                  0,
                  28,
                  90,
                  27,
                  106,
                  57
               },
               2,
               84
            );

            try {
               var10000 = new Object[0];
               var2.invokeMethod(var7, Arrays.asList(var24, var6), DInvokeType.VIRTUAL);
            } catch (DexDecEvalStubException var19) {
               if (!JvmMethodSig.nameAndParams(var19.getStubMethodSignature())
                  .equals(
                     cvm.q(
                        new byte[]{
                           34,
                           27,
                           4,
                           24,
                           17,
                           1,
                           37,
                           9,
                           7,
                           69,
                           107,
                           12,
                           71,
                           84,
                           84,
                           65,
                           77,
                           27,
                           96,
                           65,
                           92,
                           84,
                           64,
                           91,
                           69,
                           68,
                           96,
                           17,
                           14,
                           13,
                           24,
                           0,
                           78,
                           21,
                           65,
                           39,
                           64,
                           1,
                           6,
                           69,
                           17,
                           0,
                           72,
                           9
                        },
                        2,
                        193
                     )
                  )) {
                  throw var19;
               }
            }

            var7 = cvm.q(
               new byte[]{
                  -74,
                  45,
                  15,
                  10,
                  22,
                  29,
                  6,
                  13,
                  75,
                  78,
                  17,
                  0,
                  95,
                  110,
                  49,
                  0,
                  28,
                  5,
                  10,
                  2,
                  21,
                  29,
                  6,
                  1,
                  85,
                  22,
                  19,
                  81,
                  1,
                  45,
                  49,
                  23,
                  4,
                  21,
                  17,
                  77,
                  1,
                  127
               },
               1,
               250
            );

            try {
               var10000 = new Object[0];
               var2.invokeMethod(var7, Arrays.asList(var24), DInvokeType.VIRTUAL);
            } catch (DexDecEvalStubException var20) {
               if (!JvmMethodSig.nameAndParams(var20.getStubMethodSignature()).equals(cvm.q(new byte[]{44, 1, 51, 11, 23, 8, 19, 13, 92, 9}, 2, 15))) {
                  throw var20;
               }
            }

            var2.deleteTopContext();
            String var8 = this.RF.getMainActivity();
            if (var8 != null) {
               String var9 = "L" + var8.replace('.', '/') + ";";
               IDexClass var10 = var1.getClass(var9);
               if (var10 == null) {
                  q.warn(S.L("The main acvitity class was not found: %s (it may be unpacked during the app's initialization)"), var9);
               } else {
                  var10000 = new Object[0];
                  var2.loadClass(var9);
                  var2.pushContext("gu");
                  var10000 = new Object[0];
                  IDImm var11 = var2.createNewInstance(var9);
                  if (DexUtil.findInternalVirtualMethodTarget(
                        var1,
                        var10,
                        cvm.q(new byte[]{44, 1, 51, 11, 23, 8, 19, 13}, 2, 131),
                        cvm.q(new byte[]{-121, 45, 15, 10, 22, 29, 6, 13, 75, 64, 28, 92, 109, 55, 27, 10, 8, 9, 94}, 1, 203)
                     )
                     != null) {
                     var7 = var9
                        + cvm.q(
                           new byte[]{
                              -115, 19, 81, 1, 45, 49, 23, 4, 21, 17, 77, 100, 45, 15, 10, 22, 29, 6, 13, 75, 64, 28, 92, 109, 55, 27, 10, 8, 9, 94, 18, 127
                           },
                           1,
                           160
                        );

                     try {
                        IDImm var12 = var2.createNewInstance(
                           cvm.q(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 107, 85, 95, 93, 85, 86, 23}, 2, 126)
                        );
                        var10000 = new Object[0];
                        var2.invokeMethod(var7, Arrays.asList(var11, var12), DInvokeType.VIRTUAL);
                     } catch (DexDecEvalStubException var21) {
                        if (!JvmMethodSig.nameAndParams(var21.getStubMethodSignature())
                           .equals(
                              cvm.q(
                                 new byte[]{44, 1, 51, 11, 23, 8, 19, 13, 92, 108, 73, 13, 77, 82, 94, 80, 93, 28, 67, 83, 29, 114, 71, 90, 72, 76, 42, 73, 72},
                                 2,
                                 235
                              )
                           )) {
                           throw var21;
                        }
                     }
                  }

                  var2.deleteTopContext();
               }
            }
         } catch (DexDecEvaluationException var22) {
            if (this.Uv) {
               com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var22);
            }

            q.catchingSilent(var22);
            throw var22;
         } finally {
            System.currentTimeMillis();
         }
      }
   }

   @Override
   public String formatMonitorReport(int var1) {
      if (this.gO == null) {
         throw new IllegalStateException();
      } else {
         return this.gO.q(var1);
      }
   }

   @Override
   public void teardown() {
      if (this.xK != null) {
         ((bld)this.xK).q();
      }
   }
}
