package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public abstract class So extends NK {
   private static final ILogger A = GlobalLog.getLogger(So.class);
   private boolean kS;
   protected IProcessor pC;

   public So(aI var1, int var2) {
      super(var1);
      if (var2 != 32 && var2 != 64) {
         throw new RuntimeException("Illegal initial x86 mode");
      } else if (var1.ys() != Endianness.LITTLE_ENDIAN) {
         throw new RuntimeException("x86 big-endian does not exist");
      } else {
         this.pC = new com.pnfsoftware.jeb.corei.parsers.x86.Or(var2);
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.x86.vh pC(long var1) throws IOException {
      byte[] var3 = new byte[16];
      int var4 = this.sY.pC(var1, var3);

      try {
         com.pnfsoftware.jeb.corei.parsers.x86.vh var5 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)this.pC.parseAt(var3, 0, var4);
         if (var5 == null) {
            return null;
         } else {
            Object[] var10000 = new Object[]{var5};
            return var5;
         }
      } catch (ProcessorException var7) {
         A.catching(var7);
         return null;
      }
   }

   @Override
   public boolean kS(long var1, int var3) {
      return true;
   }

   @Override
   public byte[] pC(long var1, int var3) {
      return new byte[]{-5};
   }

   @Override
   public boolean A(long var1, int var3) throws IOException {
      return this.ys.pC(var1, 1);
   }

   @Override
   public boolean pC() {
      return this.kS;
   }

   @Override
   public boolean pC(boolean var1) {
      this.kS = var1;
      return true;
   }

   @Override
   public boolean pC(LD var1, boolean var2) throws IOException {
      if (!this.pC()) {
         return false;
      } else {
         long var3 = var1.getFlags();
         if (var2) {
            var3 |= 256L;
         } else {
            var3 &= -257L;
         }

         if (!this.pC(var1, var3)) {
            return false;
         } else {
            try {
               this.E.pC(var1);
               return true;
            } catch (Ae var6) {
               A.catching(var6);
               return false;
            }
         }
      }
   }

   protected boolean pC(LD var1, long var2) {
      RegisterDescriptionEntry var4 = RegisterUtil.getEntryByType(var1, RegisterType.Flags);
      return var4 != null && var1.setValueAsLong(var4.getNumber(), var2);
   }

   @Override
   public IProcessor A() {
      return this.pC;
   }
}
