package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerStaticOk;

@Ser
public class PrimitiveSizes implements IPrimitiveSizes {
   @SerStaticOk
   public static final PrimitiveSizes GCC_ARM = new PrimitiveSizes(1, 2, 4, 4, 8, 4, 8, 8);
   @SerStaticOk
   public static final PrimitiveSizes GCC_ARM64 = new PrimitiveSizes(1, 2, 4, 8, 8, 4, 8, 16);
   @SerStaticOk
   public static final PrimitiveSizes GCC_X86 = new PrimitiveSizes(1, 2, 4, 4, 8, 4, 8, 12);
   @SerStaticOk
   public static final PrimitiveSizes GCC_X86_64 = new PrimitiveSizes(1, 2, 4, 8, 8, 4, 8, 16);
   @SerStaticOk
   public static final PrimitiveSizes GCC_MIPS = new PrimitiveSizes(1, 2, 4, 4, 8, 4, 8, 8);
   @SerStaticOk
   public static final PrimitiveSizes MSVC_GENERIC = new PrimitiveSizes(1, 2, 4, 4, 8, 4, 8, 8);
   @SerStaticOk
   public static final PrimitiveSizes MSVC_ARM = MSVC_GENERIC;
   @SerStaticOk
   public static final PrimitiveSizes MSVC_ARM64 = MSVC_GENERIC;
   @SerStaticOk
   public static final PrimitiveSizes MSVC_X86 = MSVC_GENERIC;
   @SerStaticOk
   public static final PrimitiveSizes MSVC_X86_64 = MSVC_GENERIC;
   @SerStaticOk
   public static final PrimitiveSizes COMMON_PRIMITIVE_SIZES = new PrimitiveSizes(1, 2, 4, 4, 8, 4, 8, 10);
   @SerStaticOk
   public static final PrimitiveSizes COMMON_PRIMITIVE_SIZES64 = new PrimitiveSizes(1, 2, 4, 8, 8, 4, 8, 10);
   @SerId(1)
   int charSize;
   @SerId(2)
   int shortSize;
   @SerId(3)
   int intSize;
   @SerId(4)
   int longSize;
   @SerId(5)
   int longLongSize;
   @SerId(6)
   int floatSize;
   @SerId(7)
   int doubleSize;
   @SerId(8)
   int longDoubleSize;

   public static PrimitiveSizes getCommon(ProcessorType var0, CompilerType var1) {
      if (var0 == null) {
         var0 = ProcessorType.UNKNOWN;
      }

      if (var1 == null) {
         var1 = CompilerType.UNKNOWN;
      }

      if (var1 == CompilerType.GCC) {
         if (var0 == ProcessorType.ARM) {
            return GCC_ARM;
         }

         if (var0 == ProcessorType.ARM64) {
            return GCC_ARM64;
         }

         if (var0 == ProcessorType.X86) {
            return GCC_X86;
         }

         if (var0 == ProcessorType.X86_64) {
            return GCC_X86_64;
         }
      } else if (var1 == CompilerType.MSVC) {
         if (var0 == ProcessorType.ARM) {
            return MSVC_ARM;
         }

         if (var0 == ProcessorType.ARM64) {
            return MSVC_ARM64;
         }

         if (var0 == ProcessorType.X86) {
            return MSVC_X86;
         }

         if (var0 == ProcessorType.X86_64) {
            return MSVC_X86_64;
         }
      }

      return var0.is64Bit() ? COMMON_PRIMITIVE_SIZES64 : COMMON_PRIMITIVE_SIZES;
   }

   public PrimitiveSizes(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.charSize = var1;
      this.shortSize = var2;
      this.intSize = var3;
      this.longSize = var4;
      this.longLongSize = var5;
      this.floatSize = var6;
      this.doubleSize = var7;
      this.longDoubleSize = var8;
   }

   public PrimitiveSizes(IPrimitiveSizes var1) {
      this(
         var1.getCharSize(),
         var1.getShortSize(),
         var1.getIntSize(),
         var1.getLongSize(),
         var1.getLongLongSize(),
         var1.getFloatSize(),
         var1.getDoubleSize(),
         var1.getLongDoubleSize()
      );
   }

   @Override
   public int getCharSize() {
      return this.charSize;
   }

   @Override
   public int getShortSize() {
      return this.shortSize;
   }

   @Override
   public int getIntSize() {
      return this.intSize;
   }

   @Override
   public int getLongSize() {
      return this.longSize;
   }

   @Override
   public int getLongLongSize() {
      return this.longLongSize;
   }

   @Override
   public int getFloatSize() {
      return this.floatSize;
   }

   @Override
   public int getDoubleSize() {
      return this.doubleSize;
   }

   @Override
   public int getLongDoubleSize() {
      return this.longDoubleSize;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.charSize;
      var1 = 31 * var1 + this.doubleSize;
      var1 = 31 * var1 + this.floatSize;
      var1 = 31 * var1 + this.intSize;
      var1 = 31 * var1 + this.longDoubleSize;
      var1 = 31 * var1 + this.longLongSize;
      var1 = 31 * var1 + this.longSize;
      return 31 * var1 + this.shortSize;
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
         PrimitiveSizes var2 = (PrimitiveSizes)var1;
         if (this.charSize != var2.charSize) {
            return false;
         } else if (this.doubleSize != var2.doubleSize) {
            return false;
         } else if (this.floatSize != var2.floatSize) {
            return false;
         } else if (this.intSize != var2.intSize) {
            return false;
         } else if (this.longDoubleSize != var2.longDoubleSize) {
            return false;
         } else if (this.longLongSize != var2.longLongSize) {
            return false;
         } else {
            return this.longSize != var2.longSize ? false : this.shortSize == var2.shortSize;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff(
         "char:%d,short:%d,int:%d,long:%d,long-long:%d,float:%d,double:%d,long-double:%d",
         this.charSize,
         this.shortSize,
         this.intSize,
         this.longSize,
         this.longLongSize,
         this.floatSize,
         this.doubleSize,
         this.longDoubleSize
      );
   }
}
