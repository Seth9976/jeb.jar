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

public class bhh implements IGenericUnpacker {
   private static final ILogger pC = GlobalLog.getLogger(bhh.class);
   private IApkUnit A;
   private IEmulatedAndroid kS;
   private boolean wS;
   private boolean UT;
   private boolean E;
   private bhi sY;

   public bhh(IDexDecompilerUnit var1) {
      this(DexUtil.findParentApk(var1.getCodeUnit()));
   }

   public bhh(IApkUnit var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public IEmulatedAndroid getEmulatedAndroid() {
      if (this.kS == null) {
         this.kS = this.A.createEmulatedAndroid();
         IDState var1 = this.kS.getState();
         ((btp)var1).setLazyJNIOnLoadExec(false);
      }

      return this.kS;
   }

   @Override
   public void setMonitorEvents(boolean var1) {
      this.E = var1;
   }

   @Override
   public void setMaxExecutionTime(long var1) {
      this.getEmulatedAndroid().getState().setMaxDuration(var1);
   }

   @Override
   public void setIntegrateRecoveredDexFiles(boolean var1) {
      ((bhg)this.getEmulatedAndroid()).pC(var1);
   }

   @Override
   public void setUseRecoveredSoFiles(boolean var1) {
      ((bhg)this.getEmulatedAndroid()).A(var1);
   }

   @Override
   public Collection getRecoveredDexHashes() {
      return ((bhg)this.getEmulatedAndroid()).sY();
   }

   @Override
   public Collection getRecoveredSoHashes() {
      return ((bhg)this.getEmulatedAndroid()).UT();
   }

   @Override
   public boolean shouldAttemptUnpack() {
      String var1 = this.A.getApplicationComponentFactory();
      if (var1 != null) {
         return true;
      } else {
         String var2 = this.A.getApplicationName();
         if (var2 != null) {
            IDexUnit var3 = this.A.getDex();
            String var4 = "L" + var2.replace('.', '/') + ";";
            IDexClass var5 = var3.getClass(var4);
            if (var5 != null
               && DexUtil.findInternalVirtualMethodTarget(
                     var3,
                     var5,
                     ckx.pC(new byte[]{34, 27, 4, 24, 17, 1, 37, 9, 7, 69, 107, 12, 71, 84, 84, 65, 77}, 2, 206),
                     ckx.pC(new byte[]{-2, 45, 15, 10, 22, 29, 6, 13, 75, 76, 12, 1, 26, 17, 11, 26, 91, 108, 44, 1, 26, 17, 29, 12, 79}, 1, 178)
                  )
                  != null) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public void attemptUnpack() throws DexDecEvaluationException {
      if (this.wS) {
         throw new IllegalStateException();
      } else {
         this.wS = true;
         System.currentTimeMillis();

         try {
            this.getEmulatedAndroid();
            IDexUnit var1 = this.A.getDex();
            IDState var2 = this.kS.getState();
            if (this.E) {
               this.sY = new bhi(this);
               this.sY.pC();
            }

            String var4 = this.A.getApplicationComponentFactory();
            if (var4 != null) {
               String var3 = "L" + var4.replace('.', '/') + ";";
               var2.loadClass(var3);
               var2.pushContext("gu");
               var2.createNewInstance(var3);
               var2.deleteTopContext();
            }

            String var6 = this.A.getApplicationName();
            String var5;
            if (var6 == null) {
               var5 = "Landroid/app/Application;";
            } else {
               var5 = "L" + var6.replace('.', '/') + ";";
               IDexClass var7 = var1.getClass(var5);
               if (var7 == null) {
                  throw new RuntimeException("The application class was not found: " + var5);
               }

               if (DexUtil.findInternalVirtualMethodTarget(
                     var1,
                     var7,
                     ckx.pC(new byte[]{34, 27, 4, 24, 17, 1, 37, 9, 7, 69, 107, 12, 71, 84, 84, 65, 77}, 2, 137),
                     ckx.pC(new byte[]{-63, 45, 15, 10, 22, 29, 6, 13, 75, 76, 12, 1, 26, 17, 11, 26, 91, 108, 44, 1, 26, 17, 29, 12, 79}, 1, 141)
                  )
                  != null) {
                  this.UT = true;
               }
            }

            Object[] var10000 = new Object[0];
            var2.loadClass(var5);
            var2.pushContext("gu");
            var10000 = new Object[0];
            IDImm var26 = var2.createNewInstance(var5);
            ((bhg)this.kS).pC(var2.getObject(var26, true));
            IDImm var8 = var2.registerObject(((bhg)this.kS).WR);
            String var9 = ckx.pC(
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
                  65,
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
                  120,
                  70,
                  38
               },
               2,
               202
            );

            try {
               var10000 = new Object[0];
               var2.invokeMethod(var9, Arrays.asList(var26, var8), DInvokeType.VIRTUAL);
            } catch (DexDecEvalStubException var21) {
               if (!JvmMethodSig.nameAndParams(var21.getStubMethodSignature())
                  .equals(
                     ckx.pC(
                        new byte[]{
                           -93,
                           21,
                           0,
                           21,
                           2,
                           11,
                           42,
                           35,
                           18,
                           22,
                           38,
                           44,
                           1,
                           26,
                           17,
                           29,
                           12,
                           92,
                           100,
                           45,
                           15,
                           10,
                           22,
                           29,
                           6,
                           13,
                           75,
                           76,
                           12,
                           1,
                           26,
                           17,
                           11,
                           26,
                           91,
                           108,
                           44,
                           1,
                           26,
                           17,
                           29,
                           12,
                           79,
                           18
                        },
                        1,
                        194
                     )
                  )) {
                  throw var21;
               }
            }

            var9 = ckx.pC(
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
                  65,
                  88,
                  19,
                  6,
                  97,
                  65,
                  73,
                  85,
                  90,
                  79,
                  65,
                  70,
                  89,
                  93,
                  91,
                  23,
                  13,
                  113,
                  29,
                  15,
                  32,
                  30,
                  0,
                  65,
                  21,
                  11,
                  76,
                  6,
                  57
               },
               2,
               208
            );

            try {
               var10000 = new Object[0];
               var2.invokeMethod(var9, Arrays.asList(var26), DInvokeType.VIRTUAL);
            } catch (DexDecEvalStubException var22) {
               if (!JvmMethodSig.nameAndParams(var22.getStubMethodSignature()).equals(ckx.pC(new byte[]{-43, 1, 45, 49, 23, 4, 21, 17, 77, 1}, 1, 186))) {
                  throw var22;
               }
            }

            var2.deleteTopContext();
            String var10 = this.A.getMainActivity();
            if (var10 != null) {
               String var11 = "L" + var10.replace('.', '/') + ";";
               IDexClass var12 = var1.getClass(var11);
               if (var12 == null) {
                  pC.warn(S.L("The main acvitity class was not found: %s (it may be unpacked during the app's initialization)"), var11);
               } else {
                  var10000 = new Object[0];
                  var2.loadClass(var11);
                  var2.pushContext("gu");
                  var10000 = new Object[0];
                  IDImm var13 = var2.createNewInstance(var11);
                  if (DexUtil.findInternalVirtualMethodTarget(
                        var1,
                        var12,
                        ckx.pC(new byte[]{44, 1, 51, 11, 23, 8, 19, 13}, 2, 72),
                        ckx.pC(new byte[]{-107, 45, 15, 10, 22, 29, 6, 13, 75, 64, 28, 92, 109, 55, 27, 10, 8, 9, 94}, 1, 217)
                     )
                     != null) {
                     var9 = var11
                        + ckx.pC(
                           new byte[]{
                              -72, 19, 81, 1, 45, 49, 23, 4, 21, 17, 77, 100, 45, 15, 10, 22, 29, 6, 13, 75, 64, 28, 92, 109, 55, 27, 10, 8, 9, 94, 18, 127
                           },
                           1,
                           149
                        );

                     try {
                        IDImm var14 = var2.createNewInstance(
                           ckx.pC(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 107, 85, 95, 93, 85, 86, 23}, 2, 251)
                        );
                        var10000 = new Object[0];
                        var2.invokeMethod(var9, Arrays.asList(var13, var14), DInvokeType.VIRTUAL);
                     } catch (DexDecEvalStubException var23) {
                        if (!JvmMethodSig.nameAndParams(var23.getStubMethodSignature())
                           .equals(
                              ckx.pC(
                                 new byte[]{-108, 1, 45, 49, 23, 4, 21, 17, 77, 100, 45, 15, 10, 22, 29, 6, 13, 75, 64, 28, 92, 109, 55, 27, 10, 8, 9, 94, 18},
                                 1,
                                 251
                              )
                           )) {
                           throw var23;
                        }
                     }
                  }

                  var2.deleteTopContext();
               }
            }
         } catch (DexDecEvaluationException var24) {
            if (this.UT) {
               com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var24);
            }

            pC.catchingSilent(var24);
            throw var24;
         } finally {
            System.currentTimeMillis();
         }
      }
   }

   @Override
   public String formatMonitorReport(int var1) {
      return this.sY == null ? "" : this.sY.pC(var1);
   }

   @Override
   public void teardown() {
      if (this.kS != null) {
         ((bhg)this.kS).pC();
      }
   }
}
