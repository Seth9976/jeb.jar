package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Zo {
   public static final IEncodedMemoryArea q = DirectEncodedMemoryArea.get(20, 2);
   public static final de RF = new Zo.eo('S', q, 2);
   public static final de xK = new Zo.eo('S', q, 3);
   public static final de Dw = new Zo.eo('S', q, 1, 2, 0);
   public static final de Uv = new Zo.eo('U', q, 2);
   public static final de oW = new Zo.eo('U', q, 3);
   public static final de gO = new Zo.eo('U', q, 1, 2, 0);
   public static final de nf = new Zo.eo('I', q, 2);
   public static final de gP = new Zo.eo('I', q, 3);
   public static final de za = new Zo.eo('I', q, 1, 2, 0);
   public static final de lm = new Zo.eo('I', q, 0, 2, 1);
   public static final de zz = new Zo.eo('P', q, 0);
   public static final de JY = new Zo.eo('F', q, 2, 2, 0);
   public static final de HF = new Zo.eo('F', q, 1, 2, 0);
   public static final de LK = new de.nI("8");
   public static final de io = new de.nI("16");
   public static final de qa = new de.nI("32");
   public static final de Hk = new de.nI("F16");
   public static final de Me = new de.nI("F32");
   public static final de PV = new de.nI("I64");
   public static final de oQ = new de.nI("I32");
   public static final de xW = new de.nI("I16");
   public static final de KT = new de.nI("I8");
   public static final de Gf = new de.nI("S8");
   public static final de Ef = new de.nI("U8");
   public static final de cC = new de.nI("BF16");
   public static final de sH = new de.nI("F32.F16");
   public static final de CE = new de.nI("F32.F64");
   public static final de wF = new de.nI("F64.F16");
   public static final de If = new de.nI("F64.F32");
   public static final de Dz = new de.nI("F16.F32");
   public static final de mI = new de.nI("F16.F64");
   public static final de jq = new de.nI("F16.U16");
   public static final de ui = new de.nI("F16.U32");
   public static final de TX = new de.nI("F16.S16");
   public static final de Rr = new de.nI("F16.S32");
   public static final de EB = new de.nI("F32.S16");
   public static final de Xo = new de.nI("F32.S32");
   public static final de Bu = new de.nI("F32.U16");
   public static final de IN = new de.nI("F32.U32");
   public static final de rL = new de.nI("S16.F32");
   public static final de eJ = new de.nI("S32.F32");
   public static final de YN = new de.nI("U16.F32");
   public static final de Rv = new de.nI("U32.F32");
   public static final de zx = new de.nI("S32.F16");
   public static final de ZT = new de.nI("U32.F16");
   public static final de Ri = new de.nI("S16.F16");
   public static final de GY = new de.nI("U16.F16");
   public static final de Wx = new de.nI("F64.S16");
   public static final de AB = new de.nI("F64.S32");
   public static final de CY = new de.nI("F64.U16");
   public static final de WI = new de.nI("F64.U32");
   public static final de Tq = new de.nI("S16.F64");
   public static final de Yp = new de.nI("S32.F64");
   public static final de Gu = new de.nI("U16.F64");
   public static final de nY = new de.nI("U32.F64");
   public static final de lF = new de.nI("BF16.F32");

   public static int q(byte[] var0) {
      return (var0[1] & 48) >>> 4;
   }

   public static int q(byte[] var0, int var1) {
      return var1 == 16 ? (var0[0] & 16) >>> 4 : var0[0] & 1;
   }

   public static class CU implements de.eo {
      private de sH;
      private de CE;

      public CU(de var1, de var2) {
         this.sH = var1;
         this.CE = var2;
      }

      @Override
      public CharSequence getDataType(byte[] var1) throws eK {
         return this.sH.getDataType(var1) + "." + this.CE.getDataType(var1);
      }
   }

   public static class eo implements de.eo {
      private Character wF;
      private int If;
      private int Dz;
      protected int sH = 0;
      private boolean mI = false;
      protected IEncodedMemoryArea CE;

      public eo(Character var1, IEncodedMemoryArea var2, int var3) {
         this(var1, var2, 0, var3, 0);
      }

      public eo(Character var1, IEncodedMemoryArea var2, int var3, int var4, int var5) {
         this(var1, var2, var3, var4, var5, false);
      }

      public eo(Character var1, IEncodedMemoryArea var2, int var3, int var4, int var5, boolean var6) {
         this.wF = var1;
         this.CE = var2;
         this.If = var3;
         this.Dz = var4;
         this.sH = var5;
         this.mI = var6;
      }

      @Override
      public CharSequence getDataType(byte[] var1) throws eK {
         StringBuilder var2 = new StringBuilder();
         if (this.wF != null) {
            var2.append(this.wF);
         }

         int var3 = this.q(var1);
         if (var3 == -1) {
            throw new eK("SIMD Data Type is out of range");
         } else {
            var2.append(var3);
            return var2;
         }
      }

      protected int q(byte[] var1) {
         int var2 = this.CE.decodeInt(var1);
         if (var2 <= this.Dz && var2 >= this.If) {
            if (this.mI) {
               var2 = this.Dz - var2;
            }

            switch (var2 + this.sH) {
               case 0:
                  return 8;
               case 1:
                  return 16;
               case 2:
                  return 32;
               case 3:
                  return 64;
               case 4:
                  return 128;
               default:
                  return (int)Math.pow(2.0, var2 + this.sH) * 8;
            }
         } else {
            return -1;
         }
      }
   }
}
