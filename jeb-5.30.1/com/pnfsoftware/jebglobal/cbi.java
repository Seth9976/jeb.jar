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

public class cbi extends AbstractDOptimizer {
   public static String q = cvm.q(new byte[]{36, 26, 17, 11, 22}, 2, 87);
   public static String RF = q + "0";
   public static String xK = q + "1";
   public static String Dw = "___" + q;
   public static String Uv = Dw + "0";
   public static String oW = Dw + "1";
   private volatile boolean gO;
   private IDexField nf;
   private IDexField gP;

   public cbi() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      bto.Dw(this);
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
            if (!this.gO) {
               this.gO = true;

               for (IDexField var4 : var2.getFields()) {
                  if (var4.getData().isStatic() && var4.getFieldTypeSignature(true).equals("I") && var4.isRenamed()) {
                     String var5 = var4.getName(true);
                     int var6 = var5.length();
                     if (var6 == 6 && var5.startsWith(q)) {
                        if (var5.equals(RF)) {
                           this.nf = var4;
                        } else if (var5.equals(xK)) {
                           this.gP = var4;
                        }
                     } else if (var6 >= 10 && var5.substring(var6 - 9, var6 - 1).equals(Dw)) {
                        if (var5.endsWith(Uv)) {
                           this.nf = var4;
                        } else if (var5.endsWith(oW)) {
                           this.gP = var4;
                        }
                     }
                  }
               }
            }

            caz.CU var12 = caz.q(this.decomp, false);
            Set var13 = var12 == null ? null : var12.q;
            Set var14 = var12 == null ? null : var12.RF;
            if (this.nf == null && this.gP == null && var13 == null && var14 == null) {
               return 0;
            } else {
               int var15 = 0;
               cbi.eo var7 = new cbi.eo(var13, var14);

               for (IDInstruction var9 : this.cfg.instructions()) {
                  var9.visitInstructionPostOrder(var7, true);
               }

               var15 += var7.q;

               for (IDInstruction var18 : this.cfg.instructions()) {
                  if (var18.isAssign()
                     && var18.getAssignDestination() instanceof IDStaticField var10
                     && var7.q(var10) >= 0
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

   class eo implements IDVisitor {
      int q;
      Set RF;
      Set xK;

      eo(Set var2, Set var3) {
         this.RF = var2;
         this.xK = var3;
      }

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDStaticField var4) {
            int var5 = this.q(var4);
            if (var5 >= 0 && var2.replaceSubExpression(var1, cbi.this.g.createInt(var5))) {
               this.q(var4);
               this.q++;
            }
         }
      }

      int q(IDStaticField var1) {
         IDexField var2 = var1.getNativeField(cbi.this.g);
         if (var2 == null) {
            return -1;
         } else if (cbi.this.nf != null && var2 == cbi.this.nf) {
            return 0;
         } else if (cbi.this.gP != null && var2 == cbi.this.gP) {
            return 1;
         } else {
            String var3 = null;
            if (this.RF != null) {
               var3 = var2.getSignature(false);
               if (this.RF.contains(var3)) {
                  return 0;
               }
            }

            if (this.xK != null) {
               if (var3 == null) {
                  var3 = var2.getSignature(false);
               }

               if (this.xK.contains(var3)) {
                  return 1;
               }
            }

            return -1;
         }
      }
   }
}
