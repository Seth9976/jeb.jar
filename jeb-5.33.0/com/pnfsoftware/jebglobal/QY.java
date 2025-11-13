package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankMips;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class QY extends RE {
   private static final ILogger pC = GlobalLog.getLogger(QY.class);
   private static final byte[] gp = new byte[]{13, 0, 5, 0};
   private static final byte[] oT = new byte[]{0, 5, 0, 13};

   public QY(aI var1) {
      super(var1, new com.pnfsoftware.jeb.corei.parsers.mips.p(32));
      this.ld = new RegisterLayoutBridge(var1.ED(), RegisterBankMips.getInstance());
      if (var1.sY() != ProcessorType.MIPS) {
         throw new kB("Expected processor type was MIPS, got: " + var1.sY());
      }
   }

   @Override
   public byte[] pC(long var1, int var3) {
      int var4 = var3 * 8;
      if (var4 == 32) {
         return this.UT.ys().isLittle() ? gp : oT;
      } else {
         return null;
      }
   }

   @Override
   public boolean A(long var1, int var3) throws IOException {
      return this.ys.pC(var1, 4);
   }

   public JW A(Tl var1) {
      try {
         return new JW(var1, this.ld);
      } catch (IOException var3) {
         pC.catching(var3);
         return null;
      }
   }
}
