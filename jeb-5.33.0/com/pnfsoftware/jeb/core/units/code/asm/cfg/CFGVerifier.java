package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jebglobal.aji;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class CFGVerifier {
   private static final ILogger logger = GlobalLog.getLogger(CFGVerifier.class);
   protected CFG cfg;

   public CFGVerifier(CFG var1) {
      this.cfg = var1;
   }

   protected void customVerification() throws CfgVerificationException {
   }

   protected boolean shouldExpectFallthrough(IInstruction var1) {
      return false;
   }

   public void verify() throws CfgVerificationException {
      this.customVerification();

      for (BasicBlock var2 : this.cfg) {
         boolean var3 = false;

         for (int var4 = 0; var4 < var2.size(); var4++) {
            IInstruction var5 = var2.get(var4);
            IFlowInformation var6 = var5.getBreakingFlow(var2.getAddressOfInstruction(var4));
            if (var6.isBroken()) {
               if (var3) {
                  throw new CfgVerificationException("Block %X: Multiple branching statements", var2.getBase());
               }

               var3 = true;
               if (var4 + var6.getDelaySlotCount() + 1 != var2.size()) {
                  throw new CfgVerificationException("Block %X: Illegal location for branching", var2.getBase());
               }

               this.verifyBranchingInstruction(var2, var5, var6);
            }

            var6 = var5.getRoutineCall(var2.getAddressOfInstruction(var4));
            if (var6.isBroken() && (this.cfg.getFlags() & 1) == 0) {
               if (var3) {
                  throw new CfgVerificationException("Block %X: Multiple branching statements", var2.getBase());
               }

               var3 = true;
               if (var4 + var6.getDelaySlotCount() + 1 != var2.size()) {
                  throw new CfgVerificationException("Block %X: Illegal location for branching statement", var2.getBase());
               }
            }
         }

         if (!var3) {
            int var7 = var2.outsize();
            if (var7 != 0) {
               if (var7 != 1) {
                  throw new CfgVerificationException("Block %X: Not expecting multiple out-edges", var2.getBase());
               }

               this.verifyFallthrough(var2, null);
            }
         }
      }
   }

   private void verifyBranchingInstruction(BasicBlock var1, IInstruction var2, IFlowInformation var3) throws CfgVerificationException {
      List var4 = var3.getTargets();
      ArrayList var5 = new ArrayList();
      HashSet var6 = new HashSet();
      TreeSet var7 = new TreeSet();

      for (ICodePointer var9 : var4) {
         if (!var9.isUnknownAddress()) {
            var5.add(var9.getAddress());
            var6.add(var9.getAddress());
            if (this.cfg.getInstruction(var9.getAddress()) == null) {
               var7.add(var9.getAddress());
            }
         }
      }

      if (var3.mustComputeFallThrough()) {
         var5.add(0, var1.getEndAddress());
         var6.add(var1.getEndAddress());
      }

      if (!var7.isEmpty()) {
         throw new CfgVerificationException(
            "Block %X: Targets do not point to instructions: %s (instruction: %s)", var1.getBase(), Longs.formatHexCollection(var7), var2
         );
      } else if (var5.size() != var6.size()) {
         throw new CfgVerificationException("Block %X: Duplicate targets: %s", var1.getBase(), Longs.formatHexCollection(var5));
      } else {
         List var13 = var1.getOutputOffsets();
         HashSet var14 = new HashSet(var13);
         if (var13.size() != var14.size()) {
            throw new CfgVerificationException("Block %X: Duplicate edges: %s", var1.getBase(), Longs.formatHexCollection(var13));
         } else {
            boolean var10 = false;
            if (var5.size() != var1.outsize()) {
               if (this instanceof aji var11
                  && var11.pC().getRoutine().getData().getTrampolineTarget() != null
                  && this.cfg.size() == 1
                  && var5.size() == 1
                  && var1.outsize() == 0) {
                  var10 = true;
               }

               if (!var10) {
                  Strings.ff("Block %X: Number of targets (%d) differ from number of edges (%d)", var1.getBase(), var5.size(), var1.outsize());
                  Object[] var10000 = new Object[0];
                  var10 = true;
               }
            }

            if (!var10 && !var6.equals(var14)) {
               Strings.ff(
                  "Block %X: Targets are not synchronized with edges: targets=%s / edges=%s",
                  var1.getBase(),
                  Longs.formatHexCollection(var6),
                  Longs.formatHexCollection(var14)
               );
               Object[] var15 = new Object[0];
            }

            if (this.shouldExpectFallthrough(var2)) {
               this.verifyFallthrough(var1, var5);
            }
         }
      }
   }

   private void verifyFallthrough(BasicBlock var1, List var2) throws CfgVerificationException {
      long var3 = var1.getEndAddress();
      int var5 = 0;

      for (BasicBlock var7 : var1.getOutputs()) {
         if (var7.getBase() == var3) {
            if (var5 != 0) {
               throw new CfgVerificationException(
                  "Block %X: Fallthrough is not the first edge (index %d in list: %s)",
                  var1.getBase(),
                  var5,
                  Longs.formatHexCollection(var1.getOutputOffsets())
               );
            }

            if (var2 != null && !var2.isEmpty() && (Long)var2.get(0) != var3) {
               throw new CfgVerificationException("Block %X: Fallthrough is not the first target (but is correct in the CFG)", var1.getBase());
            }
            break;
         }

         var5++;
      }

      if (var2 != null) {
         var5 = 0;

         for (long var11 : var2) {
            if (var11 == var3) {
               if (var5 != 0) {
                  throw new CfgVerificationException(
                     "Block %X: Fallthrough is not the first target (index %d in list: %s)", var1.getBase(), var5, Longs.formatHexCollection(var2)
                  );
               }

               if (var1.outsize() > 0 && var1.getOutputBlock(0).getBase() != var3) {
                  throw new CfgVerificationException("Block %X: Fallthrough is not the first edge (but is correct in targets)", var1.getBase());
               }
               break;
            }

            var5++;
         }
      }
   }
}
