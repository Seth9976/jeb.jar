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
public class m implements IInstruction {
   @SerTransient
   nA pC;
   @SerId(1)
   byte[] A;
   @SerId(2)
   jM[] kS;
   @SerId(3)
   int wS;
   @SerId(4)
   int UT;
   @SerId(5)
   int E;
   @SerId(6)
   int sY;
   @SerId(7)
   int ys;
   @SerId(8)
   int ld;
   @SerId(9)
   int gp;
   @SerId(10)
   List oT;
   @SerId(11)
   m fI;
   @SerId(12)
   m WR;
   @SerId(13)
   List NS;

   @SerCustomInitPostGraph
   private void fI() {
      this.pC = (nA)nA.pC.get(this.A[0] & 255);
   }

   public m(nA var1) {
      this.pC = var1;
   }

   public int pC() {
      return this.pC.A;
   }

   public int A() {
      return this.wS;
   }

   public int kS() {
      return this.wS + this.getSize();
   }

   void pC(int var1) {
      if (this.oT == null) {
         this.oT = new ArrayList(1);
      }

      this.oT.add(var1);
   }

   public boolean wS() {
      return this.pC.A == 11;
   }

   public boolean UT() {
      return this.pC.A == 4;
   }

   public boolean E() {
      return this.UT() && (this.gp & 1) == 0;
   }

   public boolean sY() {
      return this.UT() && (this.gp & 1) != 0;
   }

   @Override
   public int getProcessorMode() {
      return 32;
   }

   @Override
   public int getSize() {
      return this.A.length;
   }

   @Override
   public byte[] getCode() {
      return this.A;
   }

   @Override
   public String getPrefix() {
      return null;
   }

   @Override
   public String getMnemonic() {
      return this.pC.E;
   }

   public jM[] ys() {
      return this.kS;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      if (this.E == -1) {
         Assert.a(this.wS());
         return new FlowInformation();
      } else if (this.pC() == 15) {
         return new FlowInformation();
      } else {
         long var3 = var1 + this.getSize();
         if (this.oT == null) {
            return FlowInformation.NONE;
         } else {
            FlowInformation var5 = new FlowInformation();
            if (this.pC() == 4 || this.pC() == 13) {
               var5.addTarget(new CodePointer(var3));
            }

            for (int var7 : this.oT) {
               int var8 = var7 - this.kS();
               long var9 = var3 + var8;
               var5.addTarget(new CodePointer(var9));
            }

            return var5;
         }
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      return this.pC() == 16 ? new FlowInformation() : FlowInformation.NONE;
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return this.pC() == 17 ? new FlowInformation() : FlowInformation.NONE;
   }

   public Set ld() {
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
      Strings.ff(var5, "%s ", this.UT < 0 ? "-" : Integer.toString(this.UT));
      var5.append(Strings.generate(' ', this.UT * 2));
      var5.append(this.getMnemonic());
      var5.append(" ");
      int var6 = 0;

      for (jM var10 : this.ys()) {
         String var11 = var10.format(this, var3);
         if (!var11.isEmpty()) {
            if (var6 >= 1) {
               var5.append(", ");
            }

            var5.append(var10.format(this, var3));
         }

         var6++;
      }

      return Strings.ff("%-30s     [%d,%d]", var5.toString(), this.sY, this.ys);
   }

   @Override
   public String toString() {
      return this.format(null);
   }

   public int gp() {
      int var1 = ((Long)this.ys()[0].kS()).intValue();
      if (var1 < 0) {
         throw new RuntimeException();
      } else {
         return var1;
      }
   }

   public int oT() {
      return ((Long)this.ys()[0].kS()).intValue();
   }

   static {
      eW.pC = true;
   }
}
