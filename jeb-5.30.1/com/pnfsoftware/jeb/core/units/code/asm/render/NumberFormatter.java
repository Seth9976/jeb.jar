package com.pnfsoftware.jeb.core.units.code.asm.render;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;

@Ser
public class NumberFormatter {
   private static final int FLAG_FORCE_POSITIVE_RENDERING_FOR_NON_BASE10 = 1;
   @SerId(1)
   private NumberFormatter.NumberBase base;
   @SerId(2)
   boolean signedNumber;
   @SerId(3)
   private NumberFormatter.HexaNotationType hexaNotationType;
   @SerId(4)
   private ConstantsFormatter cf;
   @SerId(5)
   private int flags;
   @SerId(6)
   private Endianness endianness = Endianness.LITTLE_ENDIAN;

   public NumberFormatter() {
      this.base = NumberFormatter.NumberBase.HEXADECIMAL;
      this.signedNumber = false;
      this.hexaNotationType = NumberFormatter.HexaNotationType._h_suffix;
   }

   public NumberFormatter(NumberFormatter var1) {
      this.base = var1.base;
      this.signedNumber = var1.signedNumber;
      this.hexaNotationType = var1.hexaNotationType;
      this.flags = var1.flags;
      this.endianness = var1.endianness;
   }

   public NumberFormatter.NumberBase getBase() {
      return this.base;
   }

   public void setBase(NumberFormatter.NumberBase var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.cf = null;
         this.base = var1;
      }
   }

   public NumberFormatter.NumberBase rotateBase() {
      NumberFormatter.NumberBase var1 = this.getBase().nextBase();
      this.setBase(var1);
      return var1;
   }

   public void setSignedNumber(boolean var1) {
      this.cf = null;
      this.signedNumber = var1;
   }

   public boolean isSignedNumber() {
      return this.signedNumber;
   }

   public void setHexaNotationType(NumberFormatter.HexaNotationType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.cf = null;
         this.hexaNotationType = var1;
      }
   }

   public NumberFormatter.HexaNotationType getHexaNotationType() {
      return this.hexaNotationType;
   }

   public Endianness getEndianness() {
      return this.endianness;
   }

   public void setEndianness(Endianness var1) {
      this.endianness = var1;
   }

   public void setConstantsFormatterOverride(ConstantsFormatter var1) {
      this.cf = var1;
   }

   public boolean hasConstantsFormatterOverride() {
      return this.cf != null;
   }

   public void setForcePositiveRenderingForNonBase10(boolean var1) {
      this.flags |= 1;
   }

   public boolean getForcePositiveRenderingForNonBase10() {
      return (this.flags & 1) != 0;
   }

   public String format(int var1, long var2) {
      return this.format(var1, var2, this.base, this.signedNumber);
   }

   public String format(int var1, long var2, NumberFormatter.NumberBase var4, boolean var5) {
      if (this.cf != null) {
         return this.cf.format();
      } else {
         if (var4 == null) {
            var4 = this.base;
         }

         if (var1 < 64 && var2 < 0L) {
            var5 = true;
         }

         Long var6;
         if (var1 == 8) {
            var6 = (byte)var2 & 255L;
         } else if (var1 == 16) {
            var6 = (short)var2 & 65535L;
         } else if (var1 == 32) {
            var6 = (int)var2 & 4294967295L;
         } else {
            var6 = var2;
         }

         if (var4 != NumberFormatter.NumberBase.DECIMAL && var4 != NumberFormatter.NumberBase.HEXADECIMAL && var4 != NumberFormatter.NumberBase.OCTAL) {
            switch (var4) {
               case BINARY:
                  String var14 = Long.toBinaryString(var2);
                  if (var14.length() > var1) {
                     var14 = var14.substring(var14.length() - var1, var14.length());
                  }

                  return Strings.f("b'%s", var14);
               case ASCII:
                  if (var2 == 0L || var1 % 8 != 0) {
                     return Strings.f("%d", var6);
                  } else if (this.endianness == Endianness.BIG_ENDIAN) {
                     long var13 = var2;
                     byte[] var15 = new byte[var1 >>> 3];
                     int var16 = (var1 >>> 3) - 1;

                     do {
                        byte var17 = (byte)(var13 >>> var16 * 8);
                        var13 &= (1L << var16 * 8) - 1L;
                        var15[var15.length - (var16 + 1)] = var17;
                        var16--;
                     } while (var13 != 0L);

                     return Strings.f("'%s'", Formatter.escapeBytes(var15, 0, var15.length - (var16 + 1)));
                  } else {
                     long var12 = var2;
                     byte[] var9 = new byte[8];
                     int var10 = 0;

                     do {
                        byte var11 = (byte)var12;
                        var12 >>>= 8;
                        var9[var10] = var11;
                        var10++;
                     } while (var12 != 0L);

                     return Strings.f("'%s'", Formatter.escapeBytes(var9, 0, var10));
                  }
               default:
                  return Strings.f("%d", var6);
            }
         } else {
            boolean var7 = false;
            if (var5 && (var4 == NumberFormatter.NumberBase.DECIMAL || !this.getForcePositiveRenderingForNonBase10())) {
               boolean var8 = (var2 >> var1 - 1 & 1L) == 1L;
               if (var8) {
                  var6 = switch (var1) {
                     case 8 -> -(var6 | -256L);
                     case 16 -> -(var6 | -65536L);
                     case 32 -> -(var6 | -4294967296L);
                     default -> -var6;
                  };
                  var7 = true;
               }
            }

            if (var6 >= 0L) {
               if (var4 == NumberFormatter.NumberBase.HEXADECIMAL && var6 <= 9L) {
                  var4 = NumberFormatter.NumberBase.DECIMAL;
               } else if (var4 == NumberFormatter.NumberBase.OCTAL && var6 <= 7L) {
                  var4 = NumberFormatter.NumberBase.DECIMAL;
               }
            }

            switch (var4) {
               case OCTAL:
                  if (var7) {
                     return "-0" + Long.toOctalString(var6);
                  }

                  return "0" + Long.toOctalString(var6);
               case DECIMAL:
                  if (var7) {
                     return "-" + Longs.toUnsignedString(var6);
                  }

                  return Longs.toUnsignedString(var6);
               case HEXADECIMAL:
                  if (var7) {
                     return "-" + this.hexaNotationType.prefix() + Long.toHexString(var6).toUpperCase() + this.hexaNotationType.suffix();
                  }

                  return this.hexaNotationType.prefix() + Long.toHexString(var6).toUpperCase() + this.hexaNotationType.suffix();
               default:
                  throw new RuntimeException();
            }
         }
      }
   }

   public String format(int var1, BigInteger var2) {
      return this.format(var1, var2, this.base, this.signedNumber);
   }

   public String format(int var1, BigInteger var2, NumberFormatter.NumberBase var3, boolean var4) {
      if (var1 <= 64) {
         return this.format(var1, var2.longValue(), var3, var4);
      } else if (this.cf != null) {
         return this.cf.format();
      } else {
         if (var3 == null) {
            var3 = this.base;
         }

         switch (var3) {
            case DECIMAL:
               return Strings.f("%s", var2.toString());
            case HEXADECIMAL:
               return Strings.f("%s%s%s", this.hexaNotationType.prefix(), var2.toString(16), this.hexaNotationType.suffix());
            case ASCII:
               if (var1 % 8 == 0) {
                  return Strings.f("'%s'", Formatter.escapeBytes(var2.toByteArray()));
               }

               return Strings.f("%s", var2);
            default:
               return Strings.f("%s", var2.toString());
         }
      }
   }

   @Ser
   public static enum HexaNotationType {
      _h_suffix("", "h"),
      _0x_prefix("0x", "");

      private final String prefix;
      private final String suffix;

      private HexaNotationType(String var3, String var4) {
         this.prefix = var3;
         this.suffix = var4;
      }

      public String prefix() {
         return this.prefix;
      }

      public String suffix() {
         return this.suffix;
      }
   }

   @Ser
   public static enum NumberBase {
      BINARY,
      OCTAL,
      DECIMAL,
      HEXADECIMAL,
      ASCII;

      public NumberFormatter.NumberBase nextBase() {
         switch (this) {
            case BINARY:
               return OCTAL;
            case OCTAL:
               return DECIMAL;
            case DECIMAL:
               return HEXADECIMAL;
            case HEXADECIMAL:
               return ASCII;
            case ASCII:
               return BINARY;
            default:
               throw new RuntimeException();
         }
      }
   }
}
