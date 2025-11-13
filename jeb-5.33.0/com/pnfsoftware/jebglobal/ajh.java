package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.DecompilerException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class ajh {
   private static final ILogger pC = GlobalLog.getLogger(ajh.class);
   private IERoutineContext A;
   private INativeMethodItem kS;

   public ajh(IERoutineContext var1) {
      this.A = var1;
      this.kS = var1.getRoutine();
   }

   public void pC() {
      List var1 = this.A.getStatements();
      TreeMap var2 = new TreeMap();
      int var3 = 0;

      for (IEStatement var5 : var1) {
         var2.put(var3, var5);
         var3 += var5.getSize();
      }

      IEStatement var10 = (IEStatement)var1.get(0);
      if (var10.isAssign() && var10.asAssign().isBreakingFlow()) {
         IEAssign var11 = var10.asAssign();
         if (!var11.getBreakingFlow(0L).isBrokenUnknown() && var11.getLeftOperand() == this.A.getProgramCounter() && var11.getRightOperand() instanceof IEImm) {
            long var6 = ((IEImm)var11.getRightOperand()).getValueAsAddress();
            Long var8 = this.A.convertNativeAddress(var6);
            if (var8 != null) {
               IEJump var9 = this.A.createJump(var8.intValue());
               var9.copyProperties(var11);
               var1.set(0, var9);
            }
         }
      }

      int var12 = this.pC((SortedMap)var2);
      Object[] var10000 = new Object[]{var12};
      var12 = this.pC((Map)var2);
      var10000 = new Object[]{var12};
      this.A.invalidateDataFlowAnalysis();
   }

   private int pC(SortedMap var1) {
      int var2 = 0;

      for (Entry var4 : var1.entrySet()) {
         int var5 = (Integer)var4.getKey();
         IEStatement var6 = (IEStatement)var4.getValue();
         if (var6 instanceof IEAssign && ((IEAssign)var6).isBreakingFlow()) {
            IEAssign var7 = (IEAssign)var6;
            if (var7.getBreakingFlow(var5).isBrokenUnknown()) {
               long var8 = this.A.convertIntermediateOffset(var5);
               if (!this.pC(var8, var1)) {
                  ArrayList var10 = new ArrayList();

                  for (IBranchTarget var12 : var7.getBranchDetails(true).getDynamicTargetCandidates()) {
                     if (var12.isInternal()) {
                        var10.add(var12.getInternalAddress().getAddress());
                     }
                  }

                  int var20 = var5 + var7.getSize();
                  Long var21 = this.A.convertIntermediateOffset(var20);
                  CFG var13 = this.kS.getData().getCFG();
                  BasicBlock var14 = var13.getBlockContaining(var8);

                  for (BasicBlock var16 : var14.getOutputs()) {
                     long var17 = var16.getFirstAddress();
                     if (!var10.contains(var17)) {
                        int var19 = var21 != null && var17 == var21 ? 0 : -1;
                        var7.getBranchDetails(true).addCandidate(var19, new BranchTarget(new CodePointer(var17)));
                        Object[] var10000 = new Object[]{var8, var5, var7, var17};
                        var2++;
                     }
                  }

                  if (var14.hasUnknownDst()) {
                     var7.getBranchDetails(true).setIncludeUnknownTarget(true);
                  }
               }
            }
         }
      }

      return var2;
   }

   private boolean pC(long var1, Map var3) {
      Long var4 = this.A.convertNativeAddress(var1);
      if (var4 == null) {
         return false;
      } else {
         int var5 = var4.intValue();
         int var6 = 0;

         while (true) {
            Long var7 = this.A.convertIntermediateOffset(var5);
            if (var7 == null || var7 != var1) {
               return false;
            }

            IEStatement var8 = (IEStatement)var3.get(var5);
            if (var8.getBreakingFlow(var5).isBroken()) {
               if (++var6 >= 2) {
                  return true;
               }
            }

            var5 += var8.getSize();
         }
      }
   }

   private int pC(Map var1) {
      int var2 = 0;

      for (BasicBlock var4 : this.kS.getData().getCFG().getBlocksView()) {
         if (var4.outsize() != 0) {
            AddressableInstruction var5 = var4.getBranchingInstruction2(true, false);
            if (var5 != null) {
               int var6 = this.pC(var4, var5);
               Object[] var10000 = new Object[]{var4.getBase(), var5, var6};
               if (var6 > 0) {
                  long var7 = var5.getOffset();
                  Long var9 = this.A.convertNativeAddress(var7);
                  if (var9 != null) {
                     ArrayList var10 = new ArrayList();
                     ArrayList var11 = new ArrayList();
                     Integer var12 = this.pC(var1, var9.intValue(), var7, var10, var11);
                     if (var12 != null) {
                        var10000 = new Object[]{Longs.formatHexCollection(var11)};
                        IEStatement var13 = (IEStatement)var1.get(var12);
                        if (var13 instanceof IEAssign) {
                           IEAssign var14 = var13.asAssign();
                           ArrayList var15 = new ArrayList();

                           for (IBranchTarget var17 : var14.getBranchDetails(true).getDynamicTargetCandidates()) {
                              if (var17.isInternal()) {
                                 var15.add(var17.getInternalAddress().getAddress());
                              }
                           }

                           int var23 = var12 + var14.getSize();
                           Long var24 = this.A.convertIntermediateOffset(var23);

                           for (BasicBlock var19 : var4.getOutputs()) {
                              long var20 = var19.getBase();
                              if (!var15.contains(var20) && !var11.contains(var20)) {
                                 int var22 = var24 != null && var20 == var24 ? 0 : -1;
                                 var14.getBranchDetails(true).addCandidate(var22, new BranchTarget(new CodePointer(var20)));
                                 var10000 = new Object[]{var14, var20};
                                 var2++;
                              }
                           }

                           if (var4.hasUnknownDst()) {
                              var14.getBranchDetails(true).setIncludeUnknownTarget(true);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   private Integer pC(Map var1, int var2, long var3, List var5, List var6) {
      Integer var7 = null;

      for (Long var8 = this.A.convertIntermediateOffset(var2); var8 != null && var8 == var3; var8 = this.A.convertIntermediateOffset(var2)) {
         IEStatement var9 = (IEStatement)var1.get(var2);
         if (var9 == null) {
            return null;
         }

         IFlowInformation var10 = var9.getBreakingFlow(var2);
         if (var10.isBrokenUnknown()) {
            if (var7 != null) {
               String var11 = "Native insn translated to IR with multiple flow-breakers to unknown targets at IRd";
               pC.warn(var11);
               acj.pC(new DecompilerException(var11), this.A);
               return null;
            }

            var7 = var2;
         } else if (var10.isBroken()) {
            for (ICodePointer var12 : var10.getTargets()) {
               long var13 = var12.getAddress();
               Long var15 = this.A.convertIntermediateOffset((int)var13);
               if (var15 != null) {
                  var5.add(var13);
                  var6.add(var15);
               }
            }
         }

         var2 += var9.getSize();
      }

      return var7;
   }

   public int pC(BasicBlock var1, AddressableInstruction var2) {
      IFlowInformation var3 = var2.getBreakingFlow();
      if (var3.isBroken()) {
         LinkedHashSet var11 = new LinkedHashSet();
         boolean var5 = false;

         for (ICodePointer var7 : var3.getTargets()) {
            if (var7.isUnknownAddress()) {
               var5 = true;
            } else {
               var11.add(var7.getAddress());
            }
         }

         if (var5 && !var1.hasUnknownDst()) {
            String var12 = Strings.ff("Inconsistency! Unknown targets but bb does not have flag set: 0x%X", var1.getBase());
            Object[] var15 = new Object[0];
            acj.pC(new RuntimeException(var12), this.A);
         }

         HashSet var13 = new HashSet();

         for (BasicBlock var8 : var1.getOutputs()) {
            long var9 = var8.getBase();
            if (!var11.contains(var9)) {
               var13.add(var9);
            } else {
               var11.remove(var9);
            }
         }

         if (var11.isEmpty()) {
            return var13.isEmpty() ? 0 : 1;
         } else {
            return var13.isEmpty() ? 2 : 3;
         }
      } else {
         if (var1.outsize() != 1 || var1.getOutputBlock(0).getBase() != var1.getEndAddress()) {
            String var4 = Strings.ff(
               "UNEXPECTED! Instruction reporting as non-flow breaker: %s\nFlow info: %s\nBlock outputs: %s",
               var2,
               var3,
               Longs.formatHexCollection(var1.getOutputOffsets())
            );
            Object[] var10000 = new Object[0];
            acj.pC(new RuntimeException(var4), this.A);
         }

         return -1;
      }
   }
}
