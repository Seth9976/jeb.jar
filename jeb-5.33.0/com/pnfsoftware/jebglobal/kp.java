package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import java.util.TreeSet;

public class kp implements zz {
   private final Tw pC;

   public kp(Tw var1) {
      this.pC = var1;
   }

   public boolean pC(long var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var3, TreeSet var4, TreeSet var5) {
      Long var6 = (Long)var4.lower(var1);
      if (var6 != null) {
         auu var7 = this.pC.E(var6);
         CFG var8 = var7.getData().getCFG();
         if (var8 == null) {
            return false;
         }

         long var9 = var8.getEndAddress();
         if (var9 > var1) {
            long var11 = var7.getMemoryAddress();
            boolean var13 = true;

            while (true) {
               auu var14 = this.pC.UT(var11 + 1L);
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
            if (var12 != null && !((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var12.getInstruction()).UT().E()) {
               return true;
            }

            if (!PU.A((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var15.getLast()) || !((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var15.getLast()).UT().E()) {
               return true;
            }
         }
      }

      return false;
   }
}
