package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.InsnEmulator;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class OQ extends InsnEmulator implements lH {
   public static final OQ q = q(0);
   private static Map Rv;

   OQ(int var1) {
      super(var1);
   }

   public static CW[] q(int var0, CW[] var1, int var2) {
      switch (var0 & 0xFF) {
         case 2:
            return var1.length != 0 ? new CW[]{var1[0]} : null;
         case 4:
            yS var3 = (yS)var1[var1.length - 1];
            if ((var0 & 1048576) != 0) {
               CW[] var4 = new CW[var3.oW().length + 1];
               System.arraycopy(var3.oW(), 0, var4, 0, var4.length - 1);
               if (var1.length >= 2) {
                  var4[var4.length - 1] = var1[0];
               } else {
                  var4[var4.length - 1] = GC.oW(var2);
               }

               return var4;
            }

            return var3.oW();
         default:
            return null;
      }
   }

   private static boolean xK(CW[] var0, int var1) {
      if (var0 == null) {
         return false;
      } else {
         for (CW var5 : var0) {
            if (var5.RF(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public int q(int var1, long var2) {
      int var4 = var1;
      if (this.xK()) {
         return 32;
      } else if (this.Dw()) {
         return 16;
      } else {
         if (this.RF()) {
            if (var2 == -1L) {
               var4 = 0;
            } else if ((var2 & 1L) == 1L) {
               var4 = 16;
            } else {
               var4 = 32;
            }
         }

         return var4;
      }
   }

   @Override
   public boolean q() {
      return (this.getFlags() & 917504) != 0;
   }

   @Override
   public boolean RF() {
      return (this.getFlags() & 917504) == 524288;
   }

   @Override
   public boolean xK() {
      return (this.getFlags() & 917504) == 262144;
   }

   @Override
   public boolean Dw() {
      return (this.getFlags() & 917504) == 131072;
   }

   public boolean Uv() {
      return (this.getFlags() & 1048576) != 0;
   }

   @Override
   public Long q(fA var1, long var2, IMachineContext var4) throws ProcessorException {
      return this.q(var1.xK(), var2, var1.getProcessorMode(), var1.RF(), var4);
   }

   public Long q(byte[] var1, long var2, int var4, CW[] var5, IMachineContext var6) throws ProcessorException {
      switch (this.getFlags() & 3840) {
         case 256:
            return this.q(var2, var4, var5, var6, null);
         case 512:
            return this.q(var2, var4, var5, var6, false);
         case 768:
            return this.q(var2, var4, var5, var6, true);
         case 1024:
            return this.q(var2, var6, var1);
         case 1280:
            return this.q(var2, var4, var5, var6);
         case 1536:
            return this.RF(var2, var4, var5, var6);
         case 1792:
            return this.xK(var2, var4, var5, var6);
         case 2048:
         case 2304:
         case 2560:
            return this.Dw(var2, var4, var5, var6);
         default:
            return null;
      }
   }

   public Long q(long var1, int var3, CW[] var4, IMachineContext var5, rq var6) {
      if (var4 != null && var4.length != 0) {
         CW var7 = var4[var4.length - 1];
         return var6 != null ? new mi(var6, var7).q(var1, var3, var5) : new mi(var7).q(var1, var3, var5);
      } else {
         return null;
      }
   }

   public Long q(long var1, int var3, CW[] var4, IMachineContext var5, boolean var6) throws ProcessorException {
      if (var5 == null) {
         return null;
      } else {
         CW[] var7 = var4[0].oW();
         int var8 = var7[0].q(var1, var3, var5).intValue();
         int var9 = var7[1].q(var1, var3, var5).intValue();
         int var10;
         if (var6) {
            var10 = rT.xK(var5, var8 + var9) & '\uffff';
         } else {
            var10 = rT.RF(var5, Integer.valueOf(var8 + var9)) & 255;
         }

         CW var11 = CW.q(32, 2L * var10);
         return new mi(rq.q, GC.q(var3), var11).q(var1, var3, var5);
      }
   }

   public Long q(long var1, IMachineContext var3, byte[] var4) throws ProcessorException {
      int var5 = (var4[1] & 240) >>> 4;
      Deque var6 = Lf.RF(var4);
      ArrayList var7 = new ArrayList(var6);
      var7.add(0, Boolean.TRUE);
      if (var3 == null) {
         return var1 + 2L;
      } else {
         int var8 = rT.xK(var3);

         for (int var9 = 0; var9 < var7.size(); var9++) {
            boolean var10 = mZ.q(var5, var8);
            if (var10 == (Boolean)var7.get(var9)) {
               return this.q(var1 + 2L, var9, var3);
            }
         }

         return this.q(var1 + 2L, var7.size(), var3);
      }
   }

   public Long q(long var1, int var3, CW[] var4, IMachineContext var5) throws ProcessorException {
      if (var5 == null) {
         return null;
      } else {
         Integer var6 = var4[1].q(var1, var3, var5).intValue();
         return (long)rT.q(var5, var6);
      }
   }

   @Override
   public OQ.eo oW() {
      switch (this.getFlags() & -268435456) {
         case 268435456:
            return OQ.eo.q;
         case 536870912:
            return OQ.eo.RF;
         case 805306368:
            return OQ.eo.xK;
         case 1073741824:
            return OQ.eo.Dw;
         default:
            return null;
      }
   }

   public Long RF(long var1, int var3, CW[] var4, IMachineContext var5) throws ProcessorException {
      CW var6;
      if (var4.length >= 2) {
         var6 = var4[0];
         if (this.Uv() && var6.RF(var3)) {
            throw new zL("Update PC is unpredictable for this LDM instruction");
         }
      } else {
         var6 = GC.oW(var3);
      }

      yS var7 = (yS)var4[var4.length - 1];
      CW[] var8 = var7.oW();
      if (var8 == null || !var8[var8.length - 1].RF(var3)) {
         return null;
      } else if (var5 == null) {
         return null;
      } else {
         Integer var9 = var6.q(var1, var3, var5).intValue();
         return (long)rT.q(var5, Integer.valueOf(var9 + this.oW().q(var8.length - 1, var8.length)));
      }
   }

   private Long q(long var1, int var3, IMachineContext var4) throws ProcessorException {
      byte[] var5 = new byte[4];

      for (int var6 = 0; var6 < var3; var6++) {
         try {
            var4.getMemory().read(var1, 4, var5, 0);
         } catch (MemoryException var8) {
            throw new ProcessorException(var8);
         }

         var1 += cL.q(var5, var4.getInformation().getEndianness().toByteOrder()) ? 4L : 2L;
      }

      return var1;
   }

   public Long xK(long var1, int var3, CW[] var4, IMachineContext var5) throws ProcessorException {
      CW var6 = var4[0];
      if (var6.RF(var3)) {
         throw new zL("Update PC is unpredictable for this RFE instruction");
      } else if (var5 == null) {
         return null;
      } else {
         Integer var7 = var6.q(var1, var3, var5).intValue();
         return (long)rT.q(var5, Integer.valueOf(var7 + this.oW().q(0, 2)));
      }
   }

   public mi q(CW[] var1, int var2) {
      if (var1 != null && var1.length != 0) {
         if (var1.length <= 1) {
            return null;
         } else if ((this.getFlags() & 3840) == 2560) {
            return new mi(rq.q, GC.q(var2), var1[1]);
         } else if ((this.getFlags() & 3840) == 2048) {
            return new mi(this.q(true), var1[1]);
         } else {
            return var1.length <= 2 ? null : new mi(this.q(false), var1[1], var1[2]);
         }
      } else {
         return null;
      }
   }

   private rq q(boolean var1) {
      if (var1) {
         switch (this.getFlags() & 61440) {
            case 4096:
               return rq.io;
            case 45056:
               return rq.qa;
            case 61440:
               return rq.Hk;
         }
      }

      switch (this.getFlags() & 61440) {
         case 4096:
            return rq.q;
         case 8192:
            return rq.RF;
         case 12288:
            return rq.xK;
         case 16384:
            return rq.Dw;
         case 20480:
            return rq.Uv;
         case 24576:
            return rq.oW;
         case 28672:
            return rq.gO;
         case 32768:
            return rq.nf;
         case 36864:
            return rq.gP;
         case 40960:
            return rq.za;
         case 45056:
            return rq.lm;
         case 49152:
            return rq.zz;
         case 53248:
            return rq.JY;
         case 57344:
            return rq.HF;
         case 61440:
            return rq.LK;
         default:
            return null;
      }
   }

   public Long Dw(long var1, int var3, CW[] var4, IMachineContext var5) {
      mi var6 = this.q(var4, var3);
      return var6 != null ? var6.q(var1, var3, var5) : null;
   }

   public static OQ q(int var0) {
      if (Rv == null) {
         Rv = new HashMap();
      }

      OQ var1 = (OQ)Rv.get(var0);
      if (var1 == null) {
         var1 = new OQ(var0);
         Rv.put(var0, var1);
      }

      return var1;
   }

   @Override
   public lH RF(CW[] var1, int var2) {
      if (var1 != null) {
         switch (this.getFlags() & 0xFF) {
            case 2:
            case 4:
               boolean var3 = xK(q(this.getFlags(), var1, var2), var2);
               if (var3) {
                  int var4 = this.getFlags() & -256 | 1;
                  return q(var4);
               }
         }
      }

      return this;
   }

   public static enum eo {
      q,
      RF,
      xK,
      Dw;

      public Integer q(int var1, int var2) {
         return this.q(var1, var2, 4);
      }

      // $VF: Unable to simplify switch on enum
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      public Integer q(int var1, int var2, int var3) {
         switch (this) {
            case q:
            default:
               return var3 * var1;
            case RF:
               return (var1 - (var2 - 1)) * var3;
            case xK:
               return (var1 - var2) * var3;
            case Dw:
               return var3 * (var1 + 1);
         }
      }

      // $VF: Unable to simplify switch on enum
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      public Integer RF(int var1, int var2) {
         switch (this) {
            case q:
            case Dw:
            default:
               return var1 * var2;
            case RF:
            case xK:
               return -var1 * var2;
         }
      }
   }
}
