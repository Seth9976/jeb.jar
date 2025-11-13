package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionManager;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class cfi extends AbstractInstructionManager {
   private static final IEncodedMemoryArea pC = DirectEncodedMemoryArea.get(0, 32);
   private com.pnfsoftware.jeb.corei.parsers.mips.p A;

   public cfi(com.pnfsoftware.jeb.corei.parsers.mips.p var1) {
      this.A = var1;
   }

   public cfq pC(BytesBlock var1) throws ProcessorException {
      byte[] var2 = var1.getBECode();
      cfs var3 = cfr.pC(var2, this.A.pC(), this.A.A(), this.A.getMode());
      if (var3 != null && !var3.wS()) {
         if (this.A.getMode() == 32 && var3.pC()) {
            throw new ProcessorException(Strings.f("Instruction %s is reserved (64-bit)", Formatter.byteArrayToHex(var2)));
         } else if (!this.A.A() && !var3.pC(this.A.pC())) {
            throw new ProcessorException(
               var3.pC(var2) + " is not allowed regarding the current version. You can enable compatibility mode to ignore these errors"
            );
         } else {
            return new cfq(var1, var3.pC(var2), var3.pC(var2, this.A.getMode()), var3.A(), var3.kS(), this.A.getMode());
         }
      } else {
         throw new ProcessorException(getUndefinedMessage(var2));
      }
   }

   public static int pC(IMachineContext var0, long var1) {
      if (var0 != null && var0.getMemory() != null) {
         byte[] var3 = new byte[4];

         try {
            var0.getMemory().read(var1, 4, var3, 0);
         } catch (MemoryException var4) {
            return 4;
         }

         return pC(var3);
      } else {
         return 4;
      }
   }

   public static int pC(byte[] var0) {
      return A(var0) ? 2 : 4;
   }

   public static boolean A(byte[] var0) {
      return false;
   }
}
