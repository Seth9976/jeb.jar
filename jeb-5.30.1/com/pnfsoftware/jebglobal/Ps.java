package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Ps {
   private static final de q = new Zo.eo(null, Zo.q, 3, 3, -3);
   private static final Je RF = new Qg("VEXT", Y.qa, Y.Hk, Y.Me, new go(DirectEncodedMemoryArea.get(8, 3))).q(q);
   private static final Je xK = new Qg("VEXT", Y.qa, Y.Hk, Y.Me, new go(DirectEncodedMemoryArea.get(8, 4))).q(q);
   private static final Ef Dw = new Nj(DirectEncodedMemoryArea.get(8, 2));
   private static final Je Uv = new Qg("VTBL", Y.sH, Dw, Y.wF).q(q);
   private static final Je oW = new Qg("VTBX", Y.sH, Dw, Y.wF).q(q);
   private static final Ef gO = new VP(Y.wF, new FunctionEncodedMemoryArea(32, Ps::q));
   private static final de nf = new rN(null, null, 0);
   private static final Je gP = new Qg("VDUP", Y.qa, gO).q(nf);

   protected static Long q(byte[] var0) {
      IEncodedMemoryArea var1 = RF(var0);
      return var1 != null ? var1.decode(var0) : null;
   }

   protected static IEncodedMemoryArea RF(byte[] var0) {
      int var1 = xK(var0);
      if (var1 == 8) {
         return DirectEncodedMemoryArea.get(17, 3);
      } else if (var1 == 16) {
         return DirectEncodedMemoryArea.get(18, 2);
      } else {
         return var1 == 32 ? DirectEncodedMemoryArea.get(19, 1) : null;
      }
   }

   private static int xK(byte[] var0) {
      int var1 = var0[1] & 15;
      if ((var1 & 1) == 1) {
         return 8;
      } else if ((var1 & 3) == 2) {
         return 16;
      } else {
         return (var1 & 7) == 4 ? 32 : -1;
      }
   }

   public static Je q(byte[] var0, int var1) {
      int var2 = (var0[1] & 128) >>> 7;
      int var3 = (var0[3] & 16) >>> 4;
      if (var2 == 0) {
         return it.q(var0, var1);
      } else if (var3 != 0) {
         return (var0[1] & 56) == 0 && (var0[3] & 128) == 0 ? Rc.q(var0) : Iu.q(var0, var1);
      } else {
         int var4 = (var0[1] & 48) >>> 4;
         int var5 = (var0[3] & 64) >>> 6;
         if (var4 == 3) {
            int var6 = var0[2] & 15;
            int var7 = (var0[3] & 240) >>> 4;
            int var8 = Zo.q(var0, var1);
            if (var8 == 0) {
               return (var7 & 4) == 0 ? RF : xK;
            } else if ((var6 & 8) == 0) {
               return vT.q(var0, var1);
            } else if ((var6 & 12) == 8) {
               return (var7 & 4) == 0 ? Uv : oW;
            } else {
               return var6 == 12 ? gP : null;
            }
         } else {
            return var5 == 0 ? Mh.q(var0, var1) : mj.q(var0, var1);
         }
      }
   }
}
