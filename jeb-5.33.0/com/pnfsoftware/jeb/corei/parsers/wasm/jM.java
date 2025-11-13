package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class jM implements IInstructionOperand {
   @SerId(1)
   private int pC;
   @SerId(2)
   private Object A;

   public jM(int var1, Object var2) {
      if (var2 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   public int pC() {
      return this.pC & 0xFF;
   }

   public int A() {
      return this.pC >> 8 & 0xFF;
   }

   public Object kS() {
      return this.A;
   }

   @Override
   public String format(IInstruction var1, long var2) {
      if (!(var1 instanceof m var4)) {
         throw new RuntimeException();
      } else {
         int var5 = this.pC >> 8 & 0xFF;
         switch (var5) {
            case 1:
               return this.A.toString();
            case 2:
               int var13 = ((Long)this.A).intValue();
               return "$(" + var13 + ")";
            case 3:
               return "$f" + this.A;
            case 4:
               return "$t" + this.A;
            case 5:
               return "$" + this.A;
            case 6:
               return "$g" + this.A;
            case 7:
               int var12 = ((Long)this.A).intValue();
               int var15 = var4.pC();
               String var7;
               if (var15 == 2) {
                  var7 = "$B";
               } else if (var15 == 3) {
                  var7 = "$L";
               } else {
                  if (var15 != 4) {
                     throw new RuntimeException();
                  }

                  var7 = "$I";
               }

               var7 = var7 + var4.ld;
               if (var12 != 64) {
                  var7 = var7 + "(" + Tb.kS(var12) + ")";
               }

               return var7;
            case 8:
               long var6 = (Long)this.A;
               int var8 = (int)(var6 >>> 32);
               int var9 = 1 << var8;
               long var10 = var6 & 4294967295L;
               return Strings.ff("@%Xh(%d)", var10, var9);
            case 9:
               return "(reserved)";
            case 10:
            default:
               return this.A + "";
            case 11:
               return this.A + "";
            case 12:
               return this.A + "";
            case 13:
               return this.A + "";
            case 14:
               return this.A + "";
            case 15:
               return this.A + "";
         }
      }
   }
}
