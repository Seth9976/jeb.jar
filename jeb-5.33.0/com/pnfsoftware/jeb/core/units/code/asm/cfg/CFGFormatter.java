package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IVariable;
import com.pnfsoftware.jeb.util.collect.Bitmap;
import com.pnfsoftware.jeb.util.collect.CollectionOrder;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CFGFormatter {
   private static final ILogger logger = GlobalLog.getLogger(CFGFormatter.class);
   public static final AtomicInteger cfgfwccnt = new AtomicInteger();
   private CFG cfg;
   private IVariableProvider varprv;
   private boolean formatFineGrained;

   public CFGFormatter(CFG var1) {
      this(var1, null, false);
   }

   public CFGFormatter(CFG var1, IVariableProvider var2, boolean var3) {
      this.cfg = var1;
      this.varprv = var2;
      this.formatFineGrained = var3;
   }

   public final CFG getCfg() {
      return this.cfg;
   }

   public final String formatSimple() {
      return this.format(true, false, false, null, null);
   }

   public String format(boolean var1, boolean var2, boolean var3, IDFA var4, IFormattingContextFactory var5) {
      StringBuilder var6 = new StringBuilder();
      if (var3 || var2) {
         if (var4 == null) {
            var4 = this.cfg.doDataFlowAnalysis(false);
            if (var4 == null) {
               throw new IllegalArgumentException();
            }
         }

         cfgfwccnt.incrementAndGet();
      }

      this.genHeader(var6);
      if (var3) {
         String var7 = this.formatChain(var4.getInputMap());
         Strings.ff(var6, "CFG{%d blk, %d insn} %s\n", this.cfg.size(), this.cfg.getInstructionCount(), var4.toString());
         Strings.ff(var6, ">> IN(@%X): %s\n", this.cfg.getEntryAddress(), var7);
      }

      for (BasicBlock var9 : this.cfg) {
         int var10 = 0;

         for (AddressableInstruction var12 : var9.addressableInstructions()) {
            IInstruction var13 = var12.getInstruction();
            long var14 = var12.getOffset();
            this.genBOL(var6, var14, var13);
            if (var1) {
               this.genPreAddress(var6, var14, var13);
               this.genAddress(var6, var14, var13);
               this.genAddressCharacter(var6, var14, var13, var9, var10);
               this.genPostAddress(var6, var14, var13);
            }

            this.genPreInstruction(var6, var14, var13);
            Object var16 = var5 == null ? var14 : var5.createFormattingContext(var13);
            var6.append(var13.format(var16));
            this.genPostInstruction(var6, var14, var13);
            if (var2) {
               this.genPreChains(var6, var14, var13);
               var16 = this.formatChain(var4.getDefUseChains(var14));
               String var17 = this.formatChain(var4.getUseDefChains(var14));
               Strings.ff(var6, "DU: %-30s | UD: %s", var16, var17);
               this.genPostChains(var6, var14, var13);
            }

            this.genEOL(var6, var14, var13);
            var10++;
         }
      }

      if (var3) {
         for (BasicBlock var19 : this.cfg.getExitBlocks()) {
            String var20 = this.formatChain(var4.getOutputMap(var19));
            Strings.ff(var6, "<< OUT(@%X): %s\n", var19.getEndAddress(), var20);
         }
      }

      this.genTrailer(var6);
      return var6.toString();
   }

   private String formatChain(Map var1) {
      return this.varprv == null ? formatChainsInternalLegacy(var1) : formatChainsInternal(var1, this.varprv, this.formatFineGrained);
   }

   public static String formatChains(Map var0, IVariableProvider var1) {
      return formatChainsInternal(var0, var1, false);
   }

   private static String formatChainsInternal(Map var0, IVariableProvider var1, boolean var2) {
      LinkedHashMap var3 = new LinkedHashMap();

      for (int var5 : var0.keySet()) {
         IVariable var6 = var1.getContaining(var5);
         if (var6 == null) {
            throw new RuntimeException("Cannot retrieve variable containing vbit " + var5);
         }

         VarLocations var7 = (VarLocations)var3.get(var6.getId());
         if (var7 == null) {
            var7 = new VarLocations(var6);
            var3.put(var6.getId(), var7);
         }

         for (Number var9 : (Collection)var0.get(var5)) {
            var7.record(var9, var5);
         }
      }

      MultiMap var12 = new MultiMap(CollectionOrder.INSERTION);
      if (!var2) {
         for (VarLocations var16 : var3.values()) {
            var12.createKey(var16.var.getName());
            var12.putMulti(var16.var.getName(), var16.touched.keySet());
         }
      } else {
         for (VarLocations var17 : var3.values()) {
            ArrayList var19 = new ArrayList();
            MultiMap var21 = new MultiMap();

            for (Number var10 : var17.touched.keySet()) {
               Bitmap var11 = (Bitmap)var17.touched.get(var10);
               if (var11.isFull()) {
                  var19.add(var10);
               } else {
                  var21.put(var11, var10);
               }
            }

            var12.createKey(var17.var.getName());
            var12.putMulti(var17.var.getName(), var19);

            for (Bitmap var26 : var21.keySet()) {
               var12.putMulti(var17.var.getName() + var26.formatAsRanges(), var21.get(var26));
            }
         }
      }

      StringBuilder var15 = new StringBuilder();

      for (String var20 : var12.keySet()) {
         Strings.ff(var15, "%s={", var20);
         ArrayList var22 = new ArrayList(var12.get(var20));
         Collections.sort(var22, new CFGFormatter$1());
         int var25 = 0;

         for (Number var28 : var22) {
            if (var25 >= 1) {
               var15.append(",");
            }

            if (var28 instanceof Integer) {
               Strings.ff(var15, "%X", var28.intValue());
            } else if (var28 instanceof Long) {
               if (var28.longValue() == -1L) {
                  var15.append("@init");
               } else {
                  Strings.ff(var15, "@%X", var28.longValue());
               }
            }

            var25++;
         }

         var15.append("} ");
      }

      return var15.toString();
   }

   private static String formatChainsInternalLegacy(Map var0) {
      StringBuilder var1 = new StringBuilder();
      Object var2;
      if (var0 instanceof HashMap) {
         var2 = new TreeMap(var0);
      } else {
         var2 = var0;
      }

      for (int var4 : var2.keySet()) {
         var1.append("(").append(var4 >= 0 ? Integer.toHexString(var4) : "-" + Integer.toHexString(-var4)).append(")={");
         ArrayList var5 = new ArrayList((Collection)var2.get(var4));
         Collections.sort(var5, new CFGFormatter$2());
         int var6 = 0;

         for (Number var8 : var5) {
            if (var6 >= 1) {
               var1.append(",");
            }

            if (var8 instanceof Integer) {
               var1.append(Integer.toHexString(var8.intValue()).toUpperCase());
            } else if (var8 instanceof Long) {
               if (var8.longValue() == -1L) {
                  var1.append("@init");
               } else {
                  var1.append("@").append(Long.toHexString(var8.longValue()).toUpperCase());
               }
            }

            var6++;
         }

         var1.append("} ");
      }

      return var1.toString();
   }

   protected final void padLine(StringBuilder var1, int var2) {
      int var3 = Math.max(0, var1.lastIndexOf("\n"));
      int var4 = var1.length() - var3;
      if (var4 < var2) {
         var1.append(Strings.spaces(var2 - var4));
      }

      var1.append("  ");
   }

   protected void genHeader(StringBuilder var1) {
   }

   protected void genBOL(StringBuilder var1, long var2, IInstruction var4) {
   }

   protected void genPreAddress(StringBuilder var1, long var2, IInstruction var4) {
   }

   protected void genAddress(StringBuilder var1, long var2, IInstruction var4) {
      Strings.ff(var1, "%04X/%X", var2, var4.getSize());
   }

   protected void genAddressCharacter(StringBuilder var1, long var2, IInstruction var4, BasicBlock var5, int var6) {
      char var7 = ':';
      if (var6 == 0) {
         if (var5.getFirstAddress() == this.getCfg().getEntryAddress()) {
            var7 = '>';
         } else if (var5.irrinsize() == 0) {
            var7 = '+';
         } else {
            var7 = '*';
         }
      } else if (var6 == var5.size() - 1) {
         List var8 = var5.getOutputs();
         if (var8.isEmpty()) {
            var7 = 'x';
         } else if (var8.size() == 1 || var8.size() == 2) {
            long var9 = ((BasicBlock)var8.get(var8.size() - 1)).getBase();
            if (var9 > var2) {
               var7 = 'v';
            } else if (var9 < var2) {
               var7 = '^';
            }
         }
      }

      Strings.ff(var1, "%c", var7);
   }

   protected void genPostAddress(StringBuilder var1, long var2, IInstruction var4) {
      var1.append("  ");
   }

   protected void genPreInstruction(StringBuilder var1, long var2, IInstruction var4) {
   }

   protected void genPostInstruction(StringBuilder var1, long var2, IInstruction var4) {
      this.padLine(var1, 120);
   }

   protected void genPreChains(StringBuilder var1, long var2, IInstruction var4) {
   }

   protected void genPostChains(StringBuilder var1, long var2, IInstruction var4) {
      this.padLine(var1, 200);
   }

   protected void genEOL(StringBuilder var1, long var2, IInstruction var4) {
      var1.append('\n');
   }

   protected void genTrailer(StringBuilder var1) {
   }
}
