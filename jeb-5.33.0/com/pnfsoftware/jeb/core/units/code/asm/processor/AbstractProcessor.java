package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.ICodeResolver;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorVariant;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.concurrent.SafeLockImpl;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Ser
public abstract class AbstractProcessor implements IProcessor {
   @SerId(1)
   private SafeLockImpl lock = new SafeLockImpl();
   @SerId(2)
   private int parseCacheLength;
   @SerId(3)
   private Endianness endianness;
   @SerId(4)
   private int instructionAlignmentMask;
   @SerId(5)
   protected Collection supportedModes = new HashSet();
   @SerId(6)
   protected int defaultMode;
   @SerId(7)
   protected int mode;
   @SerId(8)
   protected Collection supportedVariants = new ArrayList();
   @SerId(9)
   protected ProcessorVariant variant;
   @SerId(10)
   private int parseBufferBefore = 0;

   public AbstractProcessor(int var1, int var2, Endianness var3, int var4) {
      this.parseCacheLength = var1;
      this.setDefaultMode(var2);
      this.setEndianness(var3);
      this.setInstructionAlignment(var4);
   }

   public AbstractProcessor(int var1, int var2, IUnitCreator var3, int var4) {
      this(var1, var2, var3, var4, 0);
   }

   public AbstractProcessor(int var1, int var2, IUnitCreator var3, int var4, int var5) {
      this.parseCacheLength = var1;
      this.parseBufferBefore = var5;
      this.setDefaultMode(var2);
      IUnitCreator var6 = var3;

      while (var6 != null) {
         if (var3 instanceof ICodeObjectUnit) {
            this.setEndianness(((ICodeObjectUnit)var6).getLoaderInformation().getEndianness());
            break;
         }

         if (var3 instanceof INativeCodeUnit) {
            this.setEndianness(((INativeCodeUnit)var6).getEndianness());
            break;
         }

         if (var6 instanceof IUnit) {
            var6 = ((IUnit)var6).getParent();
         } else {
            var6 = null;
         }
      }

      if (this.getEndianness() == null) {
         this.setEndianness(Endianness.LITTLE_ENDIAN);
      }

      this.setInstructionAlignment(var4);
   }

   @Override
   public String getRegisterName(long var1) {
      try {
         IRegisterBank var3 = this.getRegisterBank();
         if (var3 != null) {
            RegisterDescriptionEntry var4 = var3.getDescriptionEntryById(var1);
            if (var4 != null) {
               return var4.getName();
            }
         }
      } catch (Exception var5) {
      }

      return null;
   }

   @Override
   public IInstruction parseAt(IVirtualMemory var1, long var2) throws ProcessorException {
      IInstruction var15;
      try (ACLock var4 = this.lock.ro()) {
         if ((var2 & this.instructionAlignmentMask) != 0L) {
            throw new ProcessorException(Strings.ff("Instruction is not aligned at address %Xh", var2));
         }

         int var5 = this.parseBufferBefore;
         byte[] var6 = new byte[this.parseCacheLength + var5];
         int var7 = VirtualMemoryUtil.readBytesSafe(var1, var2 - var5, var6.length, var6, 0, 5);
         if (var7 <= 0 && this.parseBufferBefore != 0) {
            var5 = 0;
            var6 = new byte[this.parseCacheLength];
            var7 = VirtualMemoryUtil.readBytesSafe(var1, var2, var6.length, var6, 0, 5);
         }

         if (var7 <= 0) {
            throw new ProcessorException(Strings.ff("Cannot read instruction bytes at address %Xh", var2));
         }

         try {
            var15 = this.parseAtInternal(var6, var5, var7);
         } catch (Exception var13) {
            Exception var8 = var13;

            try {
               int var9 = Math.min(8, var7);
               String var10 = Formatter.byteArrayToHexString(var6, var5, var5 + var9);
               throw new ProcessorException("Failed parsing for bytes: " + var10, var8);
            } catch (Exception var12) {
               throw new ProcessorException("Failed parsing, more errors: " + var12.getMessage(), var13);
            }
         }
      }

      return var15;
   }

   @Override
   public final IInstruction parseAt(byte[] var1, int var2, int var3) throws ProcessorException {
      IInstruction var5;
      try (ACLock var4 = this.lock.ro()) {
         var5 = this.parseAtInternal(var1, var2, var3);
      }

      return var5;
   }

   protected abstract IInstruction parseAtInternal(byte[] var1, int var2, int var3) throws ProcessorException;

   @Override
   public IInstruction parseWithContext(IMachineContext var1) throws ProcessorException {
      IInstruction var3;
      try (ACLock var2 = this.lock.ro()) {
         var3 = this.parseWithContextInternal(var1);
      }

      return var3;
   }

   protected IInstruction parseWithContextInternal(IMachineContext var1) throws ProcessorException {
      throw new UnsupportedOperationException("This method is not implemented yet");
   }

   @Override
   public ProcessorType getType() {
      return ProcessorType.UNKNOWN;
   }

   @Override
   public IRegisterBank getRegisterBank() {
      return RegisterUtil.getBank(this.getType());
   }

   @Override
   public boolean isRISC() {
      return false;
   }

   @Override
   public Collection getSupportedVariants() {
      return this.supportedVariants;
   }

   @Override
   public ProcessorVariant getVariant() {
      return this.variant;
   }

   @Override
   public void setVariant(ProcessorVariant var1) throws ProcessorException {
      try (ACLock var2 = this.lock.rw()) {
         if (var1 != null && !this.supportedVariants.contains(var1)) {
            throw new ProcessorException("Unsupported variant: " + var1);
         }

         this.variant = var1;
      }
   }

   @Override
   public Collection getSupportedModes() {
      return this.supportedModes;
   }

   @Override
   public final int getDefaultMode() {
      return this.defaultMode;
   }

   protected final void setDefaultMode(int var1) {
      if (var1 == -1) {
         throw new IllegalArgumentException("Invalid processor mode");
      } else if (var1 == 0) {
         throw new IllegalArgumentException("The default processor mode cannot be unknown");
      } else {
         try (ACLock var2 = this.lock.rw()) {
            if (!this.supportedModes.contains(var1)) {
               this.supportedModes.add(var1);
            }

            this.defaultMode = var1;
            if (this.mode == 0) {
               try {
                  this.setMode(var1);
               } catch (ProcessorException var6) {
               }
            }
         }
      }
   }

   @Override
   public int getPCRegisterBitsize() {
      return this.defaultMode;
   }

   @Override
   public int getGPRegisterBitsize() {
      return this.defaultMode;
   }

   @Override
   public int setMode(int var1) throws ProcessorException {
      if (var1 == -1) {
         throw new IllegalArgumentException("Invalid processor mode");
      } else {
         int var4;
         try (ACLock var2 = this.lock.rw()) {
            int var3 = this.mode;
            if (var1 == 0) {
               this.mode = this.defaultMode;
            } else {
               if (!this.supportedModes.contains(var1)) {
                  throw new ProcessorException("Unsupported mode: " + var1);
               }

               this.mode = var1;
            }

            var4 = var3;
         }

         return var4;
      }
   }

   @Override
   public final int getMode() {
      return this.mode;
   }

   @Override
   public final Endianness getEndianness() {
      return this.endianness;
   }

   @Override
   public final void setEndianness(Endianness var1) {
      if (var1 == null) {
         throw new NullPointerException("Endianness cannot be null");
      } else {
         try (ACLock var2 = this.lock.rw()) {
            this.endianness = var1;
         }
      }
   }

   @Override
   public final int getInstructionAlignment() {
      return this.instructionAlignmentMask + 1;
   }

   public int getInstructionAlignmentMask() {
      return this.instructionAlignmentMask;
   }

   @Override
   public final void setInstructionAlignment(int var1) {
      try (ACLock var2 = this.lock.rw()) {
         if (var1 <= 0 || (var1 & var1 - 1) != 0) {
            throw new IllegalArgumentException("Invalid instruction alignment: " + var1);
         }

         this.instructionAlignmentMask = var1 - 1;
      }
   }

   @Override
   public ICodeResolver getResolver() {
      return null;
   }

   @Override
   public CodePointer createEntryPoint(long var1) {
      return this.createEntryPoint(var1, 0);
   }

   @Override
   public CodePointer createEntryPoint(long var1, int var3) {
      return new CodePointer(var1, var3);
   }

   public boolean clearInstructionCache() {
      return false;
   }
}
