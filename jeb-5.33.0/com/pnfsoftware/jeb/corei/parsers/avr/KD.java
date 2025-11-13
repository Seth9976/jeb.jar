package com.pnfsoftware.jeb.corei.parsers.avr;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Ser
public class KD implements IInstruction {
   @SerId(1)
   String pC;
   @SerId(2)
   byte[] A;
   @SerId(3)
   yt[] kS;
   @SerId(4)
   int wS;
   @SerTransient
   private int UT;

   KD(String var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
      }
   }

   void pC(yt var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.kS[this.UT++] = var1;
      }
   }

   void pC(yt... var1) {
      for (yt var5 : var1) {
         this.pC(var5);
      }
   }

   @Override
   public int getProcessorMode() {
      return 8;
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
      return this.pC;
   }

   public yt[] pC() {
      return this.kS;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      FlowInformation var3 = new FlowInformation();
      String var4 = this.getMnemonic();
      switch (var4) {
         case "ijmp":
         case "ret":
         case "reti":
            break;
         case "jmp":
            var3.addTarget(new CodePointer(this.kS[0].getOperandValue()));
            break;
         case "rjmp":
            var3.addTarget(new CodePointer(var1 + this.kS[0].getOperandValue()));
            break;
         case "breq":
         case "brne":
         case "brcs":
         case "brcc":
         case "brsh":
         case "brlo":
         case "brmi":
         case "brpl":
         case "brge":
         case "brlt":
         case "brhs":
         case "brhc":
         case "brts":
         case "brtc":
         case "brvs":
         case "brvc":
         case "brie":
         case "brid":
            var3.addTarget(new CodePointer(var1 + 2L));
            var3.addTarget(new CodePointer(var1 + this.kS[0].getOperandValue()));
            break;
         case "cpse":
         case "sbrc":
         case "sbrs":
         case "sbic":
         case "sbis":
            var3.addTarget(new CodePointer(var1 + 2L));
            var3.addTarget(new CodePointer(var1 + 2L + this.wS));
            break;
         default:
            return FlowInformation.NONE;
      }

      return var3;
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      FlowInformation var3 = new FlowInformation();
      String var4 = this.getMnemonic();
      switch (var4) {
         case "icall":
            break;
         case "call":
            var3.addTarget(new CodePointer(this.kS[0].getOperandValue()));
            break;
         case "rcall":
            var3.addTarget(new CodePointer(var1 + this.kS[0].getOperandValue()));
            break;
         default:
            return FlowInformation.NONE;
      }

      return var3;
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      throw new UnsupportedOperationException();
   }

   public Set A() {
      return Collections.emptySet();
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   @Override
   public String format(Object var1) {
      long var2 = var1 instanceof Long ? (Long)var1 : 0L;
      StringBuilder var4 = new StringBuilder();
      String var5 = this.getPrefix();
      if (var5 != null) {
         var4.append(var5);
         var4.append(' ');
      }

      var4.append(this.getMnemonic());
      if (this.kS != null) {
         var4.append(" ");
         int var6 = 0;

         for (yt var10 : this.kS) {
            if (var6 >= 1) {
               var4.append(", ");
            }

            var4.append(var10.format(this, var2));
            var6++;
         }
      }

      return var4.toString();
   }

   @Override
   public String toString() {
      return this.format(null);
   }
}
