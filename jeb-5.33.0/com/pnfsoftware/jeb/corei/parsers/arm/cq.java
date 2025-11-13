package com.pnfsoftware.jeb.corei.parsers.arm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.ICodeResolver;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IResolvedOperandValue;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.ResolvedOperandByteValue;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Characters;
import com.pnfsoftware.jeb.util.primitives.Integers;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.CJ;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.PM;
import com.pnfsoftware.jebglobal.PU;
import com.pnfsoftware.jebglobal.Qi;
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.iN;
import com.pnfsoftware.jebglobal.xn;
import java.nio.ByteOrder;
import java.util.Arrays;

@Ser
public class cq extends AbstractProcessor implements ICodeResolver, zp {
   private static final ILogger pC = GlobalLog.getLogger(cq.class);
   @SerTransient
   private Qi A;
   @SerTransient
   private xn kS;
   @SerTransient
   private CJ wS;
   @SerId(1)
   private Endianness UT;

   @SerCustomInitPostGraph
   private void A() {
      this.A = new Qi();
      this.kS = new xn();
      this.wS = new CJ();
   }

   public cq(int var1, IUnitCreator var2) {
      super(pC(var1), var1, var2, A(var1), 4);
      this.supportedModes.add(16);
      this.supportedModes.add(32);
      this.supportedModes.add(64);
      if (var1 == 64) {
         this.UT = Endianness.LITTLE_ENDIAN;
      } else if (var2 instanceof IELFUnit) {
         int var3 = ((IELFUnit)var2).getHeader().getFlags();
         if ((var3 & 8388608) != 0) {
            this.UT = Endianness.LITTLE_ENDIAN;
         } else {
            this.UT = ((IELFUnit)var2).getHeader().getEndianness();
         }
      } else {
         this.UT = Endianness.LITTLE_ENDIAN;
      }

      this.A();
   }

   private static int pC(int var0) {
      return var0 == 64 ? 4 : 18;
   }

   private static int A(int var0) {
      return var0 == 64 ? 4 : 2;
   }

   @Override
   public int getPCRegisterBitsize() {
      return this.getMode() == 64 ? 64 : 32;
   }

   @Override
   public int getGPRegisterBitsize() {
      return this.getPCRegisterBitsize();
   }

   @Override
   public String getRegisterName(long var1) {
      return LC.kS(var1, this.mode);
   }

   @Override
   public rQ pC(IVirtualMemory var1, long var2, int var4) throws ProcessorException {
      int var5 = this.getMode();
      this.kS.pC(false);

      rQ var6;
      try {
         if (var4 != 0) {
            try {
               this.setMode(var4);
            } catch (ProcessorException var15) {
               throw new RuntimeException(Strings.ff("incorrect mode %d defined at address %xh", var4, var2));
            }
         }

         var6 = (rQ)this.parseAt(var1, var2);
      } finally {
         this.kS.pC(true);
         if (var4 != 0) {
            try {
               this.setMode(var5);
            } catch (ProcessorException var14) {
               pC.catchingSilent(var14);
            }
         }
      }

      return var6;
   }

   protected rQ pC(IMachineContext var1) throws ProcessorException {
      byte[] var2 = new byte[8];
      long var3 = var1.getRegisters().getProgramCounter();
      int var5 = var3 < 4L ? 0 : 4;

      try {
         var1.getMemory().read(var3 - var5, 8, var2, 0);
      } catch (MemoryException var8) {
         throw new ProcessorException(var8);
      }

      if (this.getMode() != 64) {
         boolean var6 = PM.wS(var1);
         if (var6 && this.getMode() != 16) {
            this.setMode(16);
         } else if (!var6 && this.getMode() == 16) {
            this.setMode(32);
         }

         boolean var7 = PM.UT(var1);
         if (var7) {
            throw new ProcessorException("Processor entered Jazelle mode which is not supported by JEB");
         }
      }

      return this.pC(var2, var5, var5 + 4);
   }

   protected rQ pC(byte[] var1, int var2, int var3) throws ProcessorException {
      byte[] var4 = Arrays.copyOfRange(var1, var2, Math.min(var2 + 4, var3));
      rQ var5;

      var5 = switch (this.getMode()) {
         default -> {
            if (var4.length < 4) {
               throw new ProcessorException(Strings.ff("Invalid ARM instruction %s must be 32 bit instruction", Formatter.byteArrayToHexString(var4)));
            }

            yield (rQ)this.A.getInstruction(new BytesBlock(var4, this.pC(), 4));
         }
         case 16 -> {
            if (var4.length < 2) {
               throw new ProcessorException(Strings.ff("Invalid ARM Thumb instruction %s must be 16 bit instruction", Formatter.byteArrayToHex(var4)));
            }

            yield this.pC(var4);
            if (var5.fI()) {
               int var6 = this.kS.pC(var5);
               if (var2 + var3 < 2 + 2 * var6) {
                  this.kS.A();
                  throw new ProcessorException("Can not validate instructions of the IT Block");
               }

               int var7 = var2 + var5.getSize();

               for (int var8 = 0; var8 < var6; var8++) {
                  byte[] var9 = Arrays.copyOfRange(var1, var7, Math.min(var7 + 4, var3));
                  if (var9.length < 2) {
                     this.kS.A();
                     throw new ProcessorException("Can not validate instructions of the IT Block");
                  }

                  var5 = this.pC(var9);
                  if (!this.kS.pC() && xn.pC(var5, var8 == var6 - 1)) {
                     throw new ProcessorException("Illegal instruction in the middle of an IT Block");
                  }

                  var7 += var5.getSize();
               }

               this.kS.A();
               yield this.pC(var4);
            }
         }
         case 64 -> (rQ)this.wS.getInstruction(new BytesBlock(var4, this.pC(), 4));
      };

      if (PU.E(var5)) {
         boolean var11 = false;
         if ((this.getMode() == 32 || this.getMode() == 0) && var2 >= 4) {
            byte[] var12 = Arrays.copyOfRange(var1, var2 - 4, Math.min(var2, var3));
            if (this.pC() == Endianness.LITTLE_ENDIAN) {
               ArrayUtil.swap(var12);
            }

            int var13 = Integers.safeInt(var5.UT().A(), 14);
            int var14 = var13 << 4 | 1;
            if (ArrayUtil.compareBytes(var12, 0, new byte[]{(byte)var14, -96, -32, 15}, 0, 4) == 0) {
               var11 = true;
            }
         }

         if (var11) {
            return new iN(var5);
         }
      }

      return var5;
   }

   private ByteOrder kS() {
      return this.pC().toByteOrder();
   }

   public rQ pC(byte[] var1) throws ProcessorException {
      if (xn.pC(var1, this.kS())) {
         if (var1.length < 4) {
            throw new ProcessorException(Strings.ff("Invalid ARM instruction %s must be 32 bit instruction", Formatter.byteArrayToHex(var1)));
         }
      } else {
         var1 = Arrays.copyOfRange(var1, 0, 2);
      }

      return this.kS.A(new BytesBlock(var1, this.pC(), 2));
   }

   @Override
   public ICodeResolver getResolver() {
      return this;
   }

   public ICodePointer pC(IMachineContext var1, rQ var2) throws ProcessorException {
      return var2.pC(var1);
   }

   public IResolvedOperandValue pC(IMachineContext var1, rQ var2, int var3) throws ProcessorException {
      Yg[] var4 = var2.A();
      if (var4 != null && var3 < var4.length) {
         Yg var5 = var4[var3];
         Long var6 = var5.pC(var1.getRegisters().getProgramCounter(), this.getMode(), var1);
         if (var6 == null) {
            return null;
         } else {
            switch (var5.getOperandType()) {
               case 0:
               case 1:
                  return new ResolvedOperandByteValue(var6, this.getMode() == 64 ? 64 : 32, var1.getInformation().getEndianness().toByteOrder());
               case 2:
               case 3:
                  byte[] var7 = new byte[4];
                  int var8 = VirtualMemoryUtil.readBytesSafe(var1.getMemory(), var6, 4, var7, 0, 1);
                  if (var8 == 4) {
                     if (Characters.isAsciiChar(var7[0])
                        && Characters.isAsciiChar(var7[1])
                        && Characters.isAsciiChar(var7[2])
                        && Characters.isAsciiChar(var7[3])) {
                        return new ResolvedOperandByteValue(var7);
                     }

                     return new ResolvedOperandByteValue(var7);
                  }

                  return new ResolvedOperandByteValue(var6, this.getMode() == 64 ? 64 : 32, var1.getInformation().getEndianness().toByteOrder());
               case 4:
               case 5:
               default:
                  return null;
               case 6:
                  return null;
            }
         }
      } else {
         return null;
      }
   }

   @Override
   public int setMode(int var1) throws ProcessorException {
      if (this.getDefaultMode() == 64 && var1 != 64 && var1 != 0) {
         pC.error(S.L("Cannot change mode in arm64"));
         return this.getDefaultMode();
      } else if ((this.getDefaultMode() == 32 || this.getDefaultMode() == 16) && var1 == 64) {
         pC.error(S.L("Cannot change mode to arm64"));
         return this.getDefaultMode();
      } else {
         return super.setMode(var1);
      }
   }

   @Override
   public boolean isRISC() {
      return true;
   }

   @Override
   public ProcessorType getType() {
      switch (this.getMode()) {
         case 64:
            return ProcessorType.ARM64;
         default:
            return ProcessorType.ARM;
      }
   }

   @Override
   public CodePointer createEntryPoint(long var1, int var3) {
      if (this.getDefaultMode() == 64) {
         if ((var1 & 3L) != 0L) {
            pC.debug("Unaligned address detected at %xh", var1);
            var1 = -1L;
         }

         return new CodePointer(var1, 64);
      } else {
         return (var1 & 3L) != 0L ? new CodePointer(var1 & -2L, 16) : new CodePointer(var1, var3 == 0 ? 32 : var3);
      }
   }

   @Override
   public CodePointer createEntryPoint(long var1) {
      return this.createEntryPoint(var1, 32);
   }

   @Override
   public boolean clearInstructionCache() {
      this.A.clearCache();
      this.wS.clearCache();
      this.kS.clearCache();
      return true;
   }

   public Endianness pC() {
      return this.UT != null ? this.UT : this.getEndianness();
   }
}
