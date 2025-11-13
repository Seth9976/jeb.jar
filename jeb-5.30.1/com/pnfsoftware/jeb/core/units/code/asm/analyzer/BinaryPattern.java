package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class BinaryPattern implements IBinaryPattern {
   @SerId(1)
   byte[] binary;
   @SerId(2)
   byte[] mask;
   @SerId(3)
   int realStartOffset;
   @SerId(4)
   int processorMode;

   public BinaryPattern(byte[] var1) {
      this(var1, null, 0, 0);
   }

   public BinaryPattern(byte[] var1, byte[] var2) {
      this(var1, var2, 0, 0);
   }

   public BinaryPattern(byte[] var1, byte[] var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public BinaryPattern(byte[] var1, byte[] var2, int var3, int var4) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.binary = var1;
         if (var2 != null && var2.length != var1.length) {
            throw new IllegalArgumentException();
         } else {
            this.mask = var2;
            this.realStartOffset = var3;
            this.processorMode = var4;
         }
      }
   }

   @Override
   public byte[] getBinary() {
      return this.binary;
   }

   @Override
   public byte[] getMask() {
      return this.mask;
   }

   @Override
   public int getRealStartOffset() {
      return this.realStartOffset;
   }

   @Override
   public int getProcessorMode() {
      return this.processorMode;
   }

   @Override
   public boolean validate(INativeCodeAnalyzer var1, long var2, byte[] var4, int var5, int var6) {
      return true;
   }

   @Override
   public Object getExtra() {
      return null;
   }

   @Override
   public String toString() {
      return this.getMask() == null
         ? Strings.ff("bin=[%s]", Formatter.byteArrayToHex(this.getBinary()))
         : Strings.ff("bin=[%s],mask=[%s]", Formatter.byteArrayToHex(this.getBinary()), Formatter.byteArrayToHex(this.getMask()));
   }
}
