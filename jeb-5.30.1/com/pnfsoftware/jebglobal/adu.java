package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledItem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class adu implements IDecompiledItem {
   @SerId(1)
   INativeDecompilerContext q;
   @SerId(2)
   DecompilationStatus RF;
   @SerId(3)
   int xK;
   @SerId(4)
   private volatile int Uv;
   @SerId(5)
   private long oW;
   @SerId(6)
   private long gO;
   @SerId(7)
   public aef Dw;

   public adu(INativeDecompilerContext var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
         this.RF = DecompilationStatus.IN_PROCESS;
         this.oW = System.currentTimeMillis();
         this.gO = this.oW;
      }
   }

   public abstract void q();

   @Override
   public INativeDecompilerContext getDecompiler() {
      return this.q;
   }

   @Override
   public DecompilationStatus getStatus() {
      return this.RF;
   }

   public void q(DecompilationStatus var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Need a decompilation status");
      } else {
         if (var1 == DecompilationStatus.COMPLETED && this.RF != DecompilationStatus.COMPLETED) {
            this.xK++;
         }

         this.RF = var1;
      }
   }

   @Override
   public int getCompletionCount() {
      return this.xK;
   }

   @Override
   public long getCreationTimestamp() {
      return this.oW;
   }

   public void RF() {
      this.gO = System.currentTimeMillis();
   }

   @Override
   public long getLastModificationTimestampMs() {
      return this.gO;
   }

   @Override
   public int getFlags() {
      return this.Uv;
   }

   @Override
   public void setFlags(int var1) {
      this.Uv = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("status=%s,cc=%d,flags=0x%X", this.RF, this.xK, this.Uv);
   }
}
