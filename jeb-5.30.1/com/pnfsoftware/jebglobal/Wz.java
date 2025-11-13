package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankMips;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class Wz extends JT {
   private static final ILogger q = GlobalLog.getLogger(Wz.class);
   private static final byte[] za = new byte[]{13, 0, 5, 0};
   private static final byte[] lm = new byte[]{0, 5, 0, 13};

   public Wz(oH var1) {
      super(var1, new clc(32));
      this.gP = new RegisterLayoutBridge(var1.Hk(), RegisterBankMips.getInstance());
      if (var1.nf() != ProcessorType.MIPS) {
         throw new uS("Expected processor type was MIPS, got: " + var1.nf());
      }
   }

   @Override
   public byte[] q(long var1, int var3) {
      int var4 = var3 * 8;
      if (var4 == 32) {
         return this.Uv.gP().isLittle() ? za : lm;
      } else {
         return null;
      }
   }

   @Override
   public boolean RF(long var1, int var3) throws IOException {
      return this.nf.q(var1, 4);
   }

   public Ut RF(kW var1) {
      try {
         return new Ut(var1, this.gP);
      } catch (IOException var3) {
         q.catching(var3);
         return null;
      }
   }
}
