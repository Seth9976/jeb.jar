package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IErrorDescription;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class adx extends adu implements IDecompiledClass {
   private static final StructuredLogger Uv = aeg.q(adx.class);
   @SerId(1)
   private INativeClassItem oW;
   @SerId(2)
   private IEConverter gO;
   @SerId(3)
   private ICClass nf;
   @SerId(4)
   private int gP;

   public adx(INativeDecompilerContext var1, INativeClassItem var2) {
      super(var1);
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.oW = var2;
         this.gO = var1.getConverter();
         this.RF = DecompilationStatus.IN_PROCESS;
      }
   }

   @Override
   public void q() {
      if (this.nf != null) {
         this.nf.reset();
      }

      this.RF = DecompilationStatus.IN_PROCESS;
   }

   public INativeClassItem xK() {
      return this.oW;
   }

   @Override
   public INativeClassItem getClassItem() {
      return this.oW;
   }

   public IEConverter Dw() {
      return this.gO;
   }

   public ICClass Uv() {
      return this.getClassAST();
   }

   @Override
   public ICClass getClassAST() {
      return this.nf;
   }

   public void q(ICClass var1) {
      this.nf = var1;
   }

   @Override
   public IErrorDescription getErrorDescription() {
      return null;
   }

   @Override
   public String toString() {
      return Strings.ff("DECOMP:%s,stage=%d,%s", this.oW, this.gP, super.toString());
   }
}
