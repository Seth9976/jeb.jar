package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.List;
import java.util.TreeSet;

public class WW implements YX {
   private final aaf q;

   public WW(aaf var1) {
      this.q = var1;
   }

   public boolean q(long var1, fA var3, TreeSet var4, TreeSet var5) {
      axp var6 = this.q.oW(var1);
      CFG var7 = var6.getData().getCFG();
      if (!Booleans.isTrue(var6.getNonReturning())) {
         return false;
      } else {
         List var8 = var7.getExitBlocks();
         if (var8.size() == 0) {
            return false;
         } else {
            for (BasicBlock var10 : var8) {
               AddressableInstruction var11 = var10.getLast2();
               if (!OC.Dw((fA)var11.getInstruction())) {
                  return false;
               }

               Long var12;
               try {
                  var12 = ((fA)var11.getInstruction()).q().q((fA)var11.getInstruction(), var11.getOffset(), null);
               } catch (ProcessorException var14) {
                  return false;
               }

               if (var12 == null) {
                  return false;
               }

               axp var13 = this.q.oW(var12);
               if (var13 == null) {
                  return false;
               }

               if (!this.q(var12, var13)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   private boolean q(long var1, axp var3) {
      String var4 = var3.getName();
      int var5 = 0;

      while (var4.startsWith("→", var5)) {
         var5++;
      }

      return "_Unwind_Resume".equals(var4.substring(var5)) ? true : "__clang_call_terminate".equals(var4.substring(var5));
   }
}
