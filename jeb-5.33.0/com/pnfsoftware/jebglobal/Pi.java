package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerException;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public abstract class Pi extends RE {
   private static final ILogger gp = GlobalLog.getLogger(Pi.class);
   protected int pC;

   public Pi(aI var1, int var2) {
      super(var1, new com.pnfsoftware.jeb.corei.parsers.arm.cq(var2, null));
      if (var2 != 32 && var2 != 64) {
         throw new RuntimeException("Illegal initial ARM mode");
      } else if (var1.ys() != Endianness.LITTLE_ENDIAN) {
         throw new RuntimeException("ARM big-endian debugging is not supported at the moment");
      } else {
         this.pC = var2;
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.arm.rQ pC(LD var1) throws IOException {
      if (this.pC != 64) {
         long var2 = var1.getFlags();
         int var4 = (var2 & 32L) != 0L ? 16 : this.pC;

         try {
            this.A.setMode(var4);
         } catch (ProcessorException var6) {
            throw new DebuggerException(var6);
         }
      }

      return (com.pnfsoftware.jeb.corei.parsers.arm.rQ)super.A(var1);
   }

   @Override
   public byte[] pC(long var1, int var3) {
      switch (var3) {
         case 2:
            return new byte[]{-66, -66};
         case 4:
            return new byte[]{-2, -34, -1, -25};
         default:
            throw new RuntimeException("No byte sequence found for bp type " + var3);
      }
   }

   @Override
   public boolean A(long var1, int var3) throws IOException {
      if (var3 == 0) {
         var3 = this.A.getMode();
      }

      switch (var3) {
         case 16:
            return this.ys.pC(var1, 2);
         case 32:
         case 64:
            return this.ys.pC(var1, 4);
         default:
            gp.warn("Unsupported processor mode for ARM breakpoint: %d", var3);
            return false;
      }
   }
}
