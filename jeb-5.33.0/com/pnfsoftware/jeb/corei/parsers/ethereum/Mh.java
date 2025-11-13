package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Ser
public class Mh implements IInstruction {
   @SerTransient
   GK pC;
   @SerId(1)
   byte[] A;
   @SerId(2)
   Tb[] kS;
   @SerId(3)
   BigInteger wS;
   @SerId(4)
   int UT;
   @SerId(5)
   int E;
   @SerId(6)
   Integer sY;

   @SerCustomInitPostGraph
   private void oT() {
      this.pC = (GK)GK.pC.get(this.A[0] & 255);
   }

   public Mh(GK var1) {
      this.pC = var1;
   }

   @Override
   public int getProcessorMode() {
      return 256;
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

   public int pC() {
      return this.pC.A;
   }

   public Integer A() {
      return this.pC.A >= 144 && this.pC.A <= 159 ? this.pC.A - 144 + 1 : null;
   }

   public Integer kS() {
      return this.pC.A >= 128 && this.pC.A <= 143 ? this.pC.A - 128 + 1 : null;
   }

   public Integer wS() {
      return this.pC.A >= 96 && this.pC.A <= 127 ? this.pC.A - 96 + 1 : null;
   }

   @Override
   public String getMnemonic() {
      return this.UT() ? "0x" + Integer.toHexString(this.pC.pC()) : this.pC.A();
   }

   public boolean UT() {
      return this.pC.sY == null;
   }

   public boolean E() {
      int var1 = this.pC();
      return var1 == 86 || var1 == 87;
   }

   public boolean sY() {
      int var1 = this.pC();
      return var1 == 86 || var1 == 87 || var1 == 0 || var1 == 243 || var1 == 253 || var1 == 254 || var1 == 255 || this.UT();
   }

   public boolean ys() {
      int var1 = this.pC();
      return var1 == 0 || var1 == 243 || var1 == 253 || var1 == 254 || var1 == 255 || this.UT();
   }

   @Override
   public IInstructionOperand[] getOperands() {
      return this.kS == null ? new Tb[0] : this.kS;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      if ((this.E & 1) != 0) {
         return FlowInformation.NONE;
      } else {
         int var3 = this.pC();
         if (var3 != 86 && var3 != 87 && var3 != 0 && var3 != 243 && var3 != 253 && var3 != 254 && var3 != 255 && !this.UT()) {
            return FlowInformation.NONE;
         } else {
            FlowInformation var4 = new FlowInformation();
            if (var3 == 87) {
               var4.addTarget(new CodePointer(var1 + this.getSize()));
            }

            if ((var3 == 87 || var3 == 86) && this.sY != null) {
               var4.addTarget(new CodePointer(this.sY.intValue()));
            }

            return var4;
         }
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      if ((this.E & 1) == 0) {
         return FlowInformation.NONE;
      } else {
         FlowInformation var3 = new FlowInformation();
         if (this.sY != null) {
            var3.addTarget(new CodePointer(this.sY.intValue()));
         }

         return var3;
      }
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
   }

   public Set ld() {
      return Collections.emptySet();
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   @Override
   public String toString() {
      return this.format(null);
   }

   @Override
   public String format(Object var1) {
      return this.pC(var1, 0);
   }

   public String pC(Object var1, int var2) {
      long var3 = var1 == null ? this.UT : (Long)var1;
      String var5;
      if (this.wS == null) {
         var5 = this.getMnemonic();
      } else {
         var5 = Strings.ff("%s %s", this.getMnemonic(), this.getOperands()[0].format(this, var3));
      }

      if (var2 == 1) {
         if (this.sY != null) {
            var5 = var5 + Strings.ff("               [-> 0x%X]", this.sY);
         }

         if ((this.E & 1) != 0) {
            var5 = var5 + "  [CALL_SUBROUTINE]";
         }

         if ((this.E & 16) != 0) {
            var5 = var5 + "  [MAY_NOT_RETURN!]";
         }

         if ((this.E & 8) != 0) {
            var5 = var5 + "  [DOES_NOT_RETURN!]";
         }

         if ((this.E & 2) != 0) {
            var5 = var5 + "  [RETURN]";
         } else if (this.sY == null && this.E()) {
            if ((this.E & 4) != 0) {
               var5 = var5 + "  [ILLEGAL!]";
            } else {
               var5 = var5 + "  [UNRESOLVED!]";
            }
         }
      }

      return var5;
   }

   public eW.Sv gp() {
      switch (this.pC.kS & 3) {
         case 0:
            return eW.Sv.pC;
         case 1:
            return eW.Sv.A;
         case 2:
            return eW.Sv.kS;
         default:
            return null;
      }
   }

   static {
      uX.pC = true;
   }
}
