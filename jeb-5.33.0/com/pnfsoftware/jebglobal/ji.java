package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import java.util.ArrayList;
import java.util.List;

public interface ji extends IX {
   ji Ab = new ji.cq();
   ji.bO rl = (var0, var1) -> Ni.pC;
   ji.bO z = (var0, var1) -> Ni.A;
   ji.Av Ek = (var0, var1) -> (var0[1] & 16) != 0;
   ji.Av hK = (var0, var1) -> true;

   Ni hasW(byte[] var1, zj var2);

   boolean hasS(byte[] var1, zj var2);

   CharSequence getSuffix(byte[] var1);

   @FunctionalInterface
   public interface Av extends ji {
      @Override
      default CharSequence getDataType(byte[] var1) throws oJ {
         return null;
      }

      @Override
      default Ni hasW(byte[] var1, zj var2) {
         return Ni.kS;
      }

      @Override
      default CharSequence getSuffix(byte[] var1) {
         return null;
      }
   }

   public static enum DH {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys;
   }

   public static class K implements EM {
      private CharSequence Er;

      public K(CharSequence var1) {
         this.Er = var1;
      }

      @Override
      public CharSequence getSuffix(byte[] var1) {
         return this.Er;
      }
   }

   public static class Sv implements YE {
      private String Er;

      public Sv(String var1) {
         this.Er = var1;
      }

      @Override
      public CharSequence getDataType(byte[] var1) {
         return this.Er;
      }
   }

   public static class Ws implements EM {
      private ZW Er;

      public Ws(IEncodedMemoryArea var1, CharSequence... var2) {
         this(new ZW(var1, var2));
      }

      public Ws(ZW var1) {
         this.Er = var1;
      }

      @Override
      public CharSequence getSuffix(byte[] var1) {
         return (CharSequence)this.Er.A(var1);
      }
   }

   @FunctionalInterface
   public interface bO extends ji {
      @Override
      default CharSequence getDataType(byte[] var1) throws oJ {
         return null;
      }

      @Override
      default boolean hasS(byte[] var1, zj var2) {
         return false;
      }

      @Override
      default CharSequence getSuffix(byte[] var1) {
         return null;
      }
   }

   public static class cq implements ji {
      @Override
      public Ni hasW(byte[] var1, zj var2) {
         return Ni.kS;
      }

      @Override
      public boolean hasS(byte[] var1, zj var2) {
         return false;
      }

      @Override
      public CharSequence getDataType(byte[] var1) throws oJ {
         return null;
      }

      @Override
      public CharSequence getSuffix(byte[] var1) {
         return null;
      }
   }

   public static class rQ implements ji.bO {
      private final ji.DH[] Er;
      private List FE = new ArrayList();
      private List Aj = new ArrayList();

      public rQ() {
         this.Er = new ji.DH[0];
      }

      public rQ(ji.DH... var1) {
         this.Er = var1;
      }

      public ji.rQ A(IEncodedMemoryArea var1) {
         this.FE.add(var1);
         return this;
      }

      public ji.rQ pC(DirectEncodedMemoryArea var1) {
         this.Aj.add(var1);
         return this;
      }

      @Override
      public Ni hasW(byte[] var1, zj var2) {
         return this.pC(var1, var2) ? Ni.pC : Ni.A;
      }

      public boolean pC(byte[] var1, zj var2) {
         boolean var3 = true;

         for (ji.DH var7 : this.Er) {
            var3 &= this.pC(var7, var1);
         }

         for (IEncodedMemoryArea var10 : this.FE) {
            var3 &= var10.decode(var1) == 0L;
         }

         for (IEncodedMemoryArea var11 : this.Aj) {
            var3 &= var11.decode(var1) == (1L << var11.getLength()) - 1L;
         }

         return var3;
      }

      private boolean pC(ji.DH var1, byte[] var2) {
         switch (var1) {
            case pC:
               return this.wS(var2);
            case A:
               return this.kS(var2);
            case kS:
               return this.A(var2);
            case wS:
               return this.pC(var2);
            case UT:
               return (var2[2] & 15) == (var2[1] & 15);
            case ys:
               return (var2[3] & 15) == (var2[2] & 15);
            case sY:
               return (var2[3] & 15) == (var2[1] & 15);
            case E:
               int var3 = var2[2] & 15;
               return var3 == (var2[3] & 15) || var3 == (var2[1] & 15);
            default:
               return false;
         }
      }

      public boolean pC(byte[] var1) {
         return (var1[1] & 8) == 0;
      }

      public boolean A(byte[] var1) {
         return (var1[2] & 128) == 0;
      }

      public boolean kS(byte[] var1) {
         return (var1[2] & 8) == 0;
      }

      public boolean wS(byte[] var1) {
         return (var1[3] & 8) == 0;
      }
   }
}
