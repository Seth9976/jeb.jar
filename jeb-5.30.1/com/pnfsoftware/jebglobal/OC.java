package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import com.pnfsoftware.jeb.util.format.Strings;

public class OC {
   public static boolean q(fA var0) {
      return var0 != null && var0.q().isPCUpdated();
   }

   public static boolean RF(fA var0) {
      return var0 != null && var0.q().isPCUpdated() || var0.gP() || var0.nf();
   }

   public static boolean xK(fA var0) {
      return Strings.isContainedIn(var0.getMnemonic(), "HVC", "SMC", "SVC");
   }

   public static boolean Dw(fA var0) {
      return var0 != null && q(var0) && var0.Uv().oW();
   }

   public static boolean Uv(fA var0) {
      return var0 != null && var0.q().isPCUpdated() && var0.q().getBranchType() == IInsnEmulator.BranchType.CALL;
   }

   public static boolean oW(fA var0) {
      return var0 != null && var0.q().isPCUpdated() && var0.q().getBranchType() == IInsnEmulator.BranchType.JMP;
   }

   public static boolean gO(fA var0) {
      return RF(var0, true)
         || Uv(var0, true)
         || oW(var0, true)
         || var0.Dw().q().equals("RET")
         || var0.Dw().q().equals("BR") && var0.RF()[0].xK(var0.getProcessorMode());
   }

   public static boolean nf(fA var0) {
      return var0.Uv().oW() && gO(var0);
   }

   public static fA q(INativeCodeAnalyzer var0, long var1) {
      return q(var0, var1, 0);
   }

   public static fA q(INativeCodeAnalyzer var0, long var1, int var3) {
      INativeContinuousItem var4 = var0.getModel().getItemAt(var1);
      fA var5 = null;
      if (var4 instanceof INativeInstructionItem) {
         var5 = (fA)((INativeInstructionItem)var4).getInstruction();
      }

      if (var5 == null || var3 != 0 && var5.getProcessorMode() != var3 && var5.getProcessorMode() == 16) {
         var5 = q(var0.getProcessor(), var0.getMemory(), var1, var3);
      }

      return var5 != null && var3 != 0 && var5.getProcessorMode() != var3 ? null : var5;
   }

   public static fA q(IProcessor var0, IVirtualMemory var1, long var2, int var4) {
      fA var5;
      try {
         if (var0 instanceof hB) {
            var5 = ((hB)var0).q(var1, var2, var4);
         } else {
            var5 = (fA)var0.parseAt(var1, var2);
         }
      } catch (ProcessorException var6) {
         return null;
      }

      return !gP(var5) ? null : var5;
   }

   public static boolean gP(fA var0) {
      return var0 != null && !var0.nf();
   }

   public static boolean q(fA var0, boolean var1) {
      if (var0.Dw().q().equals("PUSH") && (var1 || !var0.Uv().gO())) {
         CW var2 = var0.RF()[0];
         if (var2.getOperandType() == 7) {
            for (CW var6 : var2.oW()) {
               if (var6.xK(var0.getProcessorMode())) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean RF(fA var0, boolean var1) {
      if (var0.Dw().q().equals("POP") && (var1 || !var0.Uv().gO())) {
         CW var2 = var0.RF()[0];
         if (var2.getOperandType() == 7) {
            for (CW var6 : var2.oW()) {
               if (var6.RF(var0.getProcessorMode())) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean xK(fA var0, boolean var1) {
      if (var0.Dw().q().equals("POP") && (var1 || !var0.Uv().gO())) {
         CW var2 = var0.RF()[0];
         if (var2.getOperandType() == 7) {
            for (CW var6 : var2.oW()) {
               if (var6.xK(var0.getProcessorMode())) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean Dw(fA var0, boolean var1) {
      return !var0.Dw().q().equals("BX") || !var1 && var0.Uv().gO() ? false : var0.RF()[0].RF(var0.getProcessorMode());
   }

   public static boolean Uv(fA var0, boolean var1) {
      return !var0.Dw().q().equals("BX") || !var1 && var0.Uv().gO() ? false : var0.RF()[0].xK(var0.getProcessorMode());
   }

   public static boolean oW(fA var0, boolean var1) {
      return var0.Dw().q().equals("MOV") && (var1 || !var0.Uv().gO())
         ? var0.RF()[0].RF(var0.getProcessorMode()) && var0.RF()[1].xK(var0.getProcessorMode())
         : false;
   }

   public static boolean gO(fA var0, boolean var1) {
      return var1 && var0.RF().length == 3 && var0.RF()[0] == var0.RF()[2]
         ? true
         : var0.RF().length == 3 && var0.RF()[0] == var0.RF()[1] || var0.RF().length == 2;
   }

   public static int nf(fA var0, boolean var1) {
      if (var1 && var0.RF().length == 3 && var0.RF()[0] == var0.RF()[2]) {
         return 1;
      } else if (var0.RF().length == 3 && var0.RF()[0] == var0.RF()[1]) {
         return 2;
      } else {
         return var0.RF().length == 2 ? 1 : -1;
      }
   }

   public static boolean q(int var0) {
      return var0 == 8 || var0 == 12 || var0 == 10 || var0 == 2 || var0 == 5;
   }

   public static boolean RF(int var0) {
      return var0 == 9 || var0 == 13 || var0 == 11 || var0 == 3 || var0 == 4;
   }

   public static boolean xK(int var0) {
      return var0 == 9 || var0 == 13;
   }

   public static boolean Dw(int var0) {
      return var0 == 8 || var0 == 12;
   }

   public static boolean Uv(int var0) {
      return var0 == 11 || var0 == 3 || var0 == 4;
   }
}
