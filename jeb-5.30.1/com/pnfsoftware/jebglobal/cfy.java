package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.lang.reflect.Array;

public class cfy extends AbstractDOptimizer {
   public cfy() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      cfy.eo var1 = new cfy.eo();
      var1.q = ((btx)this.g).nf();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstruction(var1, true);
      }

      return var1.RF;
   }

   private IDImm q(String var1, Object var2) {
      switch (var1) {
         case "Z":
            return this.ctx.createBoolean((Boolean)var2);
         case "B":
            return this.ctx.createByte((Byte)var2);
         case "C":
            return this.ctx.createChar((Character)var2);
         case "S":
            return this.ctx.createShort((Short)var2);
         case "I":
            return this.ctx.createInt((Integer)var2);
         case "J":
            return this.ctx.createLong((Long)var2);
         case "F":
            return this.ctx.createFloat((Float)var2);
         case "D":
            return this.ctx.createDouble((Double)var2);
         case "Ljava/lang/String;":
            return this.ctx.createString((String)var2);
         default:
            return null;
      }
   }

   class eo implements IDVisitor {
      bwh q;
      int RF;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDStaticField var4 && !var4.isTypeClass()) {
            String var5 = var4.getNativeField(cfy.this.g).getSignature(false);
            bwh.eo var6 = this.q.q(var5);
            if (var6 != null) {
               bwh.CU var7 = var6.Uv();
               if (var7.q(bwh.CU.xK)) {
                  String var8 = var6.RF();
                  if (!var8.startsWith("[")) {
                     IDImm var9;
                     try {
                        Object var10 = var6.Dw();
                        var9 = cfy.this.q(var8, var10);
                     } catch (Exception var17) {
                        AbstractDOptimizer.logger.catchingSilent(var17);
                        return;
                     }

                     if (var9 != null && var2.replaceSubExpression(var1, var9)) {
                        var3.setReplacedNode(var9);
                        this.RF++;
                     }
                  }
               }
            }
         }

         if (var1 instanceof IDArrayElt var19
            && var19.getArray() instanceof IDStaticField var20
            && !var20.isTypeClass()
            && var19.getIndex() instanceof IDImm var21) {
            String var24 = var20.getNativeField(cfy.this.g).getSignature(false);
            bwh.eo var25 = this.q.q(var24);
            if (var25 != null) {
               bwh.CU var26 = var25.Uv();
               if (var26.q(bwh.CU.xK)) {
                  int var27 = (int)var21.getValueAsLong();
                  if (var27 < 0) {
                     return;
                  }

                  String var11 = var25.RF();
                  if (!var11.startsWith("[")) {
                     return;
                  }

                  String var12 = var11.substring(1);
                  if (var12.startsWith("[")) {
                     return;
                  }

                  IDImm var13;
                  try {
                     Object var14 = var25.Dw();
                     int var15 = Array.getLength(var14);
                     if (var27 >= var15) {
                        return;
                     }

                     Object var16 = Array.get(var14, var27);
                     var13 = cfy.this.q(var12, var16);
                  } catch (Exception var18) {
                     AbstractDOptimizer.logger.catchingSilent(var18);
                     return;
                  }

                  if (var13 != null && var2.replaceSubExpression(var1, var13)) {
                     var3.setReplacedNode(var13);
                     this.RF++;
                  }
               }
            }
         }
      }
   }
}
