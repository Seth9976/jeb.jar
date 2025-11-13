package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ct implements IInstructionOperand {
   @SerId(1)
   private int q;
   @SerId(2)
   private Object RF;

   public ct(int var1, Object var2) {
      if (var2 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   public int q() {
      return this.q;
   }

   public int RF() {
      return this.q & 0xFF;
   }

   public int xK() {
      return this.q >> 8 & 0xFF;
   }

   public Object Dw() {
      return this.RF;
   }

   @Override
   public String format(IInstruction var1, long var2) {
      if (!(var1 instanceof SG var4)) {
         throw new RuntimeException();
      } else {
         int var5 = this.q >> 8 & 0xFF;
         switch (var5) {
            case 1:
               return this.RF.toString();
            case 2:
               int var13 = ((Long)this.RF).intValue();
               return "$(" + var13 + ")";
            case 3:
               return "$f" + this.RF;
            case 4:
               return "$t" + this.RF;
            case 5:
               return "$" + this.RF;
            case 6:
               return "$g" + this.RF;
            case 7:
               int var12 = ((Long)this.RF).intValue();
               int var15 = var4.q();
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

               var7 = var7 + var4.za;
               if (var12 != 64) {
                  var7 = var7 + "(" + Xa.xK(var12) + ")";
               }

               return var7;
            case 8:
               long var6 = (Long)this.RF;
               int var8 = (int)(var6 >>> 32);
               int var9 = 1 << var8;
               long var10 = var6 & 4294967295L;
               return Strings.ff("@%Xh(%d)", var10, var9);
            case 9:
               return "(reserved)";
            case 10:
            default:
               return this.RF + "";
            case 11:
               return this.RF + "";
            case 12:
               return this.RF + "";
            case 13:
               return this.RF + "";
            case 14:
               return this.RF + "";
            case 15:
               return this.RF + "";
         }
      }
   }
}
