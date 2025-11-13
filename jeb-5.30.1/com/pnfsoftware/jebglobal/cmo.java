package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class cmo {
   public static cmf q(byte[] var0, clg var1, boolean var2) throws ProcessorException {
      return cmr.q(var0, var1, var2);
   }

   public static cmf RF(byte[] var0, clg var1, boolean var2) throws ProcessorException {
      return cmq.q(var0, var1, var2);
   }

   public static cmf q(byte[] var0) throws ProcessorException {
      return cmh.q(var0);
   }

   public static cmf xK(byte[] var0, clg var1, boolean var2) throws ProcessorException {
      return cmh.q(var0, var1, var2);
   }

   public static cmf RF(byte[] var0) throws ProcessorException {
      return cmh.RF(var0);
   }

   public static cmf xK(byte[] var0) throws ProcessorException {
      return cmh.xK(var0);
   }

   public static cmf Dw(byte[] var0, clg var1, boolean var2) throws ProcessorException {
      return cmr.RF(var0, var1, var2);
   }

   public static cmf Dw(byte[] var0) throws ProcessorException {
      return cmf.q(var0, "SIMD insn are not supported by the current plugin version");
   }

   public static cmf q(byte[] var0, clg var1, boolean var2, int var3) throws ProcessorException {
      return cmr.q(var0, var3);
   }
}
