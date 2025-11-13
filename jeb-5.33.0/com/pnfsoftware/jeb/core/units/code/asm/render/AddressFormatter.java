package com.pnfsoftware.jeb.core.units.code.asm.render;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.C;

@Ser
public class AddressFormatter {
   @SerId(1)
   private AddressFormatter.AddressBase base;
   @SerId(2)
   private String relativePrefix;
   @SerId(3)
   private NumberFormatter.HexaNotationType hexaNotationType;

   public AddressFormatter() {
      this(AddressFormatter.AddressBase.LABEL, "PC+", NumberFormatter.HexaNotationType._h_suffix);
   }

   public AddressFormatter(AddressFormatter.AddressBase var1, String var2, NumberFormatter.HexaNotationType var3) {
      this.base = var1;
      this.relativePrefix = var2;
      this.hexaNotationType = var3;
   }

   public void setBase(AddressFormatter.AddressBase var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.base = var1;
      }
   }

   public AddressFormatter.AddressBase getBase() {
      return this.base;
   }

   public void setRelativePrefix(String var1) {
      if (var1 == null) {
         var1 = "";
      }

      this.relativePrefix = var1;
   }

   public String getRelativePrefix() {
      return this.relativePrefix;
   }

   public void setHexaNotationType(NumberFormatter.HexaNotationType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.hexaNotationType = var1;
      }
   }

   public NumberFormatter.HexaNotationType getHexaNotationType() {
      return this.hexaNotationType;
   }

   public String format(INativeCodeUnit var1, int var2, long var3, IInstructionOperandGeneric var5) {
      return this.format(var1, var2, var3, var5, this.base);
   }

   public String format(int var1, long var2, IInstructionOperandGeneric var4) {
      return this.base != null && this.base != AddressFormatter.AddressBase.LABEL
         ? this.format(null, var1, var2, var4, this.base)
         : this.format(null, var1, var2, var4, AddressFormatter.AddressBase.ABSOLUTE_ADDRESS);
   }

   public String format(INativeCodeUnit var1, int var2, long var3, IInstructionOperandGeneric var5, AddressFormatter.AddressBase var6) {
      if (var6 == null) {
         var6 = this.base;
      }

      if (var5 == null && (var6 == AddressFormatter.AddressBase.RELATIVE_ADDRESS_D || var6 == AddressFormatter.AddressBase.RELATIVE_ADDRESS_H)) {
         var6 = AddressFormatter.AddressBase.ABSOLUTE_ADDRESS;
      }

      switch (var6) {
         case LABEL:
         default:
            return ((C)var1).wS(var3);
         case ABSOLUTE_ADDRESS:
            return Strings.f("%s%X%s", this.hexaNotationType.prefix(), this.getValue(var2, var3), this.hexaNotationType.suffix());
         case RELATIVE_ADDRESS_H:
            return Strings.f(
               "%s%s%X%s", this.relativePrefix, this.hexaNotationType.prefix(), this.getValue(var2, var5.getOperandValue()), this.hexaNotationType.suffix()
            );
         case RELATIVE_ADDRESS_D:
            long var7 = var5.getOperandValue();
            Long var9;
            if (var2 == 16) {
               var9 = (long)((short)var7);
            } else if (var2 == 32) {
               var9 = (long)((int)var7);
            } else {
               var9 = var7;
            }

            if (var9 >= 0L) {
               return Strings.f("%s%d", this.relativePrefix, var9);
            } else {
               String var10 = this.relativePrefix;
               int var11 = this.relativePrefix.lastIndexOf("+");
               if (var11 >= 0) {
                  var10 = Strings.replaceLast(this.relativePrefix, "+", "-");
                  var9 = -var9;
               }

               return Strings.f("%s%d", var10, var9);
            }
      }
   }

   private Long getValue(int var1, long var2) {
      Long var4;
      if (var1 == 16) {
         var4 = (short)var2 & 65535L;
      } else if (var1 == 32) {
         var4 = (int)var2 & 4294967295L;
      } else {
         var4 = var2;
      }

      return var4;
   }

   @Ser
   public static enum AddressBase {
      LABEL,
      ABSOLUTE_ADDRESS,
      RELATIVE_ADDRESS_H,
      RELATIVE_ADDRESS_D;

      public AddressFormatter.AddressBase nextBase() {
         switch (this) {
            case LABEL:
               return ABSOLUTE_ADDRESS;
            case ABSOLUTE_ADDRESS:
               return RELATIVE_ADDRESS_H;
            case RELATIVE_ADDRESS_H:
               return RELATIVE_ADDRESS_D;
            case RELATIVE_ADDRESS_D:
               return LABEL;
            default:
               throw new RuntimeException();
         }
      }
   }
}
