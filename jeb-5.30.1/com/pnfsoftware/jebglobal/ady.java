package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IErrorDescription;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ady extends adu implements IDecompiledField {
   private static final StructuredLogger Uv = aeg.q(ady.class);
   @SerId(1)
   private INativeFieldItem oW;
   @SerId(2)
   private IEConverter gO;
   @SerId(3)
   private ICField nf;
   @SerId(4)
   private int gP;

   public ady(INativeDecompilerContext var1, INativeFieldItem var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException();
      } else {
         this.oW = var2;
         this.q = var1;
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

   public INativeFieldItem xK() {
      return this.oW;
   }

   @Override
   public INativeFieldItem getFieldItem() {
      return this.oW;
   }

   public IEConverter Dw() {
      return this.gO;
   }

   public ICField Uv() {
      return this.getFieldAST();
   }

   @Override
   public ICField getFieldAST() {
      return this.nf;
   }

   public void q(ICField var1) {
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
