package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexParsingException;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCodeItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugInfo;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Ser
@SerVersion(2)
public class bev implements IDexCodeItem {
   @SerId(1)
   private int pC = -1;
   @SerId(2)
   private int A;
   @SerId(3)
   private int kS;
   @SerId(4)
   private int wS;
   @SerId(5)
   private beq UT;
   @SerId(6)
   private int E;
   @SerId(7)
   private int sY;
   @SerId(8)
   private List ys;
   @SerId(9)
   private bfb[] ld;
   @SerId(10)
   private CFG gp;
   @SerId(11)
   private int oT;
   @SerId(value = 12, version = 1)
   private boolean fI;
   @SerId(value = 13, version = 2)
   private Map WR;
   @SerId(14)
   private List NS;
   @SerId(15)
   private bes vP;

   public bev(int var1, int var2, int var3, bes var4, int var5, int var6, List var7, List var8, CFG var9, Map var10, bfb[] var11, int var12) {
      if (var8 == null) {
         throw new DexParsingException("Cannot build ClassCode item without instructions!");
      } else if (var1 >= 0 && var2 >= 0 && var3 >= 0 && var5 >= 0) {
         this.A = var1;
         this.kS = var2;
         this.wS = var3;
         this.vP = var4;
         this.E = var5;
         this.sY = var6;
         this.NS = var7;
         this.ys = var8;
         this.gp = var9;
         this.WR = var10;
         this.ld = var11 == null ? new bfb[0] : var11;
         this.oT = var12;
      } else {
         throw new DexParsingException(Strings.ff("Illegal ClassCode params: regcount=%d incount=%d outcount=%d insn_off=%Xh", var1, var2, var3, var5));
      }
   }

   public void pC(int var1) {
      this.pC = var1;
   }

   public int pC() {
      return this.pC;
   }

   @Override
   public int getDexEntryIndex() {
      return this.oT;
   }

   @Override
   public int getInstructionsOffset() {
      return this.E;
   }

   @Override
   public int getInstructionsSize() {
      return this.sY;
   }

   @Override
   public boolean hasParsingErrors() {
      return this.fI || this.NS != null && !this.NS.isEmpty();
   }

   @Override
   public List getParsingErrors() {
      return this.NS == null ? Collections.emptyList() : Collections.unmodifiableList(this.NS);
   }

   @Override
   public List getInstructions() {
      return Collections.unmodifiableList(this.ys);
   }

   @Override
   public CFG getControlFlowGraph() {
      return this.gp;
   }

   @Override
   public Map getGaps() {
      return this.WR == null ? Collections.EMPTY_MAP : Collections.unmodifiableMap(this.WR);
   }

   @Override
   public IDalvikInstruction getInstruction(int var1) {
      return (IDalvikInstruction)this.ys.get(var1);
   }

   @Override
   public IDalvikInstruction getInstructionAt(int var1) {
      return (IDalvikInstruction)CollectionUtil.binarySearch(this.ys, var1, new bew(this));
   }

   public bek A(int var1) {
      for (bek var3 : this.ys) {
         if (var3.getOffset() == var1) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public int getRegisterCount() {
      return this.A;
   }

   public int A() {
      return this.kS;
   }

   @Override
   public int getInputArgumentCount() {
      return this.A();
   }

   public int kS() {
      return this.wS;
   }

   @Override
   public int getOutputArgumentCount() {
      return this.kS();
   }

   public bfb[] wS() {
      return this.ld;
   }

   @Override
   public List getExceptionItems() {
      return Arrays.asList(this.ld);
   }

   @Override
   public IDexDebugInfo getDebugInfo() {
      if (this.UT != null) {
         return this.UT;
      } else {
         return this.vP != null ? this.vP.pC() : null;
      }
   }

   public static String kS(int var0) {
      return Formatter.toHexString(var0, true);
   }
}
