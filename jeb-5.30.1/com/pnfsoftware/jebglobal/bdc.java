package com.pnfsoftware.jebglobal;

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
public class bdc implements IInstruction {
   @SerId(1)
   String q;
   @SerId(2)
   byte[] RF;
   @SerId(3)
   bdd[] xK;
   @SerId(4)
   int Dw;
   @SerTransient
   private int Uv;

   bdc(String var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
      }
   }

   void q(bdd var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.xK[this.Uv++] = var1;
      }
   }

   void q(bdd... var1) {
      for (bdd var5 : var1) {
         this.q(var5);
      }
   }

   @Override
   public int getProcessorMode() {
      return 8;
   }

   @Override
   public int getSize() {
      return this.RF.length;
   }

   @Override
   public byte[] getCode() {
      return this.RF;
   }

   @Override
   public String getPrefix() {
      return null;
   }

   @Override
   public String getMnemonic() {
      return this.q;
   }

   public bdd[] q() {
      return this.xK;
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
            var3.addTarget(new CodePointer(this.xK[0].getOperandValue()));
            break;
         case "rjmp":
            var3.addTarget(new CodePointer(var1 + this.xK[0].getOperandValue()));
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
            var3.addTarget(new CodePointer(var1 + this.xK[0].getOperandValue()));
            break;
         case "cpse":
         case "sbrc":
         case "sbrs":
         case "sbic":
         case "sbis":
            var3.addTarget(new CodePointer(var1 + 2L));
            var3.addTarget(new CodePointer(var1 + 2L + this.Dw));
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
            var3.addTarget(new CodePointer(this.xK[0].getOperandValue()));
            break;
         case "rcall":
            var3.addTarget(new CodePointer(var1 + this.xK[0].getOperandValue()));
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

   public Set RF() {
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
      if (this.xK != null) {
         var4.append(" ");
         int var6 = 0;

         for (bdd var10 : this.xK) {
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
