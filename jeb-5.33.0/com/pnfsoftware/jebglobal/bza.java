package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;

public class bza extends AbstractDOptimizer {
   private int pC;

   public bza() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      this.pC = 0;
      int var1 = 0;
      BasicBlock var2 = this.cfg.getEntryBlock();

      label91:
      for (IDInstruction var4 : var2) {
         var1++;
         if (var4.isAssign() && var4.getAssignDestination() instanceof IDField var6) {
            IDExpression var7 = var4.getAssignSource();
            int var8;
            if ((var8 = this.pC(var7)) != 0) {
               IDexField var9 = var6.getNativeField(this.g);
               if (var9 != null && var9.isInternal() && var9.getData().isPrivate() && !var9.getData().isVolatile()) {
                  IDField var10 = var6;

                  for (int var11 = 0; var11 < var1; var11++) {
                     if (!((IDInstruction)var2.get(var11)).visitInstruction(new bzb(this, var10), true)) {
                        continue label91;
                     }
                  }

                  int var14 = 0;

                  for (IDInstruction var13 : this.cfg.instructions()) {
                     var14++;
                     if (var14 > var1 && var13.isAssign() && var13.getAssignDestination().equalsEx(var6, false)) {
                        continue label91;
                     }
                  }

                  var14 = 0;

                  for (IDInstruction var17 : this.cfg.instructions()) {
                     if (++var14 > var1) {
                        var17.visitInstruction(new bzc(this, var10, var8, var7), true);
                     }
                  }
               }
            }
         }
      }

      return this.pC;
   }

   private int pC(IDExpression var1) {
      if (var1 instanceof IDImm) {
         if (var1.getType().isPrimitive()) {
            return 1;
         }
      } else if (var1 instanceof IDNewArrayInfo var2) {
         if (var2.getCountOfInitialValues() > 0 && var1.getType().getArrayTypeDimensionBelow().isPrimitive()) {
            return 2;
         }
      } else if (bpl.UT(var1)) {
         return 3;
      }

      return 0;
   }
}
