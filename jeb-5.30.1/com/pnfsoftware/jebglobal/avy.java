package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public enum avy {
   q(1),
   RF(100),
   xK(150),
   Dw(175),
   Uv(200),
   oW(250),
   gO(300),
   nf(350),
   gP(400),
   za(450),
   lm(500),
   zz(550),
   JY(600),
   HF(620),
   LK(650),
   io(675),
   qa(700),
   Hk(705),
   Me(710),
   PV(750),
   oQ(800),
   xW(900),
   KT(999);

   private final int Gf;

   private avy(int var3) {
      if (var3 > 0 && var3 < 1000) {
         this.Gf = var3;
      } else {
         throw new IllegalArgumentException("Valid stage id must be in [1,999]");
      }
   }

   public int q() {
      return this.Gf;
   }

   public static avy q(int var0) {
      for (avy var4 : values()) {
         if (var4.Gf == var0) {
            return var4;
         }
      }

      throw new JebRuntimeException("Illegal decompilation stage id: " + var0);
   }

   public static avy RF() {
      return q;
   }

   public static avy xK() {
      return KT;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public NativeDecompilationStage Dw() {
      switch (this) {
         case RF:
            return NativeDecompilationStage.RAW_CONVERSION;
         case xK:
            return NativeDecompilationStage.IR_CONVERSION;
         case Dw:
            return NativeDecompilationStage.SIMULATION;
         case gP:
            return NativeDecompilationStage.STACK_ANALYSIS;
         case zz:
            return NativeDecompilationStage.TYPES_APPLICATION;
         case PV:
            return NativeDecompilationStage.LIFTING;
         case KT:
            return NativeDecompilationStage.COMPLETED;
         default:
            return NativeDecompilationStage.UNKNOWN;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static avy q(NativeDecompilationStage var0) {
      if (var0 != null) {
         switch (var0) {
            case RAW_CONVERSION:
               return RF;
            case IR_CONVERSION:
               return xK;
            case SIMULATION:
               return Dw;
            case STACK_ANALYSIS:
               return gP;
            case TYPES_APPLICATION:
               return zz;
            case LIFTING:
               return PV;
            case COMPLETED:
               return KT;
         }
      }

      return null;
   }
}
