package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class akt {
   private static final ILogger pC = GlobalLog.getLogger(akt.class);

   public static boolean pC(IERoutineContext var0, BasicBlock var1) {
      for (BasicBlock var3 : var1.getAllInputs()) {
         IEStatement var4 = (IEStatement)var3.getLast();
         if (EUtil.isPCAssign(var4) || var4.isJumpFar()) {
            return false;
         }
      }

      return true;
   }

   public static int pC(IERoutineContext var0, BasicBlock var1, int var2) {
      return pC(var0, var1, var2, false);
   }

   public static int pC(IERoutineContext var0, BasicBlock var1, int var2, boolean var3) {
      IEStatement var4 = (IEStatement)var1.get(var2);
      if (var4 == null) {
         throw new RuntimeException("Cannot remove null instruction");
      } else if (var1.isEmpty()) {
         throw new RuntimeException("Empty basic bloc!");
      } else if (var1.size() == 1 || var2 == 0 && !var3 || var2 == 0 && !pC(var0, var1)) {
         if (var4.isNop()) {
            return 0;
         } else {
            IENop var16 = var0.createNop(var4);
            var1.set(0, var16);
            return -1;
         }
      } else {
         int var5 = var4.getSize();
         IEStatement var6;
         IEStatement var7;
         if (var2 == 0) {
            var6 = (IEStatement)var1.get(1);
            BasicBlock var8 = var0.getCfg().getBlockEndingAt(var1.getBase());
            var7 = var8 != null ? (IEStatement)var8.getLast() : null;
         } else {
            var6 = (IEStatement)var1.get(var2 - 1);
            var7 = var6;
         }

         if (var7 instanceof IECall) {
            IECall var17 = var7.asCall();
            IEGeneric var9 = var17.getReturnLocation();
            if (var9 != null) {
               if (!(var9 instanceof IEImm)) {
                  if (var4.isNop()) {
                     return 0;
                  }

                  IENop var18 = var0.createNop(var4);
                  var1.set(var2, var18);
                  return -1;
               }

               if (var2 + 1 < var1.size()) {
                  IEImm var10 = var9.asImm();
                  if (var10.canReadAsAddress()) {
                     long var11 = var10.getValueAsAddress();
                     if (var4.getLowerLevelAddresses().contains(var11)) {
                        IEStatement var13 = (IEStatement)var1.get(var2 + 1);
                        Long var14 = var13.getPrimaryLowerLevelAddress();
                        if (var14 != null) {
                           IEImm var15 = EUtil.imm(var14, var9.getBitsize());
                           var17.setReturnLocation(var15);
                        }
                     }
                  }
               }
            }
         }

         var6.adjustSize(var5);
         var1.remove(var2);
         Assert.a(!var1.isEmpty());
         return 1;
      }
   }

   public static void pC(IERoutineContext var0, IBasicBlock var1, int var2, int var3) {
      IEStatement var4 = (IEStatement)var1.get(var2 == var1.size() ? var2 - 1 : var2);
      int var5 = (int)(var1.getEndAddress() - var1.getBase() - var1.size());
      int var6 = var4.getSize() + var3 - var5;
      EUtil.expandStatementSize(var0, var4, var6);
   }

   public static boolean pC(IERoutineContext var0, BasicBlock var1, int var2, List var3) throws JebException {
      if (!pC(var1, var3.size())) {
         pC(var0, var1, var2, var3.size());
         throw new JebException("Size adjusted, cfg was modified");
      } else {
         int var4 = var3.size();
         List var5 = var1.getInstructions();
         if (var2 != 0) {
            for (int var6 = var2 - 1; var6 >= 0 && var4 > 0; var6--) {
               IEStatement var7 = (IEStatement)var5.get(var6);
               int var8 = var7.getSize();
               if (var8 > 1) {
                  int var9 = var8 - var4 >= 1 ? var4 : var8 - 1;
                  var4 -= var9;
                  var7.adjustSize(-var9);
               }
            }
         }

         if (var4 > 0 && var2 < var5.size()) {
            for (int var10 = var2; var10 < var5.size() && var4 > 0; var10++) {
               IEStatement var12 = (IEStatement)var5.get(var10);
               int var14 = var12.getSize();
               if (var14 > 1) {
                  int var15 = var14 - var4 >= 1 ? var4 : var14 - 1;
                  var4 -= var15;
                  var12.adjustSize(-var15);
               }
            }
         }

         for (int var11 = 0; var11 < var3.size(); var11++) {
            IEStatement var13 = (IEStatement)var3.get(var11);
            var13.setSize(1);
            var1.add(var2 + var11, var13);
         }

         return true;
      }
   }

   public static boolean pC(IBasicBlock var0, int var1) {
      return var1 + var0.size() <= var0.getEndAddress() - var0.getBase();
   }

   public static boolean pC(IEGeneric var0, IEGeneric var1) {
      return var0 == null ? var1 == null : var0.equalsEx(var1, false);
   }

   public static boolean pC(CFG var0, BasicBlock var1) {
      if (var1.size() != 1 || !EUtil.isJump((IEGeneric)var1.get(0))) {
         return false;
      } else if (var1.allinsize() != 0) {
         return false;
      } else {
         BasicBlock var2 = null;
         long var3 = var1.getFirstAddress();

         for (BasicBlock var6 : var0) {
            if (var6 != var1 && var6.size() >= 1 && var6.getEndAddress() == var3) {
               var2 = var6;
               break;
            }
         }

         if (var2 == null) {
            return false;
         } else {
            int var7 = ((IEStatement)var1.get(0)).getSize();
            var0.removeBlock(var1);
            ((IEStatement)var2.getLast()).adjustSize(var7);
            return true;
         }
      }
   }

   public static int pC(CFG var0, boolean var1, boolean var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var0.size() - 1; var4++) {
         BasicBlock var5 = var0.get(var4);
         if (((IEStatement)var5.getLast()).isJump()) {
            IEJump var6 = ((IEStatement)var5.getLast()).asJump();
            IEGeneric var7 = var6.getCondition();
            if (!var1 || var7 != null) {
               BasicBlock var8 = var0.get(var4 + 1);
               if (var6.getBranchAddress() == var8.getBase()) {
                  IERoutineContext var9 = var6.getContext();
                  Object var10;
                  if (var2 || var7 != null && !EUtil.hasNoSideEffect(var7)) {
                     IEVar var11 = var9.getGlobalContext().createVirtualRegister("oldJumpCondition" + var7.getBitsize(), var7.getBitsize());
                     var10 = var9.createAssign(var11, var7);
                     ((IEStatement)var10).copyProperties(var6);
                  } else {
                     var10 = var9.createNop(var6);
                  }

                  var5.set(var5.size() - 1, (IInstruction)var10);
                  var0.deleteOutEdges(var5);
                  var0.addEdge(var5, var8);
                  var3++;
               }
            }
         }
      }

      return var3;
   }

   public static int A(CFG var0, boolean var1, boolean var2) {
      int var3 = 0;
      if (var1) {
         var3 += var0.removeUnreachableBlocks();
      }

      if (var2) {
         var3 += pC(var0, true, false);
      }

      return var3;
   }

   public static int pC(CFG var0) {
      return A(var0, true, true);
   }

   public static IEMem pC(IERoutineContext var0, IEMem var1, IEMem var2) {
      IEGeneric var3 = var1.getReference();
      IEGeneric var4 = var2.getReference();
      IEGeneric var5 = var3;
      IEGeneric var6 = var4;
      BigInteger var7 = BigInteger.ZERO;
      BigInteger var8 = BigInteger.ZERO;
      int var9 = var1.getBitsize() >>> 3;
      if (EUtil.isOperation(var4, OperationType.ADD, OperationType.SUB)) {
         IEGeneric var10 = ((IEOperation)var4).getOperand2();
         if (var10 instanceof IEImm) {
            var6 = ((IEOperation)var4).getOperand1();
            var8 = ((IEOperation)var4).getOperationType() == OperationType.ADD ? ((IEImm)var10).getValue() : ((IEImm)var10).getValue().negate();
         }

         if (var8.equals(BigInteger.valueOf(var9)) && var3.equals(var6)) {
            return var0.createMem(var3, var1.getBitsize() + var2.getBitsize());
         }
      }

      if (EUtil.isOperation(var3, OperationType.ADD, OperationType.SUB)) {
         IEGeneric var11 = ((IEOperation)var3).getOperand2();
         if (var11 instanceof IEImm) {
            var5 = ((IEOperation)var3).getOperand1();
            var7 = ((IEOperation)var3).getOperationType() == OperationType.ADD ? ((IEImm)var11).getValue() : ((IEImm)var11).getValue().negate();
         }
      }

      return var8.subtract(var7).equals(BigInteger.valueOf(var9)) && var5.equals(var6) ? var0.createMem(var3, var1.getBitsize() + var2.getBitsize()) : null;
   }

   public static boolean pC(IESwitch var0, CFG var1, BasicBlock var2, BasicBlock var3, BasicBlock var4) {
      boolean var5 = false;
      int var6 = (int)var3.getBase();
      int var7 = (int)var4.getBase();
      if (var0.getDefaultAddress() == var6) {
         var0.setDefaultAddress(var7);
         var5 = true;
      }

      if (var0.isPossibleTarget(var6)) {
         ArrayList var8 = new ArrayList();

         for (Couple var10 : var0.getCases()) {
            if ((Integer)var10.getSecond() == var6) {
               var8.add((IEGeneric)var10.getFirst());
            }
         }

         for (IEGeneric var12 : var8) {
            var0.removeCase(var12);
            var0.addCase(var12, var7);
            var5 = true;
         }
      }

      if (!var2.getOutputOffsets().contains((long)var7)) {
         if (var1.reconnectEdge(var2, var3, var4) != 1) {
            throw new RuntimeException("Edge reconnect failed");
         }
      } else {
         var1.deleteEdge(var2, var3);
      }

      return var5;
   }

   public static int pC(IERoutineContext var0) {
      CFG var1 = var0.getCfg();
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         BasicBlock var4 = var1.get(var3);
         if (var4.outsize() == 0) {
            AddressableInstruction var5 = var4.getLast2();
            IEStatement var6 = (IEStatement)var5.getInstruction();
            if (EUtil.isPCAssign(var6) && ((IEAssign)var6).getSrcOperand() instanceof IEImm var8) {
               long var9 = var8.getValueAsAddress();
               Long var11 = var0.convertNativeAddress(var9);
               if (var11 != null) {
                  BasicBlock var12 = var1.getBlockAt(var11);
                  if (var12 != null && var1.addEdge(var4, var12)) {
                     var2++;
                  }
               }
            }
         }
      }

      return var2;
   }
}
