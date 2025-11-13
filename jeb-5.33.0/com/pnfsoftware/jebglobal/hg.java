package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.InsnEmulator;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class hg extends InsnEmulator implements dW {
   public static final hg pC = pC(0);
   private static Map A;

   hg(int var1) {
      super(var1);
   }

   public static Yg[] pC(int var0, Yg[] var1, int var2) {
      switch (var0 & 0xFF) {
         case 2:
            return var1.length != 0 ? new Yg[]{var1[0]} : null;
         case 4:
            FH var3 = (FH)var1[var1.length - 1];
            if ((var0 & 1048576) != 0) {
               Yg[] var4 = new Yg[var3.E().length + 1];
               System.arraycopy(var3.E(), 0, var4, 0, var4.length - 1);
               if (var1.length >= 2) {
                  var4[var4.length - 1] = var1[0];
               } else {
                  var4[var4.length - 1] = LC.sY(var2);
               }

               return var4;
            }

            return var3.E();
         default:
            return null;
      }
   }

   private static boolean kS(Yg[] var0, int var1) {
      if (var0 == null) {
         return false;
      } else {
         for (Yg var5 : var0) {
            if (var5.A(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public int pC(int var1, long var2) {
      int var4 = var1;
      if (this.kS()) {
         return 32;
      } else if (this.wS()) {
         return 16;
      } else {
         if (this.A()) {
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
   public boolean pC() {
      return (this.getFlags() & 917504) != 0;
   }

   public boolean A() {
      return (this.getFlags() & 917504) == 524288;
   }

   @Override
   public boolean kS() {
      return (this.getFlags() & 917504) == 262144;
   }

   @Override
   public boolean wS() {
      return (this.getFlags() & 917504) == 131072;
   }

   public boolean UT() {
      return (this.getFlags() & 1048576) != 0;
   }

   @Override
   public Long pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, IMachineContext var4) throws ProcessorException {
      return this.pC(var1.kS(), var2, var1.getProcessorMode(), var1.A(), var4);
   }

   public Long pC(byte[] var1, long var2, int var4, Yg[] var5, IMachineContext var6) throws ProcessorException {
      switch (this.getFlags() & 3840) {
         case 256:
            return this.pC(var2, var4, var5, var6, null);
         case 512:
            return this.pC(var2, var4, var5, var6, false);
         case 768:
            return this.pC(var2, var4, var5, var6, true);
         case 1024:
            return this.pC(var2, var6, var1);
         case 1280:
            return this.pC(var2, var4, var5, var6);
         case 1536:
            return this.A(var2, var4, var5, var6);
         case 1792:
            return this.kS(var2, var4, var5, var6);
         case 2048:
         case 2304:
         case 2560:
            return this.wS(var2, var4, var5, var6);
         default:
            return null;
      }
   }

   public Long pC(long var1, int var3, Yg[] var4, IMachineContext var5, VZ var6) {
      if (var4 != null && var4.length != 0) {
         Yg var7 = var4[var4.length - 1];
         return var6 != null ? new sT(var6, var7).pC(var1, var3, var5) : new sT(var7).pC(var1, var3, var5);
      } else {
         return null;
      }
   }

   public Long pC(long var1, int var3, Yg[] var4, IMachineContext var5, boolean var6) throws ProcessorException {
      if (var5 == null) {
         return null;
      } else {
         Yg[] var7 = var4[0].E();
         int var8 = var7[0].pC(var1, var3, var5).intValue();
         int var9 = var7[1].pC(var1, var3, var5).intValue();
         int var10;
         if (var6) {
            var10 = PM.kS(var5, var8 + var9) & '\uffff';
         } else {
            var10 = PM.A(var5, var8 + var9) & 255;
         }

         Yg var11 = Yg.pC(32, 2L * var10);
         return new sT(VZ.pC, LC.pC(var3), var11).pC(var1, var3, var5);
      }
   }

   public Long pC(long var1, IMachineContext var3, byte[] var4) throws ProcessorException {
      int var5 = (var4[1] & 240) >>> 4;
      Deque var6 = uV.A(var4);
      ArrayList var7 = new ArrayList(var6);
      var7.add(0, Boolean.TRUE);
      if (var3 == null) {
         return var1 + 2L;
      } else {
         int var8 = PM.kS(var3);

         for (int var9 = 0; var9 < var7.size(); var9++) {
            boolean var10 = zj.pC(var5, var8);
            if (var10 == (Boolean)var7.get(var9)) {
               return this.pC(var1 + 2L, var9, var3);
            }
         }

         return this.pC(var1 + 2L, var7.size(), var3);
      }
   }

   public Long pC(long var1, int var3, Yg[] var4, IMachineContext var5) throws ProcessorException {
      if (var5 == null) {
         return null;
      } else {
         Integer var6 = var4[1].pC(var1, var3, var5).intValue();
         return (long)PM.pC(var5, var6);
      }
   }

   public hg.Av E() {
      switch (this.getFlags() & -268435456) {
         case 268435456:
            return hg.Av.pC;
         case 536870912:
            return hg.Av.A;
         case 805306368:
            return hg.Av.kS;
         case 1073741824:
            return hg.Av.wS;
         default:
            return null;
      }
   }

   public Long A(long var1, int var3, Yg[] var4, IMachineContext var5) throws ProcessorException {
      Yg var6;
      if (var4.length >= 2) {
         var6 = var4[0];
         if (this.UT() && var6.A(var3)) {
            throw new com.pnfsoftware.jeb.corei.parsers.arm.DH("Update PC is unpredictable for this LDM instruction");
         }
      } else {
         var6 = LC.sY(var3);
      }

      FH var7 = (FH)var4[var4.length - 1];
      Yg[] var8 = var7.E();
      if (var8 == null || !var8[var8.length - 1].A(var3)) {
         return null;
      } else if (var5 == null) {
         return null;
      } else {
         Integer var9 = var6.pC(var1, var3, var5).intValue();
         return (long)PM.pC(var5, var9 + this.E().pC(var8.length - 1, var8.length));
      }
   }

   private Long pC(long var1, int var3, IMachineContext var4) throws ProcessorException {
      byte[] var5 = new byte[4];

      for (int var6 = 0; var6 < var3; var6++) {
         try {
            var4.getMemory().read(var1, 4, var5, 0);
         } catch (MemoryException var8) {
            throw new ProcessorException(var8);
         }

         var1 += xn.pC(var5, var4.getInformation().getEndianness().toByteOrder()) ? 4L : 2L;
      }

      return var1;
   }

   public Long kS(long var1, int var3, Yg[] var4, IMachineContext var5) throws ProcessorException {
      Yg var6 = var4[0];
      if (var6.A(var3)) {
         throw new com.pnfsoftware.jeb.corei.parsers.arm.DH("Update PC is unpredictable for this RFE instruction");
      } else if (var5 == null) {
         return null;
      } else {
         Integer var7 = var6.pC(var1, var3, var5).intValue();
         return (long)PM.pC(var5, var7 + this.E().pC(0, 2));
      }
   }

   public sT pC(Yg[] var1, int var2) {
      if (var1 != null && var1.length != 0) {
         if (var1.length <= 1) {
            return null;
         } else if ((this.getFlags() & 3840) == 2560) {
            return new sT(VZ.pC, LC.pC(var2), var1[1]);
         } else if ((this.getFlags() & 3840) == 2048) {
            return new sT(this.pC(true), var1[1]);
         } else {
            return var1.length <= 2 ? null : new sT(this.pC(false), var1[1], var1[2]);
         }
      } else {
         return null;
      }
   }

   private VZ pC(boolean var1) {
      if (var1) {
         switch (this.getFlags() & 61440) {
            case 4096:
               return VZ.xC;
            case 45056:
               return VZ.ED;
            case 61440:
               return VZ.Sc;
         }
      }

      switch (this.getFlags() & 61440) {
         case 4096:
            return VZ.pC;
         case 8192:
            return VZ.A;
         case 12288:
            return VZ.kS;
         case 16384:
            return VZ.wS;
         case 20480:
            return VZ.UT;
         case 24576:
            return VZ.E;
         case 28672:
            return VZ.sY;
         case 32768:
            return VZ.ys;
         case 36864:
            return VZ.ld;
         case 40960:
            return VZ.gp;
         case 45056:
            return VZ.oT;
         case 49152:
            return VZ.fI;
         case 53248:
            return VZ.WR;
         case 57344:
            return VZ.NS;
         case 61440:
            return VZ.vP;
         default:
            return null;
      }
   }

   public Long wS(long var1, int var3, Yg[] var4, IMachineContext var5) {
      sT var6 = this.pC(var4, var3);
      return var6 != null ? var6.pC(var1, var3, var5) : null;
   }

   public static hg pC(int var0) {
      if (A == null) {
         A = new HashMap();
      }

      hg var1 = (hg)A.get(var0);
      if (var1 == null) {
         var1 = new hg(var0);
         A.put(var0, var1);
      }

      return var1;
   }

   @Override
   public dW A(Yg[] var1, int var2) {
      if (var1 != null) {
         switch (this.getFlags() & 0xFF) {
            case 2:
            case 4:
               boolean var3 = kS(pC(this.getFlags(), var1, var2), var2);
               if (var3) {
                  int var4 = this.getFlags() & -256 | 1;
                  return pC(var4);
               }
         }
      }

      return this;
   }

   public static enum Av {
      pC,
      A,
      kS,
      wS;

      public Integer pC(int var1, int var2) {
         return this.pC(var1, var2, 4);
      }

      public Integer pC(int var1, int var2, int var3) {
         switch (this) {
            case pC:
            default:
               return var3 * var1;
            case A:
               return (var1 - (var2 - 1)) * var3;
            case kS:
               return (var1 - var2) * var3;
            case wS:
               return var3 * (var1 + 1);
         }
      }

      public Integer A(int var1, int var2) {
         switch (this) {
            case pC:
            case wS:
            default:
               return var1 * var2;
            case A:
            case kS:
               return -var1 * var2;
         }
      }
   }
}
