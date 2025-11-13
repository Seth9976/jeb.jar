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

public abstract class Ls extends yi {
   private static final ILogger RF = GlobalLog.getLogger(Ls.class);
   private boolean xK;
   protected IProcessor q;

   public Ls(oH var1, int var2) {
      super(var1);
      if (var2 != 32 && var2 != 64) {
         throw new RuntimeException("Illegal initial x86 mode");
      } else if (var1.gP() != Endianness.LITTLE_ENDIAN) {
         throw new RuntimeException("x86 big-endian does not exist");
      } else {
         this.q = new cti(var2);
      }
   }

   public ctc q(long var1) throws IOException {
      byte[] var3 = new byte[16];
      int var4 = this.gO.q(var1, var3);

      try {
         ctc var5 = (ctc)this.q.parseAt(var3, 0, var4);
         if (var5 == null) {
            return null;
         } else {
            Object[] var10000 = new Object[]{var5};
            return var5;
         }
      } catch (ProcessorException var7) {
         RF.catching(var7);
         return null;
      }
   }

   @Override
   public boolean xK(long var1, int var3) {
      return true;
   }

   @Override
   public byte[] q(long var1, int var3) {
      return new byte[]{-5};
   }

   @Override
   public boolean RF(long var1, int var3) throws IOException {
      return this.nf.q(var1, 1);
   }

   @Override
   public boolean q() {
      return this.xK;
   }

   @Override
   public boolean q(boolean var1) {
      this.xK = var1;
      return true;
   }

   @Override
   public boolean q(Ht var1, boolean var2) throws IOException {
      if (!this.q()) {
         return false;
      } else {
         long var3 = var1.getFlags();
         if (var2) {
            var3 |= 256L;
         } else {
            var3 &= -257L;
         }

         if (!this.q(var1, var3)) {
            return false;
         } else {
            try {
               this.oW.q(var1);
               return true;
            } catch (WI var6) {
               RF.catching(var6);
               return false;
            }
         }
      }
   }

   protected boolean q(Ht var1, long var2) {
      RegisterDescriptionEntry var4 = RegisterUtil.getEntryByType(var1, RegisterType.Flags);
      return var4 != null && var1.setValueAsLong(var4.getNumber(), var2);
   }

   @Override
   public IProcessor RF() {
      return this.q;
   }
}
