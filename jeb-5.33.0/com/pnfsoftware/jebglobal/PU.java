package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import com.pnfsoftware.jeb.util.format.Strings;

public class PU {
   public static boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return var0 != null && var0.pC().isPCUpdated();
   }

   public static boolean A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return var0 != null && var0.pC().isPCUpdated() || var0.ld() || var0.ys();
   }

   public static boolean kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return Strings.isContainedIn(var0.getMnemonic(), "HVC", "SMC", "SVC");
   }

   public static boolean wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return var0 != null && pC(var0) && var0.UT().E();
   }

   public static boolean UT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return var0 != null && var0.pC().isPCUpdated() && var0.pC().getBranchType() == IInsnEmulator.BranchType.CALL;
   }

   public static boolean E(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return var0 != null && var0.pC().isPCUpdated() && var0.pC().getBranchType() == IInsnEmulator.BranchType.JMP;
   }

   public static boolean sY(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return A(var0, true)
         || UT(var0, true)
         || E(var0, true)
         || var0.wS().pC().equals("RET")
         || var0.wS().pC().equals("BR") && var0.A()[0].kS(var0.getProcessorMode());
   }

   public static boolean ys(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return var0.wS().pC().equals("RET");
   }

   public static boolean ld(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return var0.UT().E() && sY(var0);
   }

   public static com.pnfsoftware.jeb.corei.parsers.arm.rQ pC(INativeCodeAnalyzer var0, long var1) {
      return pC(var0, var1, 0);
   }

   public static com.pnfsoftware.jeb.corei.parsers.arm.rQ pC(INativeCodeAnalyzer var0, long var1, int var3) {
      INativeContinuousItem var4 = var0.getModel().getItemAt(var1);
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var5 = null;
      if (var4 instanceof INativeInstructionItem) {
         var5 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)((INativeInstructionItem)var4).getInstruction();
      }

      if (var5 == null || var3 != 0 && var5.getProcessorMode() != var3 && var5.getProcessorMode() == 16) {
         var5 = pC(var0.getProcessor(), var0.getMemory(), var1, var3);
      }

      return var5 != null && var3 != 0 && var5.getProcessorMode() != var3 ? null : var5;
   }

   public static com.pnfsoftware.jeb.corei.parsers.arm.rQ pC(IProcessor var0, IVirtualMemory var1, long var2, int var4) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var5;
      try {
         if (var0 instanceof com.pnfsoftware.jeb.corei.parsers.arm.zp) {
            var5 = ((com.pnfsoftware.jeb.corei.parsers.arm.zp)var0).pC(var1, var2, var4);
         } else {
            var5 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.parseAt(var1, var2);
         }
      } catch (ProcessorException var6) {
         return null;
      }

      return !gp(var5) ? null : var5;
   }

   public static boolean gp(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      return var0 != null && !var0.ys();
   }

   public static boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, boolean var1) {
      if (var0.wS().pC().equals("PUSH") && (var1 || !var0.UT().sY())) {
         Yg var2 = var0.A()[0];
         if (var2.getOperandType() == 7) {
            for (Yg var6 : var2.E()) {
               if (var6.kS(var0.getProcessorMode())) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, boolean var1) {
      if (var0.wS().pC().equals("POP") && (var1 || !var0.UT().sY())) {
         Yg var2 = var0.A()[0];
         if (var2.getOperandType() == 7) {
            for (Yg var6 : var2.E()) {
               if (var6.A(var0.getProcessorMode())) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, boolean var1) {
      if (var0.wS().pC().equals("POP") && (var1 || !var0.UT().sY())) {
         Yg var2 = var0.A()[0];
         if (var2.getOperandType() == 7) {
            for (Yg var6 : var2.E()) {
               if (var6.kS(var0.getProcessorMode())) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, boolean var1) {
      return !var0.wS().pC().equals("BX") || !var1 && var0.UT().sY() ? false : var0.A()[0].A(var0.getProcessorMode());
   }

   public static boolean UT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, boolean var1) {
      return !var0.wS().pC().equals("BX") || !var1 && var0.UT().sY() ? false : var0.A()[0].kS(var0.getProcessorMode());
   }

   public static boolean E(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, boolean var1) {
      return var0.wS().pC().equals("MOV") && (var1 || !var0.UT().sY())
         ? var0.A()[0].A(var0.getProcessorMode()) && var0.A()[1].kS(var0.getProcessorMode())
         : false;
   }

   public static boolean pC(int var0) {
      return var0 == 8 || var0 == 12 || var0 == 10 || var0 == 2 || var0 == 5;
   }

   public static boolean A(int var0) {
      return var0 == 9 || var0 == 13 || var0 == 11 || var0 == 3 || var0 == 4;
   }

   public static boolean kS(int var0) {
      return var0 == 9 || var0 == 13;
   }

   public static boolean wS(int var0) {
      return var0 == 8 || var0 == 12;
   }

   public static boolean UT(int var0) {
      return var0 == 11 || var0 == 3 || var0 == 4;
   }
}
