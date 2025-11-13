package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.Set;

public class bwt extends AbstractDOptimizer {
   public static String pC = ckx.pC(new byte[]{36, 26, 17, 11, 22}, 2, 112);
   public static String A = pC + "0";
   public static String kS = pC + "1";
   public static String wS = "___" + pC;
   public static String UT = wS + "0";
   public static String E = wS + "1";
   private volatile boolean sY;
   private IDexField ys;
   private IDexField ld;

   public bwt() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      bpl.kS(this);
   }

   @Override
   public int perform() {
      IDexType var1 = this.ctx.getMethod().getClassType();
      if (var1 == null) {
         return 0;
      } else {
         IDexClass var2 = var1.getImplementingClass();
         if (var2 == null) {
            return 0;
         } else {
            if (!this.sY) {
               this.sY = true;

               for (IDexField var4 : var2.getFields()) {
                  if (var4.getData().isStatic() && var4.getFieldTypeSignature(true).equals("I") && var4.isRenamed()) {
                     String var5 = var4.getName(true);
                     int var6 = var5.length();
                     if (var6 == 6 && var5.startsWith(pC)) {
                        if (var5.equals(A)) {
                           this.ys = var4;
                        } else if (var5.equals(kS)) {
                           this.ld = var4;
                        }
                     } else if (var6 >= 10 && var5.substring(var6 - 9, var6 - 1).equals(wS)) {
                        if (var5.endsWith(UT)) {
                           this.ys = var4;
                        } else if (var5.endsWith(E)) {
                           this.ld = var4;
                        }
                     }
                  }
               }
            }

            bwk.Sv var12 = bwk.pC(this.decomp, false);
            Set var13 = var12 == null ? null : var12.pC;
            Set var14 = var12 == null ? null : var12.A;
            if (this.ys == null && this.ld == null && var13 == null && var14 == null) {
               return 0;
            } else {
               int var15 = 0;
               bwt.Av var7 = new bwt.Av(var13, var14);

               for (IDInstruction var9 : this.cfg.instructions()) {
                  var9.visitInstructionPostOrder(var7, true);
               }

               var15 += var7.pC;

               for (IDInstruction var18 : this.cfg.instructions()) {
                  if (var18.isAssign()
                     && var18.getAssignDestination() instanceof IDStaticField var10
                     && var7.pC(var10) >= 0
                     && !var18.getAssignSource().hasSideEffects(this.ctx, true)) {
                     var18.transformToNop();
                     var15++;
                  }
               }

               if (var15 > 0) {
                  this.cfg.invalidateDataFlowAnalysis();
               }

               return var15;
            }
         }
      }
   }

   class Av implements IDVisitor {
      int pC;
      Set A;
      Set kS;

      Av(Set var2, Set var3) {
         this.A = var2;
         this.kS = var3;
      }

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDStaticField var4) {
            int var5 = this.pC(var4);
            if (var5 >= 0 && var2.replaceSubExpression(var1, bwt.this.g.createInt(var5))) {
               this.pC(var4);
               this.pC++;
            }
         }
      }

      int pC(IDStaticField var1) {
         IDexField var2 = var1.getNativeField(bwt.this.g);
         if (var2 == null) {
            return -1;
         } else if (bwt.this.ys != null && var2 == bwt.this.ys) {
            return 0;
         } else if (bwt.this.ld != null && var2 == bwt.this.ld) {
            return 1;
         } else {
            String var3 = null;
            if (this.A != null) {
               var3 = var2.getSignature(false);
               if (this.A.contains(var3)) {
                  return 0;
               }
            }

            if (this.kS != null) {
               if (var3 == null) {
                  var3 = var2.getSignature(false);
               }

               if (this.kS.contains(var3)) {
                  return 1;
               }
            }

            return -1;
         }
      }
   }
}
