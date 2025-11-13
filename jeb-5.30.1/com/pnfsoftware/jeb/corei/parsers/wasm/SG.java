package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Ser
public class SG implements IInstruction {
   public static final int q = 1;
   @SerTransient
   ME RF;
   @SerId(1)
   byte[] xK;
   @SerId(2)
   ct[] Dw;
   @SerId(3)
   int Uv;
   @SerId(4)
   int oW;
   @SerId(5)
   int gO;
   @SerId(6)
   int nf;
   @SerId(7)
   int gP;
   @SerId(8)
   int za;
   @SerId(9)
   int lm;
   @SerId(10)
   List zz;
   @SerId(11)
   SG JY;
   @SerId(12)
   SG HF;
   @SerId(13)
   List LK;

   @SerCustomInitPostGraph
   private void zz() {
      this.RF = (ME)ME.q.get(this.xK[0] & 255);
   }

   public SG(ME var1) {
      this.RF = var1;
   }

   public int q() {
      return this.RF.RF;
   }

   public int RF() {
      return this.Uv;
   }

   public int xK() {
      return this.Uv + this.getSize();
   }

   void q(int var1) {
      if (this.zz == null) {
         this.zz = new ArrayList(1);
      }

      this.zz.add(var1);
   }

   public boolean Dw() {
      return this.RF.RF == 11;
   }

   public boolean Uv() {
      return this.RF.RF == 4;
   }

   public boolean oW() {
      return this.Uv() && (this.lm & 1) == 0;
   }

   public boolean gO() {
      return this.Uv() && (this.lm & 1) != 0;
   }

   @Override
   public int getProcessorMode() {
      return 32;
   }

   @Override
   public int getSize() {
      return this.xK.length;
   }

   @Override
   public byte[] getCode() {
      return this.xK;
   }

   @Override
   public String getPrefix() {
      return null;
   }

   @Override
   public String getMnemonic() {
      return this.RF.oW;
   }

   public ct[] nf() {
      return this.Dw;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      if (this.gO == -1) {
         Assert.a(this.Dw());
         return new FlowInformation();
      } else if (this.q() == 15) {
         return new FlowInformation();
      } else {
         long var3 = var1 + this.getSize();
         if (this.zz == null) {
            return FlowInformation.NONE;
         } else {
            FlowInformation var5 = new FlowInformation();
            if (this.q() == 4 || this.q() == 13) {
               var5.addTarget(new CodePointer(var3));
            }

            for (int var7 : this.zz) {
               int var8 = var7 - this.xK();
               long var9 = var3 + var8;
               var5.addTarget(new CodePointer(var9));
            }

            return var5;
         }
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      return this.q() == 16 ? new FlowInformation() : FlowInformation.NONE;
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return this.q() == 17 ? new FlowInformation() : FlowInformation.NONE;
   }

   public Set gP() {
      return Collections.emptySet();
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   @Override
   public String format(Object var1) {
      Long var2 = (Long)var1;
      long var3 = var2 == null ? 0L : var2;
      StringBuilder var5 = new StringBuilder();
      Strings.ff(var5, "%s ", this.oW < 0 ? "-" : Integer.toString(this.oW));
      var5.append(Strings.generate(' ', this.oW * 2));
      var5.append(this.getMnemonic());
      var5.append(" ");
      int var6 = 0;

      for (ct var10 : this.nf()) {
         String var11 = var10.format(this, var3);
         if (!var11.isEmpty()) {
            if (var6 >= 1) {
               var5.append(", ");
            }

            var5.append(var10.format(this, var3));
         }

         var6++;
      }

      return Strings.ff("%-30s     [%d,%d]", var5.toString(), this.nf, this.gP);
   }

   @Override
   public String toString() {
      return this.format(null);
   }

   public int za() {
      int var1 = ((Long)this.nf()[0].Dw()).intValue();
      if (var1 < 0) {
         throw new RuntimeException();
      } else {
         return var1;
      }
   }

   public int lm() {
      return ((Long)this.nf()[0].Dw()).intValue();
   }

   static {
      Fw.q = true;
   }
}
