package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import java.util.ArrayList;
import java.util.List;

public interface de extends Dm {
   de xW = new de.tw();
   de.iZ KT = (var0, var1) -> Vc.q;
   de.iZ Gf = (var0, var1) -> Vc.RF;
   de.CU Ef = (var0, var1) -> (var0[1] & 16) != 0;
   de.CU cC = (var0, var1) -> true;

   Vc hasW(byte[] var1, mZ var2);

   boolean hasS(byte[] var1, mZ var2);

   CharSequence getSuffix(byte[] var1);

   @FunctionalInterface
   public interface CU extends de {
      @Override
      default CharSequence getDataType(byte[] var1) throws eK {
         return null;
      }

      @Override
      default Vc hasW(byte[] var1, mZ var2) {
         return Vc.xK;
      }

      @Override
      default CharSequence getSuffix(byte[] var1) {
         return null;
      }
   }

   public static enum EE {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf;
   }

   public static class Nt implements de.oM {
      private dD sH;

      public Nt(IEncodedMemoryArea var1, CharSequence... var2) {
         this(new dD(var1, var2));
      }

      public Nt(dD var1) {
         this.sH = var1;
      }

      @Override
      public CharSequence getSuffix(byte[] var1) {
         return (CharSequence)this.sH.RF(var1);
      }
   }

   public static class ej implements de.oM {
      private CharSequence sH;

      public ej(CharSequence var1) {
         this.sH = var1;
      }

      @Override
      public CharSequence getSuffix(byte[] var1) {
         return this.sH;
      }
   }

   @FunctionalInterface
   public interface eo extends de {
      @Override
      default CharSequence getSuffix(byte[] var1) {
         return null;
      }

      @Override
      default Vc hasW(byte[] var1, mZ var2) {
         return Vc.xK;
      }

      @Override
      default boolean hasS(byte[] var1, mZ var2) {
         return false;
      }
   }

   @FunctionalInterface
   public interface iZ extends de {
      @Override
      default CharSequence getDataType(byte[] var1) throws eK {
         return null;
      }

      @Override
      default boolean hasS(byte[] var1, mZ var2) {
         return false;
      }

      @Override
      default CharSequence getSuffix(byte[] var1) {
         return null;
      }
   }

   public static class nI implements de.eo {
      private String sH;

      public nI(String var1) {
         this.sH = var1;
      }

      @Override
      public CharSequence getDataType(byte[] var1) {
         return this.sH;
      }
   }

   @FunctionalInterface
   public interface oM extends de {
      @Override
      default CharSequence getDataType(byte[] var1) throws eK {
         return null;
      }

      @Override
      default Vc hasW(byte[] var1, mZ var2) {
         return Vc.xK;
      }

      @Override
      default boolean hasS(byte[] var1, mZ var2) {
         return false;
      }
   }

   public static class qV implements de.iZ {
      private final de.EE[] sH;
      private List CE = new ArrayList();
      private List wF = new ArrayList();

      public qV() {
         this.sH = new de.EE[0];
      }

      public qV(de.EE... var1) {
         this.sH = var1;
      }

      public de.qV RF(IEncodedMemoryArea var1) {
         this.CE.add(var1);
         return this;
      }

      public de.qV q(DirectEncodedMemoryArea var1) {
         this.wF.add(var1);
         return this;
      }

      @Override
      public Vc hasW(byte[] var1, mZ var2) {
         return this.q(var1, var2) ? Vc.q : Vc.RF;
      }

      public boolean q(byte[] var1, mZ var2) {
         boolean var3 = true;

         for (de.EE var7 : this.sH) {
            var3 &= this.q(var7, var1);
         }

         for (IEncodedMemoryArea var10 : this.CE) {
            var3 &= var10.decode(var1) == 0L;
         }

         for (IEncodedMemoryArea var11 : this.wF) {
            var3 &= var11.decode(var1) == (1L << var11.getLength()) - 1L;
         }

         return var3;
      }

      // $VF: Unable to simplify switch on enum
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      private boolean q(de.EE var1, byte[] var2) {
         switch (var1) {
            case q:
               return this.Dw(var2);
            case RF:
               return this.xK(var2);
            case xK:
               return this.RF(var2);
            case Dw:
               return this.q(var2);
            case Uv:
               return (var2[2] & 15) == (var2[1] & 15);
            case nf:
               return (var2[3] & 15) == (var2[2] & 15);
            case gO:
               return (var2[3] & 15) == (var2[1] & 15);
            case oW:
               int var3 = var2[2] & 15;
               return var3 == (var2[3] & 15) || var3 == (var2[1] & 15);
            default:
               return false;
         }
      }

      public boolean q(byte[] var1) {
         return (var1[1] & 8) == 0;
      }

      public boolean RF(byte[] var1) {
         return (var1[2] & 128) == 0;
      }

      public boolean xK(byte[] var1) {
         return (var1[2] & 8) == 0;
      }

      public boolean Dw(byte[] var1) {
         return (var1[3] & 8) == 0;
      }
   }

   public static class tw implements de {
      @Override
      public Vc hasW(byte[] var1, mZ var2) {
         return Vc.xK;
      }

      @Override
      public boolean hasS(byte[] var1, mZ var2) {
         return false;
      }

      @Override
      public CharSequence getDataType(byte[] var1) throws eK {
         return null;
      }

      @Override
      public CharSequence getSuffix(byte[] var1) {
         return null;
      }
   }
}
