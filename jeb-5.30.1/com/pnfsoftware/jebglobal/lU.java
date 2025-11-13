package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import java.util.TreeSet;

public class lU implements YX {
   private final aaf q;

   public lU(aaf var1) {
      this.q = var1;
   }

   public boolean q(long var1, fA var3, TreeSet var4, TreeSet var5) {
      Long var6 = (Long)var4.lower(var1);
      if (var6 != null) {
         axp var7 = this.q.oW(var6);
         CFG var8 = var7.getData().getCFG();
         if (var8 == null) {
            return false;
         }

         long var9 = var8.getEndAddress();
         if (var9 > var1) {
            long var11 = var7.getMemoryAddress();
            boolean var13 = true;

            while (true) {
               axp var14 = this.q.Uv(var11 + 1L);
               if (var14 == null) {
                  break;
               }

               var11 = var14.getMemoryAddress();
               if (var11 >= var9) {
                  break;
               }

               if (!var5.contains(var11)) {
                  var13 = false;
                  break;
               }
            }

            if (var13) {
               return true;
            }
         } else if (var9 == var1) {
            BasicBlock var15 = var8.getBlockEndingAt(var1);
            AddressableInstruction var12 = var15.getBranchingInstruction2();
            if (var12 != null && !((fA)var12.getInstruction()).Uv().oW()) {
               return true;
            }

            if (!OC.RF((fA)var15.getLast()) || !((fA)var15.getLast()).Uv().oW()) {
               return true;
            }
         }
      }

      return false;
   }
}
