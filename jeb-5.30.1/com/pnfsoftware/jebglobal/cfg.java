package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class cfg extends AbstractDOptimizer {
   public cfg() {
      this.addTag("deobfuscator");
      bto.xK(this);
   }

   @Override
   public int perform() {
      int var1 = 0;
      cfg.eo var2 = new cfg.eo();

      for (int var3 = 0; var3 < this.cfg.size(); var3++) {
         BasicBlock var4 = this.cfg.get(var3);

         for (int var5 = 0; var5 < var4.size(); var5++) {
            IDInstruction var6 = (IDInstruction)var4.get(var5);
            if (var6.canThrow()) {
               var6.visitDepthPost(var2);
               if (var2.q != null) {
                  IDInstruction var7 = var2.q;
                  var2.q = null;
                  var7.copyBaseFields(var6);
                  var4.set(var5, var7);
                  if (var5 < var4.size() - 1) {
                     DUtil.splitBlock(this.ctx, var4, var5 + 1);
                  }

                  this.cfg.deleteOutEdges(var4);
                  var1++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.cleanGraph();
         this.invalidateChains();
      }

      return var1;
   }

   class eo implements IDVisitor {
      IDInstruction q;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         int var5;
         if (var1 instanceof IDNewArrayInfo var4
            && var4.getCountOfInitialValues() == 0
            && var4.getSize() instanceof IDImm var6
            && (var5 = (int)var6.getRawValue()) < 0) {
            this.q = cfg.this.ctx
               .createThrow(
                  cfg.this.ctx.createNewInfo("Ljava/lang/NegativeArraySizeException;-><init>(Ljava/lang/String;)V", cfg.this.ctx.createString(var5 + ""))
               );
            var3.interrupt(true);
         } else if (var1 instanceof IDArrayElt var8 && var8.getIndex() instanceof IDImm var14 && (var5 = (int)var14.getRawValue()) < 0) {
            this.q = cfg.this.ctx
               .createThrow(cfg.this.ctx.createNewInfo("Ljava/lang/ArrayIndexOutOfBoundsException;-><init>(I)V", cfg.this.ctx.createInt(var5)));
            var3.interrupt(true);
         } else {
            IDExpression var9 = null;
            if (var1 instanceof IDCallInfo var11 && var11.hasThis() && var11.getCountOfArguments() >= 1) {
               var9 = var11.getArgument(0);
            } else if (var1 instanceof IDInstanceField var15) {
               var9 = var15.getInstance();
            } else if (var1 instanceof IDArrayElt var18) {
               var9 = var18.getArray();
            }

            if (var9 instanceof IDImm var12 && var12.isNullRef()) {
               this.q = cfg.this.ctx.createThrow(cfg.this.ctx.createNewInfo("Ljava/lang/NullPointerException;-><init>()V"));
               var3.interrupt(true);
            } else if (var1 instanceof IDOperation var13
               && var13.getOperatorType().isAnyOf(JavaOperatorType.DIV, JavaOperatorType.REM)
               && (var13.getType().isSmallInt() || var13.getType().isLong())
               && var13.getOperand2() instanceof IDImm var16
               && var16.isZero()) {
               this.q = cfg.this.ctx
                  .createThrow(
                     cfg.this.ctx.createNewInfo("Ljava/lang/ArithmeticException;-><init>(Ljava/lang/String;)V", cfg.this.ctx.createString("/ by zero"))
                  );
               var3.interrupt(true);
            } else if (var1.hasSideEffects(cfg.this.ctx, false)) {
               var3.interrupt(false);
            }
         }
      }
   }
}
