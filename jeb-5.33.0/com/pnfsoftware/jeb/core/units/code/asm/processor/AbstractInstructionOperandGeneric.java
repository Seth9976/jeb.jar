package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class AbstractInstructionOperandGeneric implements IInstructionOperandGeneric {
   @SerId(1)
   protected int type;
   @SerId(2)
   protected int bitsize;
   @SerId(3)
   protected long value;

   public AbstractInstructionOperandGeneric(int var1, int var2, long var3) {
      this.type = var1;
      this.bitsize = var2;
      this.value = var3;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.bitsize;
      var1 = 31 * var1 + this.type;
      return 31 * var1 + (int)(this.value ^ this.value >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         AbstractInstructionOperandGeneric var2 = (AbstractInstructionOperandGeneric)var1;
         if (this.bitsize != var2.bitsize) {
            return false;
         } else {
            return this.type != var2.type ? false : this.value == var2.value;
         }
      }
   }

   @Override
   public int getOperandType() {
      return this.type;
   }

   @Override
   public int getOperandBitsize() {
      return this.bitsize;
   }

   @Override
   public long getOperandValue() {
      return this.value;
   }

   @Override
   public long getOperandValue(long var1) {
      return this.type == 3 ? this.getOperandValue() + var1 : this.getOperandValue();
   }

   @Override
   public String format(IInstruction var1, long var2) {
      StringBuilder var4 = new StringBuilder();
      String var5 = this.getPrefix(var1);
      if (var5 != null) {
         var4.append(var5);
      }

      var4.append(this.formatOperand(var1, var2));
      String var6 = this.getSuffix(var1);
      if (var6 != null) {
         var4.append(var6);
      }

      return var4.toString();
   }

   protected CharSequence formatOperand(IInstruction var1, long var2) {
      long var4 = this.getOperandValue(var2);
      switch (this.type) {
         case 0:
            return Strings.safe(this.getRegisterName(var4));
         case 1:
         case 9:
            if (var4 >= 0L && var4 < 10L) {
               return Long.toString(var4);
            } else if (this.getOperandBitsize() <= 16) {
               return Formatter.toHexString((short)var4, true, 2) + "h";
            } else {
               if (this.getOperandBitsize() <= 32) {
                  return Formatter.toHexString((int)var4, true, 2) + "h";
               }

               return Formatter.toHexString(var4, true, 2) + "h";
            }
         case 2:
            return Formatter.toHexString(var4, true) + "h";
         case 3:
            if (var2 == 0L && var4 < 0L) {
               return "-" + Formatter.toHexString(-var4, true) + "h";
            }

            return Formatter.toHexString(var4, true) + "h";
         case 4:
            return "[" + Strings.safe(this.getRegisterName(var4)) + "]";
         case 5:
            return "[" + Formatter.toHexString(var4, true) + "h]";
         case 6:
            return Strings.safe(this.getAlias(var4));
         case 7:
         case 8:
         default:
            return Strings.ff("<???>(type=%d,value=%d,bitsize=%d)", this.getOperandType(), var4, this.getOperandBitsize());
      }
   }

   @Override
   public String getPrefix(IInstruction var1) {
      return null;
   }

   @Override
   public String getSuffix(IInstruction var1) {
      return null;
   }

   @Override
   public String getAlias(long var1) {
      return null;
   }

   @Override
   public String toString() {
      return this.format(null, 0L);
   }

   protected final String innerFormat() {
      long var1 = this.getOperandValue();
      switch (this.type) {
         case 2:
         case 3:
            if (var1 > 0L) {
               return "+" + Formatter.toHexString(var1, true) + "h";
            }

            return "-" + Formatter.toHexString(-var1, true) + "h";
         default:
            return this.format(null, 0L);
      }
   }
}
