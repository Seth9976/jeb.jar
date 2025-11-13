package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Uf {
   public static final IEncodedMemoryArea pC = DirectEncodedMemoryArea.get(20, 2);
   public static final ji A = new Uf.Av('S', pC, 2);
   public static final ji kS = new Uf.Av('S', pC, 3);
   public static final ji wS = new Uf.Av('S', pC, 1, 2, 0);
   public static final ji UT = new Uf.Av('U', pC, 2);
   public static final ji E = new Uf.Av('U', pC, 3);
   public static final ji sY = new Uf.Av('U', pC, 1, 2, 0);
   public static final ji ys = new Uf.Av('I', pC, 2);
   public static final ji ld = new Uf.Av('I', pC, 3);
   public static final ji gp = new Uf.Av('I', pC, 1, 2, 0);
   public static final ji oT = new Uf.Av('I', pC, 0, 2, 1);
   public static final ji fI = new Uf.Av('P', pC, 0);
   public static final ji WR = new Uf.Av('F', pC, 2, 2, 0);
   public static final ji NS = new Uf.Av('F', pC, 1, 2, 0);
   public static final ji vP = new ji.Sv("8");
   public static final ji xC = new ji.Sv("16");
   public static final ji ED = new ji.Sv("32");
   public static final ji Sc = new ji.Sv("F16");
   public static final ji ah = new ji.Sv("F32");
   public static final ji eP = new ji.Sv("I64");
   public static final ji UO = new ji.Sv("I32");
   public static final ji Ab = new ji.Sv("I16");
   public static final ji rl = new ji.Sv("I8");
   public static final ji z = new ji.Sv("S8");
   public static final ji Ek = new ji.Sv("U8");
   public static final ji hK = new ji.Sv("BF16");
   public static final ji Er = new ji.Sv("F32.F16");
   public static final ji FE = new ji.Sv("F32.F64");
   public static final ji Aj = new ji.Sv("F64.F16");
   public static final ji EX = new ji.Sv("F64.F32");
   public static final ji LM = new ji.Sv("F16.F32");
   public static final ji mv = new ji.Sv("F16.F64");
   public static final ji sO = new ji.Sv("F16.U16");
   public static final ji os = new ji.Sv("F16.U32");
   public static final ji Cu = new ji.Sv("F16.S16");
   public static final ji hZ = new ji.Sv("F16.S32");
   public static final ji UW = new ji.Sv("F32.S16");
   public static final ji PR = new ji.Sv("F32.S32");
   public static final ji cX = new ji.Sv("F32.U16");
   public static final ji DQ = new ji.Sv("F32.U32");
   public static final ji ZN = new ji.Sv("S16.F32");
   public static final ji OB = new ji.Sv("S32.F32");
   public static final ji pF = new ji.Sv("U16.F32");
   public static final ji Bc = new ji.Sv("U32.F32");
   public static final ji OI = new ji.Sv("S32.F16");
   public static final ji Bf = new ji.Sv("U32.F16");
   public static final ji Pe = new ji.Sv("S16.F16");
   public static final ji ck = new ji.Sv("U16.F16");
   public static final ji RW = new ji.Sv("F64.S16");
   public static final ji e = new ji.Sv("F64.S32");
   public static final ji xM = new ji.Sv("F64.U16");
   public static final ji kU = new ji.Sv("F64.U32");
   public static final ji Kq = new ji.Sv("S16.F64");
   public static final ji go = new ji.Sv("S32.F64");
   public static final ji JF = new ji.Sv("U16.F64");
   public static final ji Nq = new ji.Sv("U32.F64");
   public static final ji pg = new ji.Sv("BF16.F32");

   public static int pC(byte[] var0) {
      return (var0[1] & 48) >>> 4;
   }

   public static int pC(byte[] var0, int var1) {
      return var1 == 16 ? (var0[0] & 16) >>> 4 : var0[0] & 1;
   }

   public static class Av implements YE {
      private Character Aj;
      private int EX;
      private int LM;
      protected int Er = 0;
      private boolean mv = false;
      protected IEncodedMemoryArea FE;

      public Av(Character var1, IEncodedMemoryArea var2, int var3) {
         this(var1, var2, 0, var3, 0);
      }

      public Av(Character var1, IEncodedMemoryArea var2, int var3, int var4, int var5) {
         this(var1, var2, var3, var4, var5, false);
      }

      public Av(Character var1, IEncodedMemoryArea var2, int var3, int var4, int var5, boolean var6) {
         this.Aj = var1;
         this.FE = var2;
         this.EX = var3;
         this.LM = var4;
         this.Er = var5;
         this.mv = var6;
      }

      @Override
      public CharSequence getDataType(byte[] var1) throws oJ {
         StringBuilder var2 = new StringBuilder();
         if (this.Aj != null) {
            var2.append(this.Aj);
         }

         int var3 = this.pC(var1);
         if (var3 == -1) {
            throw new oJ("SIMD Data Type is out of range");
         } else {
            var2.append(var3);
            return var2;
         }
      }

      protected int pC(byte[] var1) {
         int var2 = this.FE.decodeInt(var1);
         if (var2 <= this.LM && var2 >= this.EX) {
            if (this.mv) {
               var2 = this.LM - var2;
            }

            switch (var2 + this.Er) {
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
                  return (int)Math.pow(2.0, var2 + this.Er) * 8;
            }
         } else {
            return -1;
         }
      }
   }

   public static class Sv implements YE {
      private ji Er;
      private ji FE;

      public Sv(ji var1, ji var2) {
         this.Er = var1;
         this.FE = var2;
      }

      @Override
      public CharSequence getDataType(byte[] var1) throws oJ {
         return this.Er.getDataType(var1) + "." + this.FE.getDataType(var1);
      }
   }
}
