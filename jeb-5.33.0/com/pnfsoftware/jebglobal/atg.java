package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public enum atg {
   pC(1),
   A(100),
   kS(150),
   wS(175),
   UT(200),
   E(250),
   sY(300),
   ys(350),
   ld(400),
   gp(450),
   oT(500),
   fI(550),
   WR(600),
   NS(620),
   vP(650),
   xC(675),
   ED(700),
   Sc(705),
   ah(710),
   eP(750),
   UO(800),
   Ab(900),
   rl(999);

   private final int z;

   private atg(int var3) {
      if (var3 > 0 && var3 < 1000) {
         this.z = var3;
      } else {
         throw new IllegalArgumentException("Valid stage id must be in [1,999]");
      }
   }

   public int pC() {
      return this.z;
   }

   public static atg pC(int var0) {
      for (atg var4 : values()) {
         if (var4.z == var0) {
            return var4;
         }
      }

      throw new JebRuntimeException("Illegal decompilation stage id: " + var0);
   }

   public static atg A() {
      return rl;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public NativeDecompilationStage kS() {
      switch (this) {
         case A:
            return NativeDecompilationStage.RAW_CONVERSION;
         case kS:
            return NativeDecompilationStage.IR_CONVERSION;
         case wS:
            return NativeDecompilationStage.SIMULATION;
         case ld:
            return NativeDecompilationStage.STACK_ANALYSIS;
         case fI:
            return NativeDecompilationStage.TYPES_APPLICATION;
         case eP:
            return NativeDecompilationStage.LIFTING;
         case rl:
            return NativeDecompilationStage.COMPLETED;
         default:
            return NativeDecompilationStage.UNKNOWN;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static atg pC(NativeDecompilationStage var0) {
      if (var0 != null) {
         switch (var0) {
            case RAW_CONVERSION:
               return A;
            case IR_CONVERSION:
               return kS;
            case SIMULATION:
               return wS;
            case STACK_ANALYSIS:
               return ld;
            case TYPES_APPLICATION:
               return fI;
            case LIFTING:
               return eP;
            case COMPLETED:
               return rl;
         }
      }

      return null;
   }
}
