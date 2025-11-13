package com.pnfsoftware.jebglobal;

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
import java.nio.ByteOrder;
import java.util.Arrays;

@Ser
public class vh extends AbstractProcessor implements ICodeResolver, hB {
   private static final long q = -2L;
   private static final ILogger RF = GlobalLog.getLogger(vh.class);
   @SerTransient
   private Ro xK;
   @SerTransient
   private cL Dw;
   @SerTransient
   private ty Uv;
   @SerId(1)
   private Endianness oW;

   @SerCustomInitPostGraph
   private void RF() {
      this.xK = new Ro();
      this.Dw = new cL();
      this.Uv = new ty();
   }

   public vh(int var1, IUnitCreator var2) {
      super(q(var1), var1, var2, RF(var1), 4);
      this.supportedModes.add(16);
      this.supportedModes.add(32);
      this.supportedModes.add(64);
      if (var1 == 64) {
         this.oW = Endianness.LITTLE_ENDIAN;
      } else if (var2 instanceof IELFUnit) {
         int var3 = ((IELFUnit)var2).getHeader().getFlags();
         if ((var3 & 8388608) != 0) {
            this.oW = Endianness.LITTLE_ENDIAN;
         } else {
            this.oW = ((IELFUnit)var2).getHeader().getEndianness();
         }
      } else {
         this.oW = Endianness.LITTLE_ENDIAN;
      }

      this.RF();
   }

   private static int q(int var0) {
      return var0 == 64 ? 4 : 18;
   }

   private static int RF(int var0) {
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
      return GC.xK(var1, this.mode);
   }

   @Override
   public fA q(IVirtualMemory var1, long var2, int var4) throws ProcessorException {
      int var5 = this.getMode();
      this.Dw.q(false);

      fA var6;
      try {
         if (var4 != 0) {
            try {
               this.setMode(var4);
            } catch (ProcessorException var15) {
               throw new RuntimeException(Strings.ff("incorrect mode %d defined at address %xh", var4, var2));
            }
         }

         var6 = (fA)this.parseAt(var1, var2);
      } finally {
         this.Dw.q(true);
         if (var4 != 0) {
            try {
               this.setMode(var5);
            } catch (ProcessorException var14) {
               RF.catchingSilent(var14);
            }
         }
      }

      return var6;
   }

   protected fA q(IMachineContext var1) throws ProcessorException {
      byte[] var2 = new byte[8];
      long var3 = var1.getRegisters().getProgramCounter();
      int var5 = var3 < 4L ? 0 : 4;

      try {
         var1.getMemory().read(var3 - var5, 8, var2, 0);
      } catch (MemoryException var8) {
         throw new ProcessorException(var8);
      }

      if (this.getMode() != 64) {
         boolean var6 = rT.Dw(var1);
         if (var6 && this.getMode() != 16) {
            this.setMode(16);
         } else if (!var6 && this.getMode() == 16) {
            this.setMode(32);
         }

         boolean var7 = rT.Uv(var1);
         if (var7) {
            throw new ProcessorException("Processor entered Jazelle mode which is not supported by JEB");
         }
      }

      return this.q(var2, var5, var5 + 4);
   }

   protected fA q(byte[] var1, int var2, int var3) throws ProcessorException {
      byte[] var4 = Arrays.copyOfRange(var1, var2, Math.min(var2 + 4, var3));
      fA var5;

      var5 = switch (this.getMode()) {
         default -> {
            if (var4.length < 4) {
               throw new ProcessorException(Strings.ff("Invalid ARM instruction %s must be 32 bit instruction", Formatter.byteArrayToHexString(var4)));
            }

            yield (fA)this.xK.getInstruction(new BytesBlock(var4, this.q(), 4));
         }
         case 16 -> {
            if (var4.length < 2) {
               throw new ProcessorException(Strings.ff("Invalid ARM Thumb instruction %s must be 16 bit instruction", Formatter.byteArrayToHex(var4)));
            }

            yield this.q(var4);
            if (var5.zz()) {
               int var6 = this.Dw.q(var5);
               if (var2 + var3 < 2 + 2 * var6) {
                  this.Dw.RF();
                  throw new ProcessorException("Can not validate instructions of the IT Block");
               }

               int var7 = var2 + var5.getSize();

               for (int var8 = 0; var8 < var6; var8++) {
                  byte[] var9 = Arrays.copyOfRange(var1, var7, Math.min(var7 + 4, var3));
                  if (var9.length < 2) {
                     this.Dw.RF();
                     throw new ProcessorException("Can not validate instructions of the IT Block");
                  }

                  var5 = this.q(var9);
                  if (!this.Dw.q() && cL.q(var5, var8 == var6 - 1)) {
                     throw new ProcessorException("Illegal instruction in the middle of an IT Block");
                  }

                  var7 += var5.getSize();
               }

               this.Dw.RF();
               yield this.q(var4);
            }
         }
         case 64 -> (fA)this.Uv.getInstruction(new BytesBlock(var4, this.q(), 4));
      };

      if (OC.oW(var5)) {
         boolean var11 = false;
         if ((this.getMode() == 32 || this.getMode() == 0) && var2 >= 4) {
            byte[] var12 = Arrays.copyOfRange(var1, var2 - 4, Math.min(var2, var3));
            if (this.q() == Endianness.LITTLE_ENDIAN) {
               ArrayUtil.swap(var12);
            }

            int var13 = Integers.safeInt(var5.Uv().RF(), 14);
            int var14 = var13 << 4 | 1;
            if (ArrayUtil.compareBytes(var12, 0, new byte[]{(byte)var14, -96, -32, 15}, 0, 4) == 0) {
               var11 = true;
            }
         }

         if (var11) {
            return new Kl(var5);
         }
      }

      return var5;
   }

   private ByteOrder xK() {
      return this.q().toByteOrder();
   }

   public fA q(byte[] var1) throws ProcessorException {
      if (cL.q(var1, this.xK())) {
         if (var1.length < 4) {
            throw new ProcessorException(Strings.ff("Invalid ARM instruction %s must be 32 bit instruction", Formatter.byteArrayToHex(var1)));
         }
      } else {
         var1 = Arrays.copyOfRange(var1, 0, 2);
      }

      return this.Dw.RF(new BytesBlock(var1, this.q(), 2));
   }

   @Override
   public ICodeResolver getResolver() {
      return this;
   }

   public ICodePointer q(IMachineContext var1, fA var2) throws ProcessorException {
      return var2.q(var1);
   }

   public IResolvedOperandValue q(IMachineContext var1, fA var2, int var3) throws ProcessorException {
      CW[] var4 = var2.RF();
      if (var4 != null && var3 < var4.length) {
         CW var5 = var4[var3];
         Long var6 = var5.q(var1.getRegisters().getProgramCounter(), this.getMode(), var1);
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

   private static void q(byte[] var0, String var1) throws ProcessorException {
      throw new ProcessorException(Strings.ff("Instruction %s is WIP. See section [%s]", Formatter.byteArrayToHex(var0), var1));
   }

   @Override
   public int setMode(int var1) throws ProcessorException {
      if (this.getDefaultMode() == 64 && var1 != 64 && var1 != 0) {
         RF.error(S.L("Cannot change mode in arm64"));
         return this.getDefaultMode();
      } else if ((this.getDefaultMode() == 32 || this.getDefaultMode() == 16) && var1 == 64) {
         RF.error(S.L("Cannot change mode to arm64"));
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
            RF.debug("Unaligned address detected at %xh", var1);
            var1 &= -4L;
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
      this.xK.clearCache();
      this.Uv.clearCache();
      this.Dw.clearCache();
      return true;
   }

   public Endianness q() {
      return this.oW != null ? this.oW : this.getEndianness();
   }

   public void q(boolean var1) {
      this.oW = var1 ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
   }
}
