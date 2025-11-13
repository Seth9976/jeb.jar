package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.BiMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ccl extends AbstractDOptimizer {
   @Override
   public int perform() {
      HashSet var1 = new HashSet();
      HashMap var2 = new HashMap();
      BiMap var3 = new BiMap();

      for (IDVar var5 : this.ctx.getParameterVariables()) {
         var2.put(var5.getId(), null);
      }

      byte var16 = 0;
      BasicBlock var17 = this.cfg.get(0);
      if (var17.allinsize() == 0) {
         var16 = 1;

         for (IDInstruction var7 : var17) {
            if (var7.isAssignToVar()) {
               int var8 = var7.getAssignDestination().asVar().getId();
               if (!var1.contains(var8)) {
                  boolean var9 = false;
                  IDExpression var10 = var7.getAssignSource();
                  if (var10 instanceof IDVar var11) {
                     int var12 = var11.getId();
                     Integer var13 = (Integer)var3.get(var8);
                     if (var13 == null) {
                        var3.put(var8, var12);
                     } else if (var13 != var12) {
                        var9 = true;
                     }
                  } else if (DUtil.hasVariable(var10, var8)) {
                     var9 = true;
                  } else if (var2.containsKey(var8)) {
                     var9 = true;
                  } else {
                     var2.put(var8, var7);
                  }

                  if (var9) {
                     var3.remove(var8);
                     var2.remove(var8);
                     var1.add(var8);
                  }
               }
            }
         }
      }

      for (int var18 = var16; var18 < this.cfg.size(); var18++) {
         for (IDInstruction var24 : this.cfg.get(var18)) {
            if (var24.isAssignToVar()) {
               int var26 = var24.getAssignDestination().asVar().getId();
               if (!var1.contains(var26)) {
                  boolean var28 = false;
                  if (var24.getAssignSource() instanceof IDVar var35) {
                     int var14 = var35.getId();
                     Integer var15 = (Integer)var3.get(var26);
                     if (var15 == null) {
                        var3.put(var26, var14);
                     } else if (var15 != var14) {
                        var28 = true;
                     }
                  } else {
                     var28 = true;
                  }

                  if (var28) {
                     var3.remove(var26);
                     var2.remove(var26);
                     var1.add(var26);
                  }
               }
            }
         }
      }

      int var19 = 0;
      Iterator var21 = var2.keySet().iterator();

      while (true) {
         int var23;
         Integer var25;
         do {
            if (!var21.hasNext()) {
               if (var19 > 0) {
                  this.cfg.invalidateDataFlowAnalysis();
               }

               return var19;
            }

            var23 = (Integer)var21.next();
            var25 = (Integer)var3.get(var23);
            if (var25 != null) {
               break;
            }

            var25 = (Integer)var3.getKeyForValue(var23);
         } while (var25 == null);

         if (!var2.containsKey(var25)) {
            Integer var27 = (Integer)var3.get(var25);
            if (var27 != null && var27 == var23) {
               ArrayList var29 = new ArrayList();

               for (IDInstruction var36 : this.cfg.instructions()) {
                  if (var36.isAssignFromVarToVar()) {
                     int var40 = var36.getAssignDestination().asVar().getId();
                     int var42 = var36.getAssignSource().asVar().getId();
                     if (var40 == var23 && var42 == var25 || var40 == var25 && var42 == var23) {
                        var29.add(var36);
                     }
                  }
               }

               if (var29.size() >= 2) {
                  for (IDInstruction var37 : var29) {
                     var37.transformToNop();
                  }

                  IDInstruction var33 = (IDInstruction)var2.get(var23);
                  if (var33 != null) {
                     Couple var38 = this.cfg.getInstructionLocation(var33.getOffset());
                     DUtil.modifyInstructionSize(this.ctx, var33, 2);
                     var33.adjustSize(-1);
                     IDInstruction var41 = this.ctx.createAssign(this.ctx.getVar(var25), this.ctx.getVar(var23)).withOffset(var33.getOffsetEnd());
                     ((BasicBlock)var38.getFirst()).add((Integer)var38.getSecond() + 1, var41);
                  } else {
                     if (var17.allinsize() != 0) {
                        DUtil.insertHeaderBlock(this.ctx, 1, 2);
                        var17 = this.cfg.get(0);
                     }

                     var33 = (IDInstruction)this.cfg.getInstructionAt(0L);
                     DUtil.modifyInstructionSize(this.ctx, var33, 2);
                     var33.adjustSize(-1);
                     var33.setOffset(var33.getOffset() + 1L);
                     IDInstruction var39 = this.ctx.createAssign(this.ctx.getVar(var25), this.ctx.getVar(var23)).withOffset(0L);
                     this.cfg.get(0).add(0, var39);
                  }

                  var19++;
               }
            }
         }
      }
   }
}
