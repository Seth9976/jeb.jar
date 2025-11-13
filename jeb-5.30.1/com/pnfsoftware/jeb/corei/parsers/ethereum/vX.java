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
public class vX implements IInstruction {
   public static final int q = 0;
   public static final int RF = 1;
   public static final int xK = 1;
   public static final int Dw = 2;
   public static final int Uv = 4;
   public static final int oW = 8;
   public static final int gO = 16;
   @SerTransient
   CI nf;
   @SerId(1)
   byte[] gP;
   @SerId(2)
   KZ[] za;
   @SerId(3)
   BigInteger lm;
   @SerId(4)
   int zz;
   @SerId(5)
   int JY;
   @SerId(6)
   Integer HF;

   @SerCustomInitPostGraph
   private void zz() {
      this.nf = (CI)CI.q.get(this.gP[0] & 255);
   }

   public vX(CI var1) {
      this.nf = var1;
   }

   @Override
   public int getProcessorMode() {
      return 256;
   }

   @Override
   public int getSize() {
      return this.gP.length;
   }

   @Override
   public byte[] getCode() {
      return this.gP;
   }

   @Override
   public String getPrefix() {
      return null;
   }

   public int q() {
      return this.nf.RF;
   }

   public Integer RF() {
      return this.nf.RF >= 144 && this.nf.RF <= 159 ? this.nf.RF - 144 + 1 : null;
   }

   public Integer xK() {
      return this.nf.RF >= 128 && this.nf.RF <= 143 ? this.nf.RF - 128 + 1 : null;
   }

   public Integer Dw() {
      return this.nf.RF >= 96 && this.nf.RF <= 127 ? this.nf.RF - 96 + 1 : null;
   }

   @Override
   public String getMnemonic() {
      return this.Uv() ? "0x" + Integer.toHexString(this.nf.q()) : this.nf.xK();
   }

   public boolean Uv() {
      return this.nf.gO == null;
   }

   public boolean oW() {
      int var1 = this.q();
      return var1 == 86 || var1 == 87;
   }

   public boolean gO() {
      int var1 = this.q();
      return var1 == 86 || var1 == 87 || var1 == 0 || var1 == 243 || var1 == 253 || var1 == 254 || var1 == 255 || this.Uv();
   }

   public boolean nf() {
      int var1 = this.q();
      return var1 == 86 || var1 == 0 || var1 == 243 || var1 == 253 || var1 == 254 || var1 == 255 || this.Uv();
   }

   public boolean gP() {
      int var1 = this.q();
      return var1 == 0 || var1 == 243 || var1 == 253 || var1 == 254 || var1 == 255 || this.Uv();
   }

   @Override
   public IInstructionOperand[] getOperands() {
      return this.za == null ? new KZ[0] : this.za;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      if ((this.JY & 1) != 0) {
         return FlowInformation.NONE;
      } else {
         int var3 = this.q();
         if (var3 != 86 && var3 != 87 && var3 != 0 && var3 != 243 && var3 != 253 && var3 != 254 && var3 != 255 && !this.Uv()) {
            return FlowInformation.NONE;
         } else {
            FlowInformation var4 = new FlowInformation();
            if (var3 == 87) {
               var4.addTarget(new CodePointer(var1 + this.getSize()));
            }

            if ((var3 == 87 || var3 == 86) && this.HF != null) {
               var4.addTarget(new CodePointer(this.HF.intValue()));
            }

            return var4;
         }
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      if ((this.JY & 1) == 0) {
         return FlowInformation.NONE;
      } else {
         FlowInformation var3 = new FlowInformation();
         if (this.HF != null) {
            var3.addTarget(new CodePointer(this.HF.intValue()));
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

   public Set za() {
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
      return this.q(var1, 0);
   }

   public String q(Object var1, int var2) {
      long var3 = var1 == null ? this.zz : (Long)var1;
      String var5;
      if (this.lm == null) {
         var5 = this.getMnemonic();
      } else {
         var5 = Strings.ff("%s %s", this.getMnemonic(), this.getOperands()[0].format(this, var3));
      }

      if (var2 == 1) {
         if (this.HF != null) {
            var5 = var5 + Strings.ff("               [-> 0x%X]", this.HF);
         }

         if ((this.JY & 1) != 0) {
            var5 = var5 + "  [CALL_SUBROUTINE]";
         }

         if ((this.JY & 16) != 0) {
            var5 = var5 + "  [MAY_NOT_RETURN!]";
         }

         if ((this.JY & 8) != 0) {
            var5 = var5 + "  [DOES_NOT_RETURN!]";
         }

         if ((this.JY & 2) != 0) {
            var5 = var5 + "  [RETURN]";
         } else if (this.HF == null && this.oW()) {
            if ((this.JY & 4) != 0) {
               var5 = var5 + "  [ILLEGAL!]";
            } else {
               var5 = var5 + "  [UNRESOLVED!]";
            }
         }
      }

      return var5;
   }

   public qx.CU lm() {
      switch (this.nf.xK & 3) {
         case 0:
            return qx.CU.q;
         case 1:
            return qx.CU.RF;
         case 2:
            return qx.CU.xK;
         default:
            return null;
      }
   }

   static {
      zJ.q = true;
   }
}
