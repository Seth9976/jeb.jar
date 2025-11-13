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

public class cbc extends AbstractDOptimizer {
   public cbc() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      cbc.Av var1 = new cbc.Av();
      var1.pC = ((bpr)this.g).ys();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstruction(var1, true);
      }

      return var1.A;
   }

   private IDImm pC(String var1, Object var2) {
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

   class Av implements IDVisitor {
      brw pC;
      int A;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDStaticField var4 && !var4.isTypeClass()) {
            String var5 = var4.getNativeField(cbc.this.g).getSignature(false);
            brw.Av var6 = this.pC.pC(var5);
            if (var6 != null) {
               brw.Sv var7 = var6.kS();
               if (var7.pC(brw.Sv.kS)) {
                  String var8 = var6.pC();
                  if (!var8.startsWith("[")) {
                     IDImm var9;
                     try {
                        Object var10 = var6.A();
                        var9 = cbc.this.pC(var8, var10);
                     } catch (Exception var17) {
                        AbstractDOptimizer.logger.catchingSilent(var17);
                        return;
                     }

                     if (var9 != null && var2.replaceSubExpression(var1, var9)) {
                        var3.setReplacedNode(var9);
                        this.A++;
                     }
                  }
               }
            }
         }

         if (var1 instanceof IDArrayElt var19
            && var19.getArray() instanceof IDStaticField var20
            && !var20.isTypeClass()
            && var19.getIndex() instanceof IDImm var21) {
            String var24 = var20.getNativeField(cbc.this.g).getSignature(false);
            brw.Av var25 = this.pC.pC(var24);
            if (var25 != null) {
               brw.Sv var26 = var25.kS();
               if (var26.pC(brw.Sv.kS)) {
                  int var27 = (int)var21.getValueAsLong();
                  if (var27 < 0) {
                     return;
                  }

                  String var11 = var25.pC();
                  if (!var11.startsWith("[")) {
                     return;
                  }

                  String var12 = var11.substring(1);
                  if (var12.startsWith("[")) {
                     return;
                  }

                  IDImm var13;
                  try {
                     Object var14 = var25.A();
                     int var15 = Array.getLength(var14);
                     if (var27 >= var15) {
                        return;
                     }

                     Object var16 = Array.get(var14, var27);
                     var13 = cbc.this.pC(var12, var16);
                  } catch (Exception var18) {
                     AbstractDOptimizer.logger.catchingSilent(var18);
                     return;
                  }

                  if (var13 != null && var2.replaceSubExpression(var1, var13)) {
                     var3.setReplacedNode(var13);
                     this.A++;
                  }
               }
            }
         }
      }
   }
}
