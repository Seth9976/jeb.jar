package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class bqv {
   private static final ILogger pC = GlobalLog.getLogger(bqv.class);
   private IDMethodContext A;
   private IDMethodContext kS;
   private CFG wS;
   private IDTryData UT;
   private Boolean E;
   private long sY;
   private Set ys;
   private int ld = 0;
   private int gp = 0;
   private int oT = 0;
   private Deque fI = new ArrayDeque();

   public bqv(IDMethodContext var1) {
      this.A = var1;
   }

   public IDMethodContext pC() {
      if (this.E != null && this.E) {
         return this.kS;
      } else {
         throw new IllegalStateException();
      }
   }

   public boolean pC(long var1, Set var3) {
      if (this.E != null) {
         throw new IllegalStateException();
      } else {
         this.kS = this.A.copy();
         this.wS = this.kS.getCfg();
         this.UT = this.kS.getExceptionData();
         this.E = this.A(var1, var3);
         return this.E;
      }
   }

   private boolean A(long var1, Set var3) {
      Couple var4 = this.wS.getInstructionLocation(var1);
      if (var4 == null) {
         throw new RuntimeException("Cannot locate root instruction");
      } else {
         BasicBlock var5 = (BasicBlock)var4.getFirst();
         int var6 = (Integer)var4.getSecond();
         if (var5.size() > 1) {
            var5 = DUtil.splitBlock(this.kS, var5, var6);
            this.ld++;
         }

         this.sY = var5.getBase();
         IDInstruction var7 = (IDInstruction)var5.get(0);
         List var8 = var7.getUsedVariables();
         LinkedHashSet var9 = new LinkedHashSet();
         var8.forEach(var1x -> var9.add(var1x.getId()));
         if (var3 == null) {
            var3 = var9;
         } else if (!var9.containsAll((Collection)var3)) {
            throw new IllegalArgumentException("The selected varids (use-set) contains variables unused by the root instruction");
         }

         this.ys = new HashSet((Collection)var3);
         this.A();
         return this.kS();
      }
   }

   private boolean A() {
      BasicBlock var1 = this.wS.getBlockAt(this.sY);
      int var2 = 0;

      for (int var3 = -1; var3 != 0; var2++) {
         for (BasicBlock var5 : var1.getInputBlocks()) {
            this.fI.add(new bqv.Av(var5, this.ys, Sets.newHashSet(this.sY)));
         }

         var3 = 0;

         while (!this.fI.isEmpty()) {
            bqv.Av var6 = (bqv.Av)this.fI.remove();
            Object[] var10000 = new Object[]{var2, var6};
            int var7 = this.pC(var6.pC, var6.A, var6.kS, true);
            if (var7 > 0) {
               var3++;
            }
         }
      }

      return false;
   }

   private int pC(BasicBlock var1, Set var2, Set var3, boolean var4) {
      if (var2.isEmpty()) {
         return 0;
      } else {
         long var5 = var1.getBase();
         if (!var3.add(var5)) {
            if (var5 == this.sY) {
               return 0;
            } else {
               Object[] var30 = new Object[0];
               return -2;
            }
         } else if (var1.outsize() != 1) {
            Object[] var29 = new Object[0];
            return -1;
         } else {
            for (int var7 = var1.size() - 1; var7 >= 0; var7--) {
               IDInstruction var8 = (IDInstruction)var1.get(var7);
               IDVar var9 = var8.getDefinedVariable();
               if (var9 != null) {
                  int var10 = var9.getId();
                  if (var2.remove(var10)) {
                     List var11 = var8.getUsedVariables();
                     var11.forEach(var1x -> var2.add(var1x.getId()));
                     if (var2.isEmpty()) {
                        return 0;
                     }
                  }
               }
            }

            BasicBlock var19 = var1.getOutputBlock(0);
            int var20 = (int)var19.getBase();
            int var21 = var1.insize();
            if (var21 == 0) {
               return 0;
            } else if (var21 == 1) {
               this.fI.add(new bqv.Av((BasicBlock)var1.getInputBlocks().get(0), var2, var3));
               return 0;
            } else if (!var4) {
               return 0;
            } else {
               Assert.a(var21 >= 2);
               int var22 = 0;

               for (BasicBlock var12 : var1.getInputBlocks()) {
                  Object[] var10000 = new Object[]{var12};
                  this.fI.add(new bqv.Av(var12, var2, var3));
                  if (var12.getEndAddress() != var5 && var22 + 1 != var21) {
                     var10000 = new Object[]{var1};
                     long var13 = this.wS.getEndAddress();
                     BasicBlock var15 = bpl.pC(var1, var13);
                     this.wS.addBlock(var15);
                     var22++;
                     if (((IDInstruction)var15.getLast()).isJump()) {
                        ((IDInstruction)var15.getLast()).setBranchTarget(var20);
                     } else {
                        long var16 = var15.getEndAddress();
                        IDInstruction var18 = this.kS.createJump(var20);
                        var18.setOffset(var16);
                        var18.getSize();
                        var15.add(var18);
                     }

                     switch (((IDInstruction)var12.getLast()).getOpcode()) {
                        case IR_JUMP:
                           IDInstruction var24 = ((IDInstruction)var12.getLast()).duplicate();
                           var24.setBranchTarget((int)var13);
                           var12.set(var12.size() - 1, var24);
                           break;
                        case IR_JCOND:
                           IDInstruction var17 = ((IDInstruction)var12.getLast()).duplicate();
                           var17.setBranchTarget((int)var13);
                           var12.set(var12.size() - 1, var17);
                           break;
                        case IR_SWITCH:
                           IDInstruction var27 = ((IDInstruction)var12.getLast()).duplicate();
                           var27.getSwitchData().updateCases((int)var5, (int)var13);
                           var12.set(var12.size() - 1, var27);
                           break;
                        default:
                           throw new RuntimeException();
                     }

                     this.wS.addEdge(var15, var19);
                     this.wS.reconnectEdge(var12, var1, var15);

                     for (BasicBlock var26 : var1.getIrregularOutputBlocks()) {
                        this.wS.addIrregularEdge(var15, var26);
                     }

                     this.UT.copyProtectedBlock((int)var1.getBase(), (int)var15.getBase());
                  }
               }

               this.gp++;
               return 1;
            }
         }
      }
   }

   private boolean kS() {
      boolean var1 = true;
      if (var1) {
         IDMasterOptimizer var2 = this.kS.getGlobalContext().createMasterOptimizer(this.kS, false, true, false);
         var2.setSafeMode(true);
         var2.add(new cab());
         int var3 = var2.perform();
         if (var3 > 0) {
            this.wS = this.kS.getCfg();
            this.UT = this.kS.getExceptionData();
         }
      }

      BasicBlock var5 = this.wS.getBlockAt(this.sY);

      for (BasicBlock var4 : var5.getInputBlocks()) {
         if (!this.pC(var4, new HashSet(this.ys), new HashSet())) {
            Object[] var10000 = new Object[]{var4};
            return false;
         }
      }

      return true;
   }

   private boolean pC(BasicBlock var1, Set var2, Set var3) {
      if (var2.isEmpty() || var1.getBase() == this.sY) {
         return true;
      } else if (!var3.add(var1.getBase())) {
         return false;
      } else if (var1.outsize() == 1) {
         for (int var24 = var1.size() - 1; var24 >= 0; var24--) {
            IDInstruction var26 = (IDInstruction)var1.get(var24);
            IDVar var29 = var26.getDefinedVariable();
            if (var29 != null) {
               int var31 = var29.getId();
               if (var2.remove(var31)) {
                  List var8 = var26.getUsedVariables();
                  var8.forEach(var1x -> var2.add(var1x.getId()));
                  if (var2.isEmpty()) {
                     break;
                  }
               }
            }
         }

         if (var1.insize() == 1) {
            return this.pC(var1.getInputBlock(0), var2, var3);
         } else {
            if (var1.irrinsize() == 1) {
               var1 = var1.getIrregularInputBlock(0);

               int var25;
               for (var25 = 0; var25 < var1.size(); var25++) {
                  IDInstruction var27 = (IDInstruction)var1.get(var25);
                  if (var27.canThrow()) {
                     break;
                  }
               }

               for (int var28 = var25 - 1; var28 >= 0; var28--) {
                  IDInstruction var30 = (IDInstruction)var1.get(var28);
                  IDVar var32 = var30.getDefinedVariable();
                  if (var32 != null) {
                     int var33 = var32.getId();
                     if (var2.remove(var33)) {
                        List var34 = var30.getUsedVariables();
                        var34.forEach(var1x -> var2.add(var1x.getId()));
                        if (var2.isEmpty()) {
                           break;
                        }
                     }
                  }
               }
            }

            return var2.isEmpty();
         }
      } else if (var1.outsize() != 2) {
         return false;
      } else if (!((IDInstruction)var1.getLast()).isJcond()) {
         return false;
      } else if (var2.size() != 1) {
         return false;
      } else {
         int var4 = (Integer)var2.iterator().next();
         IDFA var5 = this.wS.doDataFlowAnalysis();
         Collection var6 = var5.getReachChains(var1, var1.size(), var4, 2);
         if (var6.size() != 1) {
            return false;
         } else {
            long var7 = (Long)var6.iterator().next();
            IDInstruction var9 = (IDInstruction)this.wS.getInstruction(var7);
            if (var9.isAssignToVar(var4) && var9.getAssignSource() instanceof IDImm) {
               IDInstruction var11 = (IDInstruction)var1.getLast();
               if (var11.getVarIds().contains(var4)) {
                  return false;
               } else {
                  long var12 = this.wS.getEndAddress();
                  IDInstruction var14 = this.kS.createJump((int)var12);
                  var14.copyBaseFields(var11);
                  var1.set(var1.size() - 1, var14);
                  BasicBlock var15 = new BasicBlock();
                  IDInstruction var16 = var11.duplicate().withSize(1).withOffset(var12);
                  var16.setBranchTarget((int)var12 + 3);
                  var15.add(var16);
                  this.wS.addBlock(var15);
                  BasicBlock var17 = new BasicBlock();
                  var17.add(var9.duplicate().withSize(1).withOffset(var12 + 1L));
                  var17.add(this.kS.createJump((int)var11.getOffsetEnd()).withOffset(var12 + 2L));
                  this.wS.addBlock(var17);
                  BasicBlock var18 = new BasicBlock();
                  var18.add(var9.duplicate().withSize(1).withOffset(var12 + 3L));
                  var18.add(this.kS.createJump(var11.getBranchTarget()).withOffset(var12 + 4L));
                  this.wS.addBlock(var18);
                  this.wS.deleteOutEdges(var1);
                  this.wS.addEdge(var1, var15);
                  this.wS.addEdge(var15, var17);
                  this.wS.addEdge(var15, var18);
                  this.wS.addEdge(var17, this.wS.getBlockAt(var11.getOffsetEnd()));
                  this.wS.addEdge(var18, this.wS.getBlockAt((long)var11.getBranchTarget()));
                  this.oT++;

                  for (BasicBlock var20 : Arrays.asList(var15, var17, var18)) {
                     for (BasicBlock var22 : var1.getIrregularOutputBlocks()) {
                        this.wS.addIrregularEdge(var20, var22);
                     }

                     this.UT.copyProtectedBlock((int)var1.getBase(), (int)var20.getBase());
                  }

                  return true;
               }
            } else {
               return false;
            }
         }
      }
   }

   private static class Av {
      BasicBlock pC;
      Set A;
      Set kS = new HashSet();

      Av(BasicBlock var1, Set var2, Set var3) {
         this.pC = var1;
         this.A = new LinkedHashSet(var2);
         this.kS = new HashSet(var3);
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X", this.pC.getBase());
      }
   }
}
