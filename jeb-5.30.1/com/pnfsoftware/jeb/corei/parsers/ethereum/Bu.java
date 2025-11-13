package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.Arrays;

@Ser
public class Bu extends AbstractProcessor {
   private static final ILogger RF = GlobalLog.getLogger(Bu.class);
   @SerId(1)
   PY q;

   public Bu() {
      this(null);
   }

   public Bu(PY var1) {
      super(1, 256, Endianness.BIG_ENDIAN, 1);
      this.q = var1;
   }

   @Override
   public ProcessorType getType() {
      return Xa.RF;
   }

   @Override
   public IRegisterBank getRegisterBank() {
      return LR.q;
   }

   public vX q(IVirtualMemory var1, long var2) throws ProcessorException {
      return this.q == null ? (vX)super.parseAt(var1, var2) : this.q.RF(var2);
   }

   vX q(int var1) throws ProcessorException {
      if (this.q == null) {
         throw new IllegalStateException();
      } else {
         return this.q(this.q.Uv, var1, this.q.Uv.length);
      }
   }

   protected vX q(byte[] var1, int var2, int var3) throws ProcessorException {
      if (var2 < var1.length && var2 < var3) {
         int var4 = var2 + 1;
         int var5 = var1[var2] & 255;
         CI var6 = zJ.o[var5];
         vX var7 = new vX(var6);
         if (var5 >= 96 && var5 <= 127) {
            int var8 = var5 - 96 + 1;
            byte[] var9 = new byte[33];
            int var10 = var8;
            if (var4 + var8 > var3) {
               var10 = var3 - var4;
            }

            ArrayUtil.copyBytes(var9, 33 - var8, var1, var4, var10);
            var4 += var10;
            var7.lm = new BigInteger(var9);
            var7.za = new KZ[]{new KZ(var7.lm)};
         }

         var7.gP = Arrays.copyOfRange(var1, var2, var4);
         return var7;
      } else {
         throw new ProcessorException("Not enough bytes");
      }
   }

   static {
      zJ.q = true;
   }
}
