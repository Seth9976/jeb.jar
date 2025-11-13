package com.pnfsoftware.jeb.util.format;

import com.pnfsoftware.jeb.util.primitives.Longs;

public class NumberFormatter {
   NumberFormatter.Base base;
   boolean signedNumber;
   NumberFormatter.NotationType notationType;

   public NumberFormatter() {
      this.base = NumberFormatter.Base.HEXADECIMAL;
      this.signedNumber = false;
      this.notationType = NumberFormatter.NotationType._h_suffix;
   }

   public NumberFormatter(NumberFormatter var1) {
      this.base = var1.base;
      this.signedNumber = var1.signedNumber;
      this.notationType = var1.notationType;
   }

   public NumberFormatter.Base getBase() {
      return this.base;
   }

   public void setBase(NumberFormatter.Base var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Need a non-null base");
      } else {
         this.base = var1;
      }
   }

   public NumberFormatter.Base rotateBase() {
      NumberFormatter.Base var1 = this.getBase().nextBase();
      this.setBase(var1);
      return var1;
   }

   public void setSignedNumber(boolean var1) {
      this.signedNumber = var1;
   }

   public boolean isSignedNumber() {
      return this.signedNumber;
   }

   public void setNotationType(NumberFormatter.NotationType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Need a non-null notation type");
      } else {
         this.notationType = var1;
      }
   }

   public NumberFormatter.NotationType getNotationType() {
      return this.notationType;
   }

   public String format(int var1, long var2) {
      return this.format(var1, var2, this.base, this.signedNumber);
   }

   public String format(int var1, long var2, NumberFormatter.Base var4, boolean var5) {
      if (var4 == null) {
         var4 = this.base;
      }
      long var6 = switch (var1) {
         case 8 -> (byte)var2 & 255L;
         case 16 -> (short)var2 & 65535L;
         case 32 -> (int)var2 & 4294967295L;
         default -> {
            var1 = 64;
            yield var2;
         }
      };
      if (var4.isStandard()) {
         boolean var12 = false;
         if (var5) {
            boolean var13 = (var2 >> var1 - 1 & 1L) == 1L;
            if (var13) {
               var6 = switch (var1) {
                  case 8 -> -(var6 | -256L);
                  case 16 -> -(var6 | -65536L);
                  case 32 -> -(var6 | -4294967296L);
                  default -> -var6;
               };
               var12 = true;
            }
         }

         switch (var4) {
            case OCTAL:
               if (var12) {
                  return "-0" + Long.toOctalString(var6);
               }

               return "0" + Long.toOctalString(var6);
            case DECIMAL:
               if (var12) {
                  return "-" + Longs.toUnsignedString(var6);
               }

               return Longs.toUnsignedString(var6);
            case HEXADECIMAL:
               if (var12) {
                  return "-" + this.notationType.prefix() + Long.toHexString(var6).toUpperCase() + this.notationType.suffix();
               }

               return this.notationType.prefix() + Long.toHexString(var6).toUpperCase() + this.notationType.suffix();
            default:
               throw new RuntimeException();
         }
      } else {
         switch (var4) {
            case BINARY:
               String var11 = Long.toBinaryString(var2);
               if (var11.length() > var1) {
                  var11 = var11.substring(var11.length() - var1, var11.length());
               }

               return "b'" + var11;
            case ASCII:
               if (var2 != 0L && var1 % 8 == 0) {
                  byte[] var8 = new byte[8];
                  int var9 = 8;

                  do {
                     byte var10 = (byte)var2;
                     var2 >>>= 8;
                     var8[--var9] = var10;
                  } while (var2 != 0L);

                  return "'" + Formatter.escapeBytes(var8, var9, 8 - var9) + "'";
               } else {
                  return Long.toString(var6);
               }
            default:
               return Long.toString(var6);
         }
      }
   }

   public static enum Base {
      BINARY(false),
      OCTAL(true),
      DECIMAL(true),
      HEXADECIMAL(true),
      ASCII(false);

      private final boolean std;

      private Base(boolean var3) {
         this.std = var3;
      }

      public boolean isStandard() {
         return this.std;
      }

      public NumberFormatter.Base nextBase() {
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

   public static enum NotationType {
      _h_suffix("", "h"),
      _0x_prefix("0x", ""),
      _none("", "");

      private final String prefix;
      private final String suffix;

      private NotationType(String var3, String var4) {
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
}
