package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IResizableInstruction;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;

public class CFGUtil {
   private static final ILogger logger = GlobalLog.getLogger(CFGUtil.class);

   public static boolean compare(CFG var0, CFG var1) {
      return compare(0, null, var0, var1);
   }

   public static boolean compare(int var0, Comparator var1, CFG var2, CFG var3) {
      if (var2.getFlags() != var3.getFlags()) {
         return failComp(var0, "Flags differ: %X, %X", var2.getFlags(), var3.getFlags());
      } else if (var2.getEntryAddress() != var3.getEntryAddress()) {
         return failComp(var0, "Entry-point address differ: 0x%X, 0x%X", var2.getEntryAddress(), var3.getEntryAddress());
      } else if (var2.size() != var3.size()) {
         return failComp(var0, "Block count differ: %d, %d", var2.size(), var3.size());
      } else {
         for (int var4 = 0; var4 < var2.size(); var4++) {
            BasicBlock var5 = var2.get(var4);
            BasicBlock var6 = var3.get(var4);
            if (var5.getFirstAddress() != var6.getFirstAddress()) {
               return failComp(var0, "Block #%d address differ: 0x%X, 0x%X", var4, var5.getFirstAddress(), var6.getFirstAddress());
            }

            if (var5.size() != var6.size()) {
               return failComp(var0, "Block #%d count of instructions differ: %d, %d", var4, var5.size(), var6.size());
            }

            for (int var7 = 0; var7 < var5.size(); var7++) {
               IInstruction var8 = var5.get(var7);
               IInstruction var9 = var6.get(var7);
               boolean var10;
               if (var1 == null) {
                  var10 = var8.equals(var9);
               } else {
                  var10 = var1.compare(var8, var9) == 0;
               }

               if (!var10) {
                  var1.compare(var8, var9);
                  return failComp(var0, "Block #%d Instruction #%d differ: '%s', '%s'", var4, var7, var8, var9);
               }
            }
         }

         return true;
      }
   }

   private static boolean failComp(int var0, String var1, Object... var2) {
      if (var0 != 0) {
         String var3 = "CFG comparison failed: " + Strings.ff(var1, var2);
         if (var0 == 1) {
            logger.debug(var3);
         } else if (var0 == 2) {
            throw new RuntimeException(var3);
         }
      }

      return false;
   }

   public static CFG duplicateShallow(CFG var0) {
      CFG var1 = var0.shallowCopy(false);
      IdentityHashMap var2 = new IdentityHashMap();
      int var3 = 0;

      for (BasicBlock var5 : var0.bblist) {
         var2.put(var5, var3);
         BasicBlock var6 = var5.shallowCopy(false);
         var1.bblist.add(var6);
         var3++;
      }

      var3 = 0;

      for (BasicBlock var12 : var0.bblist) {
         BasicBlock var13 = (BasicBlock)var1.bblist.get(var3);

         for (BasicBlock var8 : var12.getInputs()) {
            int var9 = (Integer)var2.get(var8);
            var13.src.add((BasicBlock)var1.bblist.get(var9));
         }

         for (BasicBlock var17 : var12.getOutputs()) {
            int var20 = (Integer)var2.get(var17);
            var13.dst.add((BasicBlock)var1.bblist.get(var20));
         }

         for (BasicBlock var18 : var12.getIrregularInputs()) {
            int var21 = (Integer)var2.get(var18);
            var13.irrsrc.add((BasicBlock)var1.bblist.get(var21));
         }

         for (BasicBlock var19 : var12.getIrregularOutputs()) {
            int var22 = (Integer)var2.get(var19);
            var13.irrdst.add((BasicBlock)var1.bblist.get(var22));
         }

         var3++;
      }

      return var1;
   }

   public static Collection getReachableBlocks(CFG var0) {
      HashSet var1 = new HashSet();
      ArrayDeque var2 = new ArrayDeque();
      var2.add(var0.getEntryBlock());

      while (!var2.isEmpty()) {
         BasicBlock var3 = (BasicBlock)var2.remove();
         if (var1.add(var3)) {
            var2.addAll(var3.getOutputs());
            var2.addAll(var3.getIrregularOutputs());
         }
      }

      return var1;
   }

   public static int removeUnreachableBlocks(CFG var0) {
      ArrayDeque var1 = new ArrayDeque(var0.getBlocksView());
      var1.removeAll(getReachableBlocks(var0));
      return var1.isEmpty() ? 0 : removeUnreachableBlocks(var0, var1);
   }

   public static int removeUnreachableBlocks(CFG var0, Deque var1) {
      HashSet var2 = new HashSet();
      long var3 = var0.getEntryAddress();

      while (!var1.isEmpty()) {
         BasicBlock var5 = (BasicBlock)var1.remove();
         long var6 = var5.getBase();
         if (var6 != var3 && var2.add(var6)) {
            List var8 = var5.getAllOutputBlocks();

            for (BasicBlock var10 : var5.getOutputBlocks()) {
               var0.deleteEdges(var5, var10);
            }

            for (BasicBlock var18 : var5.getIrregularOutputBlocks()) {
               var0.deleteIrregularEdges(var5, var18);
            }

            for (BasicBlock var19 : var8) {
               if (var19.allinsize() == 0) {
                  var1.add(var19);
               }
            }
         }
      }

      for (long var13 : var2) {
         BasicBlock var14 = var0.getBlockAt(var13);
         var0.removeBlock(var14);
         BasicBlock var17 = var0.getBlockEndingAt(var14.getFirstAddress());
         if (var17 != null && var17.getLast() instanceof IResizableInstruction var11) {
            var11.adjustSize(var14.getSizeOfInstructions());
         }
      }

      return var2.size();
   }
}
