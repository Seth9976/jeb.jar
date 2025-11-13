package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexFieldData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.Collection;
import java.util.LinkedHashSet;

public class bzj extends AbstractDOptimizer {
   private boolean pC;

   @Override
   public int perform() {
      IDexMethod var1 = this.ctx.getMethod();
      if (!"<clinit>".equals(var1.getName())) {
         return 0;
      } else {
         IDexType var2 = this.ctx.getMethod().getClassType();
         IDexClass var3 = var2.getImplementingClass();
         if (var3 != null && var3.isEnumeration()) {
            int var4 = 0;
            String var5 = var3.getSignature(false);
            String var6 = var5 + "-><init>(";

            for (BasicBlock var8 : this.cfg) {
               label95:
               for (int var9 = 0; var9 < var8.size(); var9++) {
                  IDInstruction var10 = (IDInstruction)var8.get(var9);
                  if (var10.isAssign()) {
                     IDExpression var11 = var10.getAssignDestination();
                     IDExpression var12 = var10.getAssignSource();
                     if (var11 instanceof IDVar var13 && this.pC(var12, var6) && var9 + 1 < var8.size()) {
                        IDInstruction var14 = (IDInstruction)var8.get(var9 + 1);
                        if (var14.isAssign()
                           && var14.getAssignSource() instanceof IDVar
                           && var14.getAssignDestination() instanceof IDStaticField var15
                           && this.pC(var15)
                           && var14.getAssignSource().equals(var13)) {
                           this.analyzeChains();
                           int var23 = var13.getId();
                           Collection var17 = this.dfa.getDefUses(var10.getOffset(), var13.getId());
                           LinkedHashSet var24 = new LinkedHashSet(var17);
                           if (var24.remove(var14.getOffset()) && !var24.isEmpty()) {
                              for (long var19 : var24) {
                                 if (!this.dfa.checkSingleDef(var19, var23, var10.getOffset()) || this.cfg.getInstruction(var19) == null) {
                                    continue label95;
                                 }
                              }

                              for (long var26 : var24) {
                                 ((IDInstruction)this.cfg.getInstruction(var26)).replaceUsedVariable(var13, var15.duplicate());
                              }

                              this.dfa.invalidate();
                              var4++;
                           }
                        }
                     }
                  }
               }
            }

            if (!this.pC && !bpl.pC(this.g)) {
               bzk var21 = new bzk(this, this.ctx, 10);
               int var22 = var21.pC();
               if (var22 > 0) {
                  this.pC = true;
                  var4 += var22;
               }
            }

            return var4;
         } else {
            return 0;
         }
      }
   }

   boolean pC(IDExpression var1, String var2) {
      if (var1 instanceof IDNewInfo var3) {
         String var4 = var3.getMethodSignature();
         if (var4.startsWith(var2) || var4.equals("Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V")) {
            return true;
         }
      }

      return false;
   }

   boolean pC(IDStaticField var1) {
      IDexField var2 = var1.getNativeField(this.g);
      if (var2 == null) {
         return false;
      } else {
         IDexFieldData var3 = var2.getData();
         return var3 != null && var3.isStatic();
      }
   }
}
