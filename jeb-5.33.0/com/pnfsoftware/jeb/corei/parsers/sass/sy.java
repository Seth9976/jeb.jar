package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class sy implements IInstruction {
   @SerId(1)
   byte[] pC;
   @SerId(2)
   String A;
   @SerId(3)
   String kS;
   @SerId(4)
   boolean wS;
   @SerId(5)
   boolean UT;
   @SerId(6)
   int E;
   @SerId(7)
   List sY = new ArrayList();
   @SerId(8)
   oP[] ys = new oP[0];
   @SerId(9)
   Boolean ld;
   @SerId(10)
   List gp = new ArrayList();

   public sy(byte[] var1, String var2, String var3) {
      Assert.a(var1 != null && var1.length == 16);
      Assert.a(!Strings.isBlank(var2));
      Assert.a(!Strings.isBlank(var3));
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public int getProcessorMode() {
      return 64;
   }

   @Override
   public int getSize() {
      return this.pC.length;
   }

   @Override
   public byte[] getCode() {
      return this.pC;
   }

   @Override
   public String getPrefix() {
      return this.E == 7 && !this.wS ? null : "@" + (this.wS ? "!" : "") + (this.UT ? "UP" : "P") + (this.E != 7 ? this.E : "T");
   }

   public String pC(String var1) {
      if (var1 != null) {
         for (qt var3 : this.sY) {
            if (var1.equalsIgnoreCase(var3.pC)) {
               return var3.A;
            }
         }
      }

      return null;
   }

   @Override
   public String getMnemonic() {
      return this.kS;
   }

   public String pC(boolean var1) {
      String var2 = "";

      for (qt var4 : this.sY) {
         if (var1 || !var4.kS) {
            var2 = var2 + "." + var4.A;
         }
      }

      return this.kS + var2;
   }

   @Override
   public IInstructionOperand[] getOperands() {
      return this.ys;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      return this.pC(var1, 0);
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      return this.pC(var1, 1);
   }

   IFlowInformation pC(long var1, int var3) {
      String var4 = this.kS;
      switch (var4) {
         case "BRA":
         case "BRX":
         case "BRXU":
         case "EXIT":
         case "RET":
         case "CALL":
            boolean var12;
            if (this.E != 7) {
               var12 = true;
            } else {
               if (this.E != 7 || this.wS) {
                  return FlowInformation.NONE;
               }

               var12 = false;
            }

            if (!var12 && (this.ys[0].getOperandValue() & 255L) != 7L) {
               var12 = true;
            }

            long var13 = var1 + 16L;
            Long var7 = null;

            for (oP var11 : this.ys) {
               if (var11.getOperandType() == 4097) {
                  var7 = var13 + var11.getOperandValue();
                  break;
               }
            }

            if (var3 == 0) {
               String var14 = this.kS;
               switch (var14) {
                  case "BRA":
                     FlowInformation var19 = new FlowInformation();
                     if (var12 || Strings.contains(this.pC(true), ".DIV", ".CONV", ".U")) {
                        var19.addTarget(new CodePointer(var13));
                     }

                     if (var7 != null) {
                        var19.addTarget(new CodePointer(var7));
                     }

                     return var19;
                  case "BRX":
                  case "BRXU":
                     return new FlowInformation();
                  case "EXIT":
                  case "RET":
                     FlowInformation var18 = new FlowInformation();
                     if (var12) {
                        var18.addTarget(new CodePointer(var13));
                     }

                     return var18;
               }
            } else if (var3 == 1) {
               String var15 = this.kS;
               byte var17 = -1;
               switch (var15.hashCode()) {
                  case 2060894:
                     if (var15.equals("CALL")) {
                        var17 = 0;
                     }
                  default:
                     switch (var17) {
                        case 0:
                           FlowInformation var20 = new FlowInformation();
                           if (var7 != null) {
                              var20.addTarget(new CodePointer(var7));
                           }

                           return var20;
                     }
               }
            }

            return FlowInformation.NONE;
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
   public Collection getInstructionFlags() {
      return Collections.emptyList();
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   @Override
   public String format(Object var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append(this.getMnemonic());
      return var2.toString();
   }
}
