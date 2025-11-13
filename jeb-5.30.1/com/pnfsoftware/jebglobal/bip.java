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
public class bip implements IDexCodeItem {
   @SerId(1)
   private int q = -1;
   @SerId(2)
   private int RF;
   @SerId(3)
   private int xK;
   @SerId(4)
   private int Dw;
   @SerId(5)
   private bik Uv;
   @SerId(6)
   private int oW;
   @SerId(7)
   private int gO;
   @SerId(8)
   private List nf;
   @SerId(9)
   private biw[] gP;
   @SerId(10)
   private CFG za;
   @SerId(11)
   private int lm;
   @SerId(value = 12, version = 1)
   private boolean zz;
   @SerId(value = 13, version = 2)
   private Map JY;
   @SerId(14)
   private List HF;
   @SerId(15)
   private bim LK;

   public bip(int var1, int var2, int var3, bim var4, int var5, int var6, List var7, List var8, CFG var9, Map var10, biw[] var11, int var12) {
      if (var8 == null) {
         throw new DexParsingException("Cannot build ClassCode item without instructions!");
      } else if (var1 >= 0 && var2 >= 0 && var3 >= 0 && var5 >= 0) {
         this.RF = var1;
         this.xK = var2;
         this.Dw = var3;
         this.LK = var4;
         this.oW = var5;
         this.gO = var6;
         this.HF = var7;
         this.nf = var8;
         this.za = var9;
         this.JY = var10;
         this.gP = var11 == null ? new biw[0] : var11;
         this.lm = var12;
      } else {
         throw new DexParsingException(Strings.ff("Illegal ClassCode params: regcount=%d incount=%d outcount=%d insn_off=%Xh", var1, var2, var3, var5));
      }
   }

   public void q(int var1) {
      this.q = var1;
   }

   public int q() {
      return this.q;
   }

   @Override
   public int getDexEntryIndex() {
      return this.lm;
   }

   @Override
   public int getInstructionsOffset() {
      return this.oW;
   }

   @Override
   public int getInstructionsSize() {
      return this.gO;
   }

   @Override
   public boolean hasParsingErrors() {
      return this.zz || this.HF != null && !this.HF.isEmpty();
   }

   @Override
   public List getParsingErrors() {
      return this.HF == null ? Collections.emptyList() : Collections.unmodifiableList(this.HF);
   }

   @Override
   public List getInstructions() {
      return Collections.unmodifiableList(this.nf);
   }

   @Override
   public CFG getControlFlowGraph() {
      return this.za;
   }

   @Override
   public Map getGaps() {
      return this.JY == null ? Collections.EMPTY_MAP : Collections.unmodifiableMap(this.JY);
   }

   @Override
   public IDalvikInstruction getInstruction(int var1) {
      return (IDalvikInstruction)this.nf.get(var1);
   }

   @Override
   public IDalvikInstruction getInstructionAt(int var1) {
      return (IDalvikInstruction)CollectionUtil.binarySearch(this.nf, var1, new biq(this));
   }

   public bie RF(int var1) {
      for (bie var3 : this.nf) {
         if (var3.getOffset() == var1) {
            return var3;
         }
      }

      return null;
   }

   @Deprecated
   public int RF() {
      return this.RF;
   }

   @Override
   public int getRegisterCount() {
      return this.RF;
   }

   public int xK() {
      return this.xK;
   }

   @Override
   public int getInputArgumentCount() {
      return this.xK();
   }

   public int Dw() {
      return this.Dw;
   }

   @Override
   public int getOutputArgumentCount() {
      return this.Dw();
   }

   public biw[] Uv() {
      return this.gP;
   }

   @Override
   public List getExceptionItems() {
      return Arrays.asList(this.gP);
   }

   @Override
   public IDexDebugInfo getDebugInfo() {
      if (this.Uv != null) {
         return this.Uv;
      } else {
         return this.LK != null ? this.LK.q() : null;
      }
   }

   public static String xK(int var0) {
      return Formatter.toHexString(var0, true);
   }
}
