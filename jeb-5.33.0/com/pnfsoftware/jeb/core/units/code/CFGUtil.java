package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CFGUtil {
   private static final ILogger logger = GlobalLog.getLogger(CFGUtil.class);

   public static void verify(IControlFlowGraph var0) {
      verify(var0, true, true, true, true, true);
   }

   public static void verify(IControlFlowGraph var0, boolean var1, boolean var2, boolean var3, boolean var4, boolean var5) {
      if (var0.size() == 0) {
         throw new IllegalArgumentException("Empty graph");
      } else {
         try {
            for (int var14 = 0; var14 < var0.size(); var14++) {
               IBasicBlock var7 = var0.get(var14);
               if (var7.isEmpty()) {
                  throw new IllegalArgumentException("Empty node: " + var7);
               }
            }

            if (var2) {
               long var15 = var0.get(0).getEndAddress();

               for (int var8 = 1; var8 < var0.size(); var8++) {
                  IBasicBlock var9 = var0.getBlock(var8);
                  long var10 = var9.getFirstAddress();
                  if (var10 != var15) {
                     throw new IllegalArgumentException(Strings.ff("Gap before block %s: 0x%X", var9, var10 - var15));
                  }

                  var15 = var9.getEndAddress();
               }
            }

            if (var1) {
               IdentityHashSet var16 = new IdentityHashSet();
               ArrayDeque var20 = new ArrayDeque();
               IBasicBlock var24 = var0.getEntryBlock();
               var16.add(var24);
               var20.add(var24);

               while (!var20.isEmpty()) {
                  IBasicBlock var30 = (IBasicBlock)var20.remove();

                  for (IBasicBlock var11 : var30.getAllOutputs()) {
                     if (var16.add(var11)) {
                        var20.add(var11);
                     }
                  }
               }

               if (var16.size() != var0.size()) {
                  List var32 = var0.getBlocks();
                  var32.removeAll(var16);
                  throw new IllegalArgumentException("Not all nodes can be reached from block #0: " + var32);
               }
            }

            if (var3) {
               for (IBasicBlock var21 : var0) {
                  IFlowInformation var31 = var21.getLast().getBreakingFlow(var21.getLastAddress());
                  int var25;
                  if (var31.isBroken()) {
                     var25 = var31.getTargets().size();
                  } else {
                     var25 = 1;
                  }

                  List var34 = var21.getOutputs();
                  if (var34.size() != var25) {
                     throw new IllegalArgumentException(Strings.ff("Duplicate out-edges from block %s: insn:%d, cfg:%d, %s", var21, var25, var34.size(), var34));
                  }
               }
            }

            if (var4) {
               for (IBasicBlock var22 : var0) {
                  List var26 = var22.getInputs();
                  if (var26.size() != new HashSet(var26).size()) {
                     throw new IllegalArgumentException(Strings.ff("Duplicate in-edges to block %s: %s", var22, var26));
                  }

                  var26 = var22.getOutputs();
                  if (var26.size() != new HashSet(var26).size()) {
                     throw new IllegalArgumentException(Strings.ff("Duplicate out-edges from block %s: %s", var22, var26));
                  }
               }
            }

            if (var5) {
               for (IBasicBlock var23 : var0) {
                  List var28 = var23.getIrregularInputs();
                  if (var28.size() != new HashSet(var28).size()) {
                     throw new IllegalArgumentException(Strings.ff("Duplicate irregular-in-edges to block %s: %s", var23, var28));
                  }

                  var28 = var23.getIrregularOutputs();
                  if (var28.size() != new HashSet(var28).size()) {
                     throw new IllegalArgumentException(Strings.ff("Duplicate irregular-out-edges to block %s: %s", var23, var28));
                  }
               }
            }
         } catch (Exception var13) {
            Exception var6 = var13;

            try {
               toDot(var0, IO.createTempFile("failed0.dot"), "FAILED VERIFICATION: " + var6.getMessage());
            } catch (IOException var12) {
            }

            throw var13;
         }
      }
   }

   public static void toTempDot(IControlFlowGraph var0, String var1) {
      toTempDot(var0, var1, null, null, -1);
   }

   public static void toTempDot(IControlFlowGraph var0, String var1, String var2, Map var3, int var4) {
      try {
         if (!var1.endsWith(".dot")) {
            var1 = var1 + ".dot";
         }

         toDot(var0, IO.createTempFile(var1), var2, var3, var4);
      } catch (IOException var6) {
         logger.catchingSilent(var6);
      }
   }

   public static void toDot(IControlFlowGraph var0, File var1) throws IOException {
      toDot(var0, var1, null, null);
   }

   public static void toDot(IControlFlowGraph var0, File var1, String var2) throws IOException {
      toDot(var0, var1, var2, null);
   }

   public static void toDot(IControlFlowGraph var0, File var1, String var2, Map var3) throws IOException {
      toDot(var0, var1, var2, var3, 0);
   }

   public static void toDot(IControlFlowGraph var0, File var1, String var2, Map var3, int var4) throws IOException {
      CFGUtil.DotFileGenerator var5 = new CFGUtil.DotFileGenerator(var0);
      var5.setTitle(var2);
      var5.setBlockHeaders(var3);
      var5.setLineLimit(var4);
      String var6 = var5.generate();
      IO.writeFile(var1, Strings.encodeUTF8(var6));
   }

   public static int countDeepInputs(IBasicBlock var0, int var1) {
      int var2 = var0.insize();
      if (var2 != 0 && var1 > 1) {
         ArrayList var3 = new ArrayList();
         HashSet var4 = new HashSet();

         for (int var5 = 2; var5 <= var1; var5++) {
            var3.clear();
            var4.clear();
            collectDeepInputsInternal(var0, var1, var3, var4);
            if (var4.size() > var2) {
               var2 = var4.size();
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   private static void collectDeepInputsInternal(IBasicBlock var0, int var1, List var2, Set var3) {
      long var4 = var0.getBase();
      if (!var2.contains(var4)) {
         if (var1 != 0 && var0.insize() != 0) {
            var2.add(var4);

            for (IBasicBlock var7 : var0.getInputs()) {
               collectDeepInputsInternal(var7, var1 - 1, var2, var3);
            }

            var2.remove(var2.size() - 1);
         } else {
            var3.add(var4);
         }
      }
   }

   public static boolean canReach(IBasicBlock var0, IBasicBlock var1) {
      return canReach(var0, var1, false);
   }

   public static boolean canReach(IBasicBlock var0, IBasicBlock var1, boolean var2) {
      return canReach(var0, var1, var2, null);
   }

   public static boolean canReach(IBasicBlock var0, IBasicBlock var1, boolean var2, Collection var3) {
      return new CFGUtil.ReachabilityChecker(var0, var1, var2, var3).check();
   }

   public static class BlockGroup {
      private CFG cfg;
      private BasicBlock blkStart;
      private BasicBlock blkStopper;
      private Set blocks;
      private Set exitpoints;
      private Set entrypoints;
      private List tmppath = new ArrayList();

      public BlockGroup(CFG var1, long var2, long var4) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.cfg = var1;
            this.blkStart = var1.getBlockAt(var2);
            if (this.blkStart == null) {
               throw new IllegalArgumentException();
            } else {
               this.blkStopper = var1.getBlockAt(var4);
               if (this.blkStopper == null) {
                  throw new IllegalArgumentException();
               }
            }
         }
      }

      public Set getBlocksInGroup() {
         if (this.blocks == null) {
            throw new IllegalStateException();
         } else {
            return this.blocks;
         }
      }

      public Set getExitPoints() {
         if (this.exitpoints == null) {
            throw new IllegalStateException();
         } else {
            return this.exitpoints;
         }
      }

      public Set getEntryPoints() {
         if (this.entrypoints == null) {
            throw new IllegalStateException();
         } else {
            return this.entrypoints;
         }
      }

      public Set determine() {
         if (this.blocks != null) {
            throw new IllegalStateException();
         } else {
            this.blocks = new LinkedHashSet();
            this.exitpoints = new LinkedHashSet();
            this.entrypoints = new LinkedHashSet();
            HashSet var1 = new HashSet();
            ArrayDeque var2 = new ArrayDeque();
            var2.add(this.blkStart);

            while (!var2.isEmpty()) {
               BasicBlock var3 = (BasicBlock)var2.remove();
               if (var1.add(var3.getBase()) && this.checkBlock(var3)) {
                  var2.addAll(var3.getOutputs());
               }
            }

            LinkedHashSet var11 = new LinkedHashSet();
            var2.add(this.blkStart);

            while (!var2.isEmpty()) {
               BasicBlock var4 = (BasicBlock)var2.remove();
               long var5 = var4.getBase();
               if (this.blocks.contains(var5) && var11.add(var5)) {
                  var2.addAll(var4.getOutputs());
               }
            }

            this.blocks = var11;

            for (long var13 : this.blocks) {
               BasicBlock var7 = this.cfg.getBlockAt(var13);

               for (long var9 : var7.getOutputOffsets()) {
                  if (!this.blocks.contains(var9)) {
                     this.exitpoints.add(var13);
                  }
               }

               for (long var15 : var7.getInputOffsets()) {
                  if (!this.blocks.contains(var15)) {
                     this.entrypoints.add(var13);
                  }
               }
            }

            if (this.blkStart == this.cfg.getEntryBlock() && this.blocks.contains(this.blkStart.getBase())) {
               this.entrypoints.add(this.blkStart.getBase());
            }

            return this.blocks;
         }
      }

      private boolean checkBlock(BasicBlock var1) {
         if (var1 == this.blkStopper) {
            return false;
         } else {
            long var2 = var1.getBase();
            if (this.tmppath.contains(var2)) {
               return true;
            } else {
               if (!this.blocks.contains(var2)) {
                  this.tmppath.add(var2);

                  for (BasicBlock var5 : var1.getInputs()) {
                     if (!this.blocks.contains(var5.getBase()) && !this.checkBlock(var5)) {
                        this.tmppath.remove(var2);
                        return false;
                     }
                  }

                  this.tmppath.remove(var2);
                  this.blocks.add(var2);
               }

               return true;
            }
         }
      }
   }

   public static class DotFileGenerator {
      private IControlFlowGraph cfg;
      private String title;
      private Map blockHeaders;
      private int lineLimit;
      private int nodeIndexingStartValue = 1;

      public DotFileGenerator(IControlFlowGraph var1) {
         this.cfg = var1;
      }

      public void setTitle(String var1) {
         this.title = var1;
      }

      public void setBlockHeaders(Map var1) {
         this.blockHeaders = var1;
      }

      public void setLineLimit(int var1) {
         this.lineLimit = var1;
      }

      public void setGenerateBlockIndices(int var1) {
         this.nodeIndexingStartValue = var1;
      }

      public String generate() {
         StringBuilder var1 = new StringBuilder();
         var1.append("digraph G {\n");
         if (this.title != null) {
            var1.append("labelloc=\"t\"\n");
            var1.append("labelfontname=\"Consolas\"\n");
            Strings.ff(var1, "label=\"%s\"\n", dotEscape(this.title));
         }

         int var2 = Integer.MAX_VALUE;
         boolean var3 = false;
         if (this.lineLimit > 0) {
            var2 = this.lineLimit;
         } else if (this.lineLimit < 0) {
            var2 = -this.lineLimit;
            var3 = true;
         }

         int var4 = 0;

         for (IBasicBlock var6 : this.cfg) {
            String var7 = "";
            if (this.nodeIndexingStartValue >= 0) {
               var7 = "(" + (var4 + this.nodeIndexingStartValue) + ")";
            }

            if (this.blockHeaders != null) {
               String var8 = (String)this.blockHeaders.get(var6.getFirstAddress());
               if (var8 != null) {
                  var7 = var7 + " " + var8;
               }
            }

            StringBuilder var27 = new StringBuilder(var7);
            var27.append("\\l");
            int var9 = 0;

            for (IInstruction var11 : var6) {
               long var12 = var6.getAddressOfInstruction(var9);
               int var14 = var9 >= 1 ? 58 : (var6.getFirstAddress() == 0L ? 62 : (var6.irrinsize() == 0 ? 43 : 42));
               String var15 = Strings.ff("%04X/%X%c ", var12, var11.getSize(), Character.valueOf((char)var14));
               var27.append(dotEscape(var15));
               String var16 = var11.format(var12);
               if (var16.length() <= var2) {
                  var27.append(dotEscape(var16));
                  var27.append("\\l");
               } else if (!var3) {
                  String var37 = var16.substring(0, var2) + "...";
                  var27.append(dotEscape(var37));
                  var27.append("\\l");
               } else {
                  int var17 = 0;

                  for (String var18 = var16; !var18.isEmpty(); var17++) {
                     int var19 = var18.length() <= var2 ? var18.length() : var2;
                     String var20 = var18.substring(0, var19);
                     if (var17 > 0) {
                        var27.append(dotEscape(Strings.generate("&nbsp;", var15.length())));
                     }

                     var27.append(dotEscape(var20));
                     var27.append("\\l");
                     var18 = var18.substring(var19);
                  }
               }

               var9++;
            }

            String var34 = "";
            String var35 = this.generateNodeBackgroundColor(var6);
            if (var35 != null) {
               var34 = var34 + Strings.ff("fillcolor=\"%s\",", var35);
            }

            String var36 = this.generateNodeTextColor(var6);
            if (var36 != null) {
               var34 = var34 + Strings.ff("fontcolor=\"%s\",", var36);
            }

            Strings.ff(
               var1, "BB_%08x [shape=Mrecord,fontname=\"Consolas\",%sstyle=\"filled\",label=\"%s\"];\n", var6.getFirstAddress(), var34, var27.toString()
            );
            var4++;
         }

         for (IBasicBlock var22 : this.cfg) {
            if (var22.outsize() == 2) {
               IBasicBlock var24 = var22.getOutputBlock(0);
               String var29 = this.generateEdgeLabel(var22, 0);
               if (var29 == null) {
                  var29 = "";
               }

               Strings.ff(
                  var1,
                  "BB_%08x -> BB_%08x [color=\"red\",fontname=\"Consolas\",fontsize=10,label=\"%s\"];\n",
                  var22.getFirstAddress(),
                  var24.getFirstAddress(),
                  var29
               );
               var24 = var22.getOutputBlock(1);
               String var32 = this.generateEdgeLabel(var22, 1);
               if (var32 == null) {
                  var32 = "";
               }

               Strings.ff(
                  var1,
                  "BB_%08x -> BB_%08x [color=\"green\",fontname=\"Consolas\",fontsize=10,label=\"%s\"];\n",
                  var22.getFirstAddress(),
                  var24.getFirstAddress(),
                  var32
               );
            } else {
               for (int var23 = 0; var23 < var22.outsize(); var23++) {
                  IBasicBlock var28 = var22.getOutputBlock(var23);
                  String var31 = this.generateEdgeLabel(var22, var23);
                  if (var31 == null) {
                     var31 = "";
                     if (var22.outsize() >= 3) {
                        var31 = var31 + var23;
                     }
                  }

                  Strings.ff(
                     var1, "BB_%08x -> BB_%08x [fontname=\"Consolas\",fontsize=10,label=\"%s\"];\n", var22.getFirstAddress(), var28.getFirstAddress(), var31
                  );
               }
            }

            for (int var26 = 0; var26 < var22.irroutsize(); var26++) {
               IBasicBlock var30 = var22.getIrregularOutputBlock(var26);
               String var33 = this.generateIrregularEdgeLabel(var22, var26);
               if (var33 == null) {
                  var33 = "";
                  if (var22.irroutsize() >= 2) {
                     var33 = var33 + var26;
                  }
               }

               Strings.ff(
                  var1,
                  "BB_%08x -> BB_%08x [style=dotted,fontname=\"Consolas\",fontsize=10,label=\"%s\"];\n",
                  var22.getFirstAddress(),
                  var30.getFirstAddress(),
                  var33
               );
            }
         }

         var1.append("}");
         return var1.toString();
      }

      protected String generateEdgeLabel(IBasicBlock var1, int var2) {
         return null;
      }

      protected String generateIrregularEdgeLabel(IBasicBlock var1, int var2) {
         return null;
      }

      protected String generateNodeBackgroundColor(IBasicBlock var1) {
         return null;
      }

      protected String generateNodeTextColor(IBasicBlock var1) {
         return null;
      }

      private static String dotEscape(String var0) {
         var0 = var0.replace("\\", "\\\\");
         var0 = var0.replace("|", "\\|");
         var0 = var0.replace("<", "\\<");
         var0 = var0.replace(">", "\\>");
         var0 = var0.replace("{", "\\{");
         var0 = var0.replace("}", "\\}");
         return var0.replace("\"", "\\\"");
      }
   }

   static class ReachabilityChecker {
      private IBasicBlock from;
      private IBasicBlock to;
      private boolean alsoFollowIrregularFlow;
      private Collection stopperBlocks;
      private Set seen = new HashSet();

      ReachabilityChecker(IBasicBlock var1, IBasicBlock var2, boolean var3, Collection var4) {
         this.from = var1;
         this.to = var2;
         this.alsoFollowIrregularFlow = var3;
         this.stopperBlocks = var4;
      }

      boolean check() {
         return this.check(this.from, 0);
      }

      private boolean check(IBasicBlock var1, int var2) {
         if (this.stopperBlocks != null && this.stopperBlocks.contains(var1)) {
            return false;
         } else if (var1 == this.to && var2 >= 1) {
            return true;
         } else if (!this.seen.add(var1.getBase())) {
            return false;
         } else {
            for (IBasicBlock var4 : var1.getOutputs()) {
               if (this.check(var4, var2 + 1)) {
                  return true;
               }
            }

            if (this.alsoFollowIrregularFlow) {
               for (IBasicBlock var6 : var1.getIrregularOutputs()) {
                  if (this.check(var6, var2 + 1)) {
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   public static class RegionFinder {
      private CFG cfg;
      private long entry;
      private Set outputs;
      private boolean canLoopBackToEntry;
      private LinkedHashSet regionBlocks = new LinkedHashSet();

      public RegionFinder(CFG var1, long var2, Set var4, boolean var5) {
         this.cfg = var1;
         this.entry = var2;
         this.outputs = var4;
         this.canLoopBackToEntry = var5;
      }

      public RegionFinder(CFG var1, long var2, long var4) {
         this.cfg = var1;
         this.entry = var2;
         this.outputs = Sets.newHashSet(var4);
         this.canLoopBackToEntry = false;
      }

      public Collection getRegionBlocks() {
         return this.regionBlocks;
      }

      public boolean process() {
         boolean var1 = true;
         ArrayDeque var2 = new ArrayDeque();
         var2.add(this.entry);
         LinkedHashSet var3 = new LinkedHashSet();
         HashSet var4 = new HashSet();
         int var5 = 0;

         while (!var2.isEmpty()) {
            long var6 = (Long)var2.remove();
            if (var3.add(var6) && !this.outputs.contains(var6)) {
               BasicBlock var8 = this.cfg.getBlockAt(var6);
               this.regionBlocks.add(var6);
               if (var6 != this.entry) {
                  for (BasicBlock var10 : var8.getAllInputs()) {
                     var4.add(var10.getAddress());
                  }
               } else if (var5 > 0 && !this.canLoopBackToEntry) {
                  var1 = false;
               }

               for (BasicBlock var12 : var8.getAllOutputs()) {
                  var2.add(var12.getAddress());
               }

               var5++;
            }
         }

         var4.removeAll(var3);
         if (!var4.isEmpty()) {
            var1 = false;
         }

         return var1;
      }
   }
}
