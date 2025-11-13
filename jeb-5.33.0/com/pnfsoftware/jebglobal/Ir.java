package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Ir {
   private static final IX pC = IX.pC(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(10, 2), DirectEncodedMemoryArea.get(30, 1)));
   private static final Hu A = kk.pC(0, new xF(), pC);
   private static final Hu kS = kk.pC(0, new Cu(), pC);
   private static final Hu wS = new Tx();
   private static final Hu UT = LF.pC(sQ.NS);
   private static final Hu E = LF.A(sQ.NS, sQ.ld);
   private static final tz[] sY = new tz[]{
      new UC("ST4", A, UT),
      new UC("ST1", A, UT),
      new UC("ST3", A, UT),
      new UC("ST1", A, UT),
      new UC("ST2", A, UT),
      new UC("ST1", A, UT),
      new UC("ST1", A, UT),
      null,
      new UC("LD4", A, UT),
      new UC("LD1", A, UT),
      new UC("LD3", A, UT),
      new UC("LD1", A, UT),
      new UC("LD2", A, UT),
      new UC("LD1", A, UT),
      new UC("LD1", A, UT),
      null
   };
   private static final IEncodedMemoryArea ys = DirectEncodedMemoryArea.get(30, 1);
   private static Hu ld = new Zs(var0 -> 8 * (kS(var0) + 1) * (ys.decodeInt(var0) + 1));
   private static final Hu gp = new Ir.Av(ld);
   private static final tz[] oT = new tz[]{
      new UC("ST4", A, E),
      new UC("ST1", A, E),
      new UC("ST3", A, E),
      new UC("ST1", A, E),
      new UC("ST2", A, E),
      new UC("ST1", A, E),
      new UC("ST1", A, E),
      null,
      new UC("ST4", A, gp),
      new UC("ST1", A, gp),
      new UC("ST3", A, gp),
      new UC("ST1", A, gp),
      new UC("ST2", A, gp),
      new UC("ST1", A, gp),
      new UC("ST1", A, gp),
      null,
      new UC("LD4", A, E),
      new UC("LD1", A, E),
      new UC("LD3", A, E),
      new UC("LD1", A, E),
      new UC("LD2", A, E),
      new UC("LD1", A, E),
      new UC("LD1", A, E),
      null,
      new UC("LD4", A, gp),
      new UC("LD1", A, gp),
      new UC("LD3", A, gp),
      new UC("LD1", A, gp),
      new UC("LD2", A, gp),
      new UC("LD1", A, gp),
      new UC("LD1", A, gp),
      null
   };
   private static final tz[] fI = new tz[]{new UC("STL1", wS, UT).pC(Le.Bc), new UC("LDAP1", wS, UT).pC(Le.Bc)};
   private static final tz[] WR = new tz[]{
      new UC("ST1", wS, UT),
      new UC("ST3", wS, UT),
      new UC("ST1", wS, UT),
      new UC("ST3", wS, UT),
      new UC("ST1", wS, UT),
      new UC("ST1", wS, UT),
      new UC("ST3", wS, UT),
      new UC("ST3", wS, UT),
      new UC("ST2", wS, UT),
      new UC("ST4", wS, UT),
      new UC("ST2", wS, UT),
      new UC("ST4", wS, UT),
      new UC("ST2", wS, UT),
      new UC("ST2", wS, UT),
      new UC("ST4", wS, UT),
      new UC("ST4", wS, UT),
      new UC("LD1", wS, UT),
      new UC("LD3", wS, UT),
      new UC("LD1", wS, UT),
      new UC("LD3", wS, UT),
      new UC("LD1", wS, UT),
      new UC("LD1", wS, UT),
      new UC("LD3", wS, UT),
      new UC("LD3", wS, UT),
      new UC("LD2", wS, UT),
      new UC("LD4", wS, UT),
      new UC("LD2", wS, UT),
      new UC("LD4", wS, UT),
      new UC("LD2", wS, UT),
      new UC("LD2", wS, UT),
      new UC("LD4", wS, UT),
      new UC("LD4", wS, UT)
   };
   private static final tz[] NS = new tz[]{new UC("LD1R", kS, UT), new UC("LD3R", kS, UT), new UC("LD2R", kS, UT), new UC("LD4R", kS, UT)};
   private static Hu vP = new Zs(var0 -> UT(var0) * (wS(var0) + 1));
   private static final Hu xC = new Ir.Av(vP);
   private static final tz[] ED = new tz[]{
      new UC("ST1", wS, E),
      new UC("ST3", wS, E),
      new UC("ST1", wS, E),
      new UC("ST3", wS, E),
      new UC("ST1", wS, E),
      new UC("ST1", wS, E),
      new UC("ST3", wS, E),
      new UC("ST3", wS, E),
      new UC("ST1", wS, xC),
      new UC("ST3", wS, xC),
      new UC("ST1", wS, xC),
      new UC("ST3", wS, xC),
      new UC("ST1", wS, xC),
      new UC("ST1", wS, xC),
      new UC("ST3", wS, xC),
      new UC("ST3", wS, xC),
      new UC("ST2", wS, E),
      new UC("ST4", wS, E),
      new UC("ST2", wS, E),
      new UC("ST4", wS, E),
      new UC("ST2", wS, E),
      new UC("ST2", wS, E),
      new UC("ST4", wS, E),
      new UC("ST4", wS, E),
      new UC("ST2", wS, xC),
      new UC("ST4", wS, xC),
      new UC("ST2", wS, xC),
      new UC("ST4", wS, xC),
      new UC("ST2", wS, xC),
      new UC("ST2", wS, xC),
      new UC("ST4", wS, xC),
      new UC("ST4", wS, xC),
      new UC("LD1", wS, E),
      new UC("LD3", wS, E),
      new UC("LD1", wS, E),
      new UC("LD3", wS, E),
      new UC("LD1", wS, E),
      new UC("LD1", wS, E),
      new UC("LD3", wS, E),
      new UC("LD3", wS, E),
      new UC("LD1", wS, xC),
      new UC("LD3", wS, xC),
      new UC("LD1", wS, xC),
      new UC("LD3", wS, xC),
      new UC("LD1", wS, xC),
      new UC("LD1", wS, xC),
      new UC("LD3", wS, xC),
      new UC("LD3", wS, xC),
      new UC("LD2", wS, E),
      new UC("LD4", wS, E),
      new UC("LD2", wS, E),
      new UC("LD4", wS, E),
      new UC("LD2", wS, E),
      new UC("LD2", wS, E),
      new UC("LD4", wS, E),
      new UC("LD4", wS, E),
      new UC("LD2", wS, xC),
      new UC("LD4", wS, xC),
      new UC("LD2", wS, xC),
      new UC("LD4", wS, xC),
      new UC("LD2", wS, xC),
      new UC("LD2", wS, xC),
      new UC("LD4", wS, xC),
      new UC("LD4", wS, xC)
   };
   private static Hu Sc = new Zs(var0 -> {
      int var1 = wS(var0) + 1;
      int var2 = (var0[2] & 12) >>> 2;
      return var1 * (int)Math.pow(2.0, var2);
   });
   private static final Hu ah = new Ir.Av(Sc);
   private static final tz[] eP = new tz[]{
      new UC("LD1R", kS, E),
      new UC("LD3R", kS, E),
      new UC("LD1R", kS, ah),
      new UC("LD3R", kS, ah),
      new UC("LD2R", kS, E),
      new UC("LD4R", kS, E),
      new UC("LD2R", kS, ah),
      new UC("LD4R", kS, ah)
   };

   private static int A(byte[] var0) {
      int var1 = (var0[2] & 240) >>> 4;
      return (var1 & 1) == 1 ? 6 : var1 >>> 1;
   }

   private static int kS(byte[] var0) {
      return ~(A(var0) >>> 1) & 3;
   }

   private static int wS(byte[] var0) {
      int var1 = (var0[2] & 255) >>> 5;
      int var2 = (var0[1] & 32) >>> 5;
      return ((var1 & 1) == 0 ? 0 : 2) + var2;
   }

   private static Integer pC(int var0, int var1) {
      int var2 = var0 >>> 1;
      switch (var2) {
         case 0:
         case 1:
            return var2;
         case 2:
         case 3:
            if ((var1 & 1) == 0) {
               return var2;
            }

            return null;
         case 4:
         case 5:
            if (var2 == 5) {
               var2 = 6;
            }

            if (var1 == 0) {
               return var2;
            } else {
               if ((var0 & 1) == 0 && var1 == 1) {
                  return var2 + 1;
               }

               return null;
            }
         default:
            return null;
      }
   }

   private static int UT(byte[] var0) {
      int var1 = (var0[2] & 255) >>> 6;
      switch (var1) {
         case 0:
            return 1;
         case 1:
            return 2;
         case 2:
            int var2 = (var0[2] & 12) >>> 2;
            return var2 == 0 ? 4 : 8;
         case 3:
         default:
            return -1;
      }
   }

   public static tz pC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 128) >>> 7;
      int var2 = DirectEncodedMemoryArea.get(23, 2).decodeInt(var0);
      int var3 = var0[1] & 63;
      int var4 = A(var0);
      Integer var5 = pC((var0[2] & 240) >>> 4, (var0[2] & 12) >>> 2);
      int var6 = (var0[1] & 64) >>> 6;
      int var7 = (var0[1] & 32) >>> 5;
      int var8 = var0[1] & 31;
      if (var1 == 0) {
         switch (var2) {
            case 0:
               if (var3 == 0) {
                  return sY[var6 << 3 | var4];
               }
               break;
            case 1:
               if (var3 < 32) {
                  return oT[var6 << 4 | (var8 == 31 ? 1 : 0) << 3 | var4];
               }
               break;
            case 2:
               if ((var3 & 31) == 0) {
                  if (var5 != null) {
                     return WR[var6 << 4 | var7 << 3 | var5];
                  }

                  if (var6 == 1 && (var0[2] & 208) == 192) {
                     return NS[var7 << 1 | (var0[2] & 32) >>> 5];
                  }
               } else if (var3 == 1 && (var0[2] & 252) == 132) {
                  return fI[var6];
               }
               break;
            case 3:
               if (var5 != null) {
                  return ED[var6 << 5 | var7 << 4 | (var8 == 31 ? 1 : 0) << 3 | var5];
               }

               if (var6 == 1 && (var0[2] & 208) == 192) {
                  return eP[var7 << 2 | (var8 == 31 ? 1 : 0) << 1 | (var0[2] & 32) >>> 5];
               }
         }
      }

      return UC.pC(var0, "LoadStore: SIMD");
   }

   private static class Av implements Hu {
      private Hu pC;

      public Av(Hu var1) {
         this.pC = var1;
      }

      @Override
      public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
         return new KH(sQ.xC.buildOperand(var1, var2), this.pC.buildOperand(var1, var2), false, false, var2);
      }
   }
}
