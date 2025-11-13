package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledItem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class acc implements IDecompiledItem {
   @SerId(1)
   INativeDecompilerContext pC;
   @SerId(2)
   DecompilationStatus A;
   @SerId(3)
   int kS;
   @SerId(4)
   private volatile int UT;
   @SerId(5)
   private long E;
   @SerId(6)
   private long sY;
   @SerId(7)
   public acn wS;

   public acc(INativeDecompilerContext var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
         this.A = DecompilationStatus.IN_PROCESS;
         this.E = System.currentTimeMillis();
         this.sY = this.E;
      }
   }

   public abstract void pC();

   @Override
   public INativeDecompilerContext getDecompiler() {
      return this.pC;
   }

   @Override
   public DecompilationStatus getStatus() {
      return this.A;
   }

   public void pC(DecompilationStatus var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Need a decompilation status");
      } else {
         if (var1 == DecompilationStatus.COMPLETED && this.A != DecompilationStatus.COMPLETED) {
            this.kS++;
         }

         this.A = var1;
      }
   }

   @Override
   public int getCompletionCount() {
      return this.kS;
   }

   @Override
   public long getCreationTimestamp() {
      return this.E;
   }

   public void A() {
      this.sY = System.currentTimeMillis();
   }

   @Override
   public long getLastModificationTimestampMs() {
      return this.sY;
   }

   @Override
   public int getFlags() {
      return this.UT;
   }

   @Override
   public void setFlags(int var1) {
      this.UT = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("status=%s,cc=%d,flags=0x%X", this.A, this.kS, this.UT);
   }
}
