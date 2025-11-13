package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class bvm {
   private CFG q;
   private Set RF;
   private Set xK;
   private Set Dw;
   private Set Uv;
   private Set oW;

   public bvm(CFG var1) {
      this.q = var1;
   }

   public Set q() {
      if (this.RF == null) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableSet(this.RF);
      }
   }

   public Set RF() {
      if (this.xK == null) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableSet(this.xK);
      }
   }

   public Set xK() {
      if (this.Dw == null) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableSet(this.Dw);
      }
   }

   public Set Dw() {
      if (this.Uv == null) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableSet(this.Uv);
      }
   }

   public Set Uv() {
      if (this.oW == null) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableSet(this.oW);
      }
   }

   public boolean q(BasicBlock var1) {
      return this.q(var1, null);
   }

   public boolean q(BasicBlock var1, Integer var2) {
      if (this.RF != null) {
         throw new IllegalStateException();
      } else if (!((IDInstruction)var1.getLast()).isJcond()) {
         throw new IllegalArgumentException();
      } else {
         HashSet var3 = null;
         HashSet var4 = null;
         boolean var5 = var2 != null;
         if (var5) {
            var3 = new HashSet();
            var4 = new HashSet();
            IDFA var6 = this.q.doDataFlowAnalysis();

            for (long var9 : var6.getUseDefs(var1.getLastAddress(), var2)) {
               if (this.q(var9, var2.intValue())) {
                  var3.add(this.q.getBlockContaining(var9).getBase());
               } else {
                  if (!this.RF(var9, var2)) {
                     return false;
                  }

                  var4.add(this.q.getBlockContaining(var9).getBase());
               }
            }

            if (var4.isEmpty()) {
               return false;
            }
         }

         HashSet var24 = new HashSet();
         BasicBlock var25 = var1.getOutputBlock(1);
         BasicBlock var26 = var1.getOutputBlock(0);

         for (int var27 = 0; var27 < 2; var27++) {
            boolean var10 = false;
            if (var27 == 1) {
               BasicBlock var11 = var25;
               var25 = var26;
               var26 = var11;
            }

            ArrayDeque var29 = new ArrayDeque();
            var29.add(var26);

            while (!var29.isEmpty()) {
               BasicBlock var12 = (BasicBlock)var29.remove();
               if (var12 != var25 && var24.add(var12.getBase())) {
                  if (var12 == var1) {
                     var10 = true;
                  }

                  var29.addAll(var12.getOutputs());
               }
            }

            if (var10) {
               break;
            }

            var24.clear();
         }

         if (var24.isEmpty()) {
            return false;
         } else {
            HashSet var28 = new HashSet();
            HashSet var30 = new HashSet();
            ArrayDeque var31 = new ArrayDeque();
            var31.add(var1);

            while (!var31.isEmpty()) {
               BasicBlock var13 = (BasicBlock)var31.remove();
               if (var30.add(var13.getBase()) && var24.contains(var13.getBase())) {
                  var28.add(var13.getBase());
                  var31.addAll(var13.getInputs());
               }
            }

            if (var5) {
               boolean var32 = false;

               for (long var15 : var28) {
                  BasicBlock var17 = this.q.getBlockAt(var15);
                  if (var3.contains(var17.getBase())) {
                     return false;
                  }

                  if (!var32 && var4.contains(var17.getBase())) {
                     var32 = true;
                  }
               }

               if (!var32) {
                  return false;
               }
            }

            BasicBlock var33 = null;
            HashSet var34 = new HashSet();
            HashSet var35 = new HashSet();
            HashSet var16 = new HashSet();
            HashSet var36 = new HashSet();

            for (long var19 : var28) {
               BasicBlock var21 = this.q.getBlockAt(var19);
               if (var21.irrinsize() > 0 || var21.irroutsize() > 0) {
                  return false;
               }

               for (BasicBlock var23 : var21.getInputs()) {
                  if (!var28.contains(var23.getBase())) {
                     if (var33 != null) {
                        if (var33 != var21) {
                           return false;
                        }
                     } else {
                        var33 = var21;
                        var34.add(var21.getBase());
                     }

                     var16.add(var23.getBase());
                  }
               }

               for (BasicBlock var38 : var21.getOutputs()) {
                  if (!var28.contains(var38.getBase())) {
                     var35.add(var21.getBase());
                     var36.add(var38.getBase());
                  }
               }
            }

            if (var16.isEmpty() && var28.contains(0L)) {
               var34.add(0L);
            }

            if (var34.size() != 1) {
               com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("LoopChecker: entry count should be 1"));
               return false;
            } else if (var28.isEmpty()) {
               com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("LoopChecker: empty loop"));
               return false;
            } else {
               this.RF = var28;
               this.xK = var34;
               this.Dw = var35;
               this.Uv = var16;
               this.oW = var36;
               return true;
            }
         }
      }
   }

   private boolean q(long var1, int var3) {
      if (var1 < 0L) {
         return false;
      } else {
         IDInstruction var4 = (IDInstruction)this.q.getInstructionAt(var1);
         return var4.isAssignToVar(var3) && var4.getAssignSource().isConstantImm();
      }
   }

   private boolean RF(long var1, int var3) {
      if (var1 < 0L) {
         return false;
      } else {
         IDInstruction var4 = (IDInstruction)this.q.getInstructionAt(var1);
         if (var4.isAssignToVar(var3) && var4.getAssignSource() instanceof IDOperation var5) {
            switch (var5.getOperatorType()) {
               case ADD:
               case SUB:
                  IDExpression var8 = var5.getOperand1();
                  IDExpression var7 = var5.getOperand2();
                  if (var8.isVar(var3) && var7 instanceof IDImm) {
                     return true;
                  }

                  if (var7.isVar(var3) && var8 instanceof IDImm) {
                     return true;
                  }
                  break;
               default:
                  return false;
            }
         }

         return false;
      }
   }
}
