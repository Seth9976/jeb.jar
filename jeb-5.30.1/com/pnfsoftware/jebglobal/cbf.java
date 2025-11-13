package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class cbf extends AbstractDOptimizer {
   public static final int q = 10;
   public static final int RF = 10;
   public static final int xK = 1;
   private int Dw;
   private int Uv;
   private int oW;
   private final boolean gO = true;
   private final int nf = 100;

   public cbf() {
      this(10, 10, 1);
   }

   public cbf(int var1, int var2, int var3) {
      this.q(var1, var2, var3);
   }

   public void q(int var1, int var2, int var3) {
      this.Dw = var1;
      this.Uv = var2;
      this.oW = var3;
   }

   public int[] q() {
      return new int[]{this.Dw, this.Uv, this.oW};
   }

   @Override
   public int perform() {
      for (BasicBlock var2 : this.cfg) {
         for (int var3 = 0; var3 < var2.size(); var3++) {
            IDInstruction var4 = (IDInstruction)var2.get(var3);
            if (var4.isAssign() && var4.getAssignDestination() instanceof IDArrayElt && this.q(var2, var3, var4)) {
               return 1;
            }
         }
      }

      return 0;
   }

   public boolean q(BasicBlock var1, int var2, IDInstruction var3) {
      IDArrayElt var4 = var3.getAssignDestination().asArrayElt();
      Set var5 = var4.getIndex().getVarIds();
      if (var5.size() == 1) {
         int var6 = (Integer)var5.iterator().next();
         IDVar var7 = this.ctx.getVar(var6);
         IDInstruction var8 = DUtil.nextInstruction(this.cfg, var1, var2);
         if (var8 != null && var8.isAssignToVar(var6)) {
            IDExpression var9 = var8.getAssignSource();
            if (var9.getVarIds().contains(var6) && !DUtil.hasInvokeInfo(var9) && !DUtil.usesReferences(var9)) {
               this.analyzeChains();
               ArrayList var10 = new ArrayList(this.dfa.getUseDefs(var3.getOffset(), var6));
               if (var10.size() == 2) {
                  IDInstruction var11 = null;
                  IDInstruction var12 = (IDInstruction)this.cfg.getInstruction((Long)var10.get(0));
                  IDInstruction var13 = (IDInstruction)this.cfg.getInstruction((Long)var10.get(1));
                  if (var12 == var8 && var13 != null) {
                     var11 = var13;
                  } else if (var13 == var8 && var12 != null) {
                     var11 = var12;
                  }

                  if (var11 != null && var11.isAssignToVar(var6) && var11.getAssignSource().isConstantImm()) {
                     IDInstruction var14 = null;
                     long[] var15 = new long[]{var11.getOffset(), var8.getOffset()};

                     label48:
                     for (long var19 : var15) {
                        for (long var23 : this.dfa.getDefUses(var19, var6)) {
                           IDInstruction var25 = (IDInstruction)this.cfg.getInstructionAt(var23);
                           if (var25 != null && var25.isJcond()) {
                              var14 = var25;
                              break label48;
                           }
                        }
                     }

                     if (var14 != null && this.q(var7, var11, var14)) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private boolean q(IDVar var1, IDInstruction var2, IDInstruction var3) {
      bvm var4 = new bvm(this.cfg);
      if (!var4.q(this.cfg.getBlockContaining(var3.getOffset()), var1.getId())) {
         return false;
      } else if (var4.Uv().size() != 1) {
         return false;
      } else {
         BasicBlock var5 = this.cfg.getBlockAt((Long)var4.Uv().iterator().next());
         boolean var6 = false;

         label74:
         for (long var8 : var4.q()) {
            BasicBlock var10 = this.cfg.getBlockAt(var8);
            Iterator var11 = var10.iterator();

            while (true) {
               if (!var11.hasNext()) {
                  continue label74;
               }

               IDInstruction var12 = (IDInstruction)var11.next();
               if (!var12.isJumpOrJcond()) {
                  if (!var12.isAssign()) {
                     break;
                  }

                  IDExpression var13 = var12.getAssignDestination();
                  if (var13 != var1) {
                     if (!(var13 instanceof IDArrayElt var14)
                        || !(var14.getArray() instanceof IDNewArrayInfo var15)
                        || var14.getIndex() != var1
                        || !var15.getType().isArray()
                        || !var15.getType().getArrayTypeDimensionBelow().isPrimitive()) {
                        break;
                     }

                     var6 = true;
                  }
               }
            }

            var6 = false;
         }

         if (var6) {
            this.Dw *= 100;
            this.Uv *= 100;
            this.oW *= 100;
         }

         long var18 = var2.getOffset();
         IDInstruction var19 = (IDInstruction)var5.get(0);
         long var9 = var19.getOffset();
         List var20 = DUtil.unroll(this.ctx, var1, var18, var9, this.Dw, this.Uv, this.oW);
         if (var20 == null) {
            return false;
         } else {
            Couple var21 = this.cfg.getInstructionLocation(var18);
            BasicBlock var22 = (BasicBlock)var21.getFirst();
            int var23 = (Integer)var21.getSecond();
            if (var23 != var22.size() - 1) {
               DUtil.splitBlock(this.ctx, var22, var23 + 1);
            }

            long var24 = (int)this.cfg.getEndAddress();

            for (int var17 = 0; var17 < var20.size(); var17++) {
               var20.set(var17, ((IDInstruction)var20.get(var17)).duplicateWithOffsetAndSize(var24, 1));
               var24++;
            }

            var20.add(this.ctx.createJump((int)var9).withOffset(var24));
            var2.transformToJump((int)this.cfg.getEndAddress());
            BasicBlock var25 = new BasicBlock(var20);
            this.cfg.addBlock(var25);
            this.ctx.getExceptionData().copyProtectedBlock((int)this.cfg.getBlockContaining(var2.getOffset()).getBase(), (int)var25.getBase());
            this.cfg.reconnectEdge(var22, var22.getOutputBlock(0), var25);
            this.cfg.addEdge(var25, var5);
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
            return true;
         }
      }
   }
}
