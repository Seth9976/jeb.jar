package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.List;

@Ser
public class yt extends AbstractInstruction {
   @SerTransient
   qt.Av pC;
   @SerId(1)
   int A;

   @SerCustomInitPostGraph
   private void pC() {
      this.pC = qt.pC(this.A, super.getMnemonic());
   }

   public yt(BytesBlock var1, qt.Av var2, RC[] var3) {
      super(var1, var2.kS, var3, 32);
      this.A = var2.pC;
      this.pC = var2;
   }

   @Override
   public String getMnemonic() {
      String var1 = null;
      switch (this.pC.A & 0xFF) {
         case 4:
            int var8 = ((this.getCode()[1] & 255) >>> 5) - 1;
            var1 = this.pC.sY[var8];
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 11:
         case 12:
         case 14:
         case 15:
         case 17:
         case 18:
         case 19:
         case 21:
         case 23:
         default:
            break;
         case 10:
         case 13:
            int var7 = this.getCode()[1] & 255;
            if (var7 < this.pC.sY.length) {
               var1 = this.pC.sY[var7];
            }
            break;
         case 16:
            int var6 = this.getCode()[1] & 255;
            int var9 = var6 & 7 | (var6 & 128) >> 4;
            if (var9 < this.pC.sY.length) {
               var1 = this.pC.sY[var9];
            }
            break;
         case 20:
            int var5 = this.getCode()[1] & 255;
            var1 = (var5 & 128) == 0 ? "L" : "T";
            break;
         case 22:
            int var4 = this.getCode()[1] & 255;
            var1 = (var4 & 4) == 0 ? "L" : "T";
            break;
         case 24:
            int var2 = this.getCode()[1] & 255;
            int var3 = var2 >> 3 & 16 | var2 & 15;
            var1 = this.pC.sY[var3];
      }

      if (var1 == null) {
         var1 = super.getMnemonic();
      }

      if (var1 == null) {
         var1 = "?";
      }

      return var1;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      String var3 = this.getMnemonic();
      switch (var3) {
         case "JU":
            FlowInformation var10 = new FlowInformation();
            int var12 = ((RC)this.getOperand(0)).kS();
            var10.addTarget(new CodePointer(var1 + 2 * var12));
            return var10;
         case "JL":
            Assert.a(this.getSize() == 4, "JL expected to be 4 bytes");
            FlowInformation var9 = new FlowInformation();
            int var11 = ((RC)this.getOperand(0)).kS();

            for (int var8 = 1; var8 <= var11 + 1; var8++) {
               var9.addTarget(new CodePointer(var1 + 4 * var8));
            }

            return var9;
         case "JC":
         case "JCN":
         case "JCB":
         case "JNB":
         case "JBI":
         case "JNBI":
         case "JO":
         case "JOS":
         case "JZ":
         case "JN":
         case "JP":
         case "JM":
         case "JPZ":
         case "JMZ":
         case "JUO":
         case "LOOP":
            FlowInformation var6 = new FlowInformation();
            int var7 = ((RC)this.getOperand(0)).kS();
            var6.addTarget(new CodePointer(var1 + this.getSize()));
            var6.addTarget(new CodePointer(var1 + 2 * var7));
            return var6;
         case "BE":
         case "BEU":
            return FlowInformation.EMPTY;
         default:
            return FlowInformation.NONE;
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      String var3 = this.getMnemonic();
      switch (var3) {
         case "UC":
         case "CC":
            return new FlowInformation();
         default:
            return FlowInformation.NONE;
      }
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   public List pC(long var1, IVirtualMemory var3) {
      if (!Strings.isContainedIn(this.getMnemonic(), "UC", "CC")) {
         return null;
      } else {
         int var4 = ((RC)this.getOperand(0)).A();
         if (var4 != 10 && var4 != 12) {
            return null;
         } else {
            ArrayList var5 = new ArrayList();
            long var6 = var1 + this.getSize();
            int[] var8 = new int[1];
            VirtualMemoryUtil.readLEIntSafe(var3, var6, var8);
            if ((var8[0] & 65535) == 2928) {
               int var9 = (var8[0] >>> 24) + (var8[0] >>> 8 & 0xFF00);
               int var10 = var9 * 2;

               for (long var11 = var6 + 4L; var11 < var6 + var10; var11 += 4L) {
                  if (VirtualMemoryUtil.readBEIntSafe(var3, var11, var8)) {
                     var5.add(var8[0]);
                  }
               }
            }

            return var5;
         }
      }
   }
}
