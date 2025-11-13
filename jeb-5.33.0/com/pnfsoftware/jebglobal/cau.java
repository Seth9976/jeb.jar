package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ApkManifestHelper;
import com.pnfsoftware.jeb.core.units.code.android.ApkStringResHelper;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.WeakHashMap;

public class cau extends AbstractDOptimizer {
   private static WeakHashMap pC = new WeakHashMap();
   private static WeakHashMap A = new WeakHashMap();

   public cau() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      if (!(this.dex.getParent() instanceof IApkUnit var1)) {
         return 0;
      } else {
         cau.Sv var5 = new cau.Sv();
         var5.pC = var1;

         for (IDInstruction var4 : this.cfg.instructions()) {
            var4.visitInstruction(var5, true);
         }

         if (var5.A > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var5.A;
      }
   }

   private static ApkStringResHelper pC(IApkUnit var0) {
      if (var0 == null) {
         return null;
      } else {
         ApkStringResHelper var1 = (ApkStringResHelper)pC.get(var0);
         if (var1 == null) {
            if (pC.containsKey(var0)) {
               return null;
            }

            synchronized (pC) {
               var1 = (ApkStringResHelper)pC.get(var0);
               if (var1 == null) {
                  try {
                     var1 = ((com.pnfsoftware.jeb.corei.parsers.apk.Ws)var0).pC();
                  } catch (Exception var4) {
                  }

                  pC.put(var0, var1);
               }
            }
         }

         return var1;
      }
   }

   private static cau.Av A(IApkUnit var0) {
      if (var0 == null) {
         return null;
      } else {
         cau.Av var1 = (cau.Av)A.get(var0);
         if (var1 == null) {
            if (A.containsKey(var0)) {
               return null;
            }

            synchronized (A) {
               var1 = (cau.Av)A.get(var0);
               if (var1 == null) {
                  try {
                     var1 = new cau.Av();
                     ApkManifestHelper var3 = new ApkManifestHelper(var0);
                     var1.pC = var3.readAttribute("uses-sdk", "targetSdkVersion");
                  } catch (Exception var5) {
                  }

                  A.put(var0, var1);
               }
            }
         }

         return var1;
      }
   }

   private static class Av {
      String pC;
   }

   class Sv implements IDVisitor {
      IApkUnit pC;
      int A;

      private boolean pC(String var1, String var2, String... var3) {
         int var4 = var1.indexOf("->");
         if (var1.substring(var4 + 2).equals(var2)) {
            String var5 = var1.substring(0, var4);
            IDTypeInfoProvider var6 = cau.this.g.getTypeInfoProvider();

            for (String var10 : var3) {
               if (var6.isCompatible(var5, var10)) {
                  return true;
               }
            }
         }

         return false;
      }

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         IDImm var4 = null;
         if (var1 instanceof IDCallInfo var5) {
            if (var5.getInvokeType() == DInvokeType.VIRTUAL) {
               String var7 = var5.getMethodSignature();
               if (this.pC(var7, "getString(I)Ljava/lang/String;", "Landroid/content/Context;", "Landroid/content/res/Resources;")) {
                  if (var5.getArgument(1) instanceof IDImm var8) {
                     int var15 = (int)var8.getRawValue();
                     if (this.pC != null) {
                        ApkStringResHelper var10 = cau.pC(this.pC);
                        if (var10 != null) {
                           String var11 = var10.getDefaultString(var15);
                           if (var11 != null) {
                              var4 = cau.this.g.createString(var11);
                           }
                        }
                     }
                  }
               } else if (this.pC(var7, "getPackageName()Ljava/lang/String;", "Landroid/content/Context;")) {
                  String var13 = this.pC.getPackageName();
                  if (var13 != null) {
                     var4 = cau.this.g.createString(var13);
                  }
               }
            }
         } else if (var1 instanceof IDInstanceField var6 && !var6.isArrayLength()) {
            String var12 = var6.getNativeField(cau.this.g).getSignature(false);
            if (var12.equals("Landroid/content/pm/ApplicationInfo;->targetSdkVersion:I")) {
               cau.Av var14 = cau.A(this.pC);
               if (var14 != null) {
                  String var16 = var14.pC;
                  if (!Strings.isBlank(var16)) {
                     int var17 = Conversion.stringToInt(var16);
                     if (var17 != 0) {
                        var4 = cau.this.g.createInt(var17);
                     }
                  }
               }
            }
         }

         if (var4 != null && var2.replaceSubExpression(var1, var4)) {
            var3.setReplacedNode(var4);
            this.A++;
         }
      }
   }
}
