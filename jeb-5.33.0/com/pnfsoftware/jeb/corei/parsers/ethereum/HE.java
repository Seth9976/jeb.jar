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
public class HE extends AbstractProcessor {
   private static final ILogger A = GlobalLog.getLogger(HE.class);
   @SerId(1)
   yt pC;

   public HE() {
      this(null);
   }

   public HE(yt var1) {
      super(1, 256, Endianness.BIG_ENDIAN, 1);
      this.pC = var1;
   }

   @Override
   public ProcessorType getType() {
      return EvmDisassemblerPlugin.pC;
   }

   @Override
   public IRegisterBank getRegisterBank() {
      return Pj.pC;
   }

   public Mh pC(IVirtualMemory var1, long var2) throws ProcessorException {
      return this.pC == null ? (Mh)super.parseAt(var1, var2) : this.pC.A(var2);
   }

   protected Mh pC(byte[] var1, int var2, int var3) throws ProcessorException {
      if (var2 < var1.length && var2 < var3) {
         int var4 = var2 + 1;
         int var5 = var1[var2] & 255;
         GK var6 = uX.A[var5];
         Mh var7 = new Mh(var6);
         if (var5 >= 96 && var5 <= 127) {
            int var8 = var5 - 96 + 1;
            byte[] var9 = new byte[33];
            int var10 = var8;
            if (var4 + var8 > var3) {
               var10 = var3 - var4;
            }

            ArrayUtil.copyBytes(var9, 33 - var8, var1, var4, var10);
            var4 += var10;
            var7.wS = new BigInteger(var9);
            var7.kS = new Tb[]{new Tb(var7.wS)};
         }

         var7.A = Arrays.copyOfRange(var1, var2, var4);
         return var7;
      } else {
         throw new ProcessorException("Not enough bytes");
      }
   }

   static {
      uX.pC = true;
   }
}
