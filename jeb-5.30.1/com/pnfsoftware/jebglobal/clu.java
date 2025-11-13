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
public class clu extends AbstractInstructionManager {
   private static final IEncodedMemoryArea q = DirectEncodedMemoryArea.get(0, 32);
   private clc RF;

   public clu(clc var1) {
      this.RF = var1;
   }

   public cmd q(BytesBlock var1) throws ProcessorException {
      byte[] var2 = var1.getBECode();
      cmf var3 = cme.q(var2, this.RF.q(), this.RF.RF(), this.RF.getMode());
      if (var3 != null && !var3.Uv()) {
         if (this.RF.getMode() == 32 && var3.RF()) {
            throw new ProcessorException(Strings.f("Instruction %s is reserved (64-bit)", Formatter.byteArrayToHex(var2)));
         } else if (!this.RF.RF() && !var3.q(this.RF.q())) {
            throw new ProcessorException(
               var3.q(var2) + " is not allowed regarding the current version. You can enable compatibility mode to ignore these errors"
            );
         } else {
            return new cmd(var1, var3.q(var2), var3.RF(var2, this.RF.getMode()), var3.q(), var3.Dw(), this.RF.getMode());
         }
      } else {
         throw new ProcessorException(getUndefinedMessage(var2));
      }
   }

   public static int q(IMachineContext var0, long var1) {
      if (var0 != null && var0.getMemory() != null) {
         byte[] var3 = new byte[4];

         try {
            var0.getMemory().read(var1, 4, var3, 0);
         } catch (MemoryException var4) {
            return 4;
         }

         return q(var3);
      } else {
         return 4;
      }
   }

   public static int q(byte[] var0) {
      return RF(var0) ? 2 : 4;
   }

   public static boolean RF(byte[] var0) {
      return false;
   }
}
