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
public class acf extends acc implements IDecompiledClass {
   private static final StructuredLogger UT = aco.pC(acf.class);
   @SerId(1)
   private INativeClassItem E;
   @SerId(2)
   private IEConverter sY;
   @SerId(3)
   private ICClass ys;
   @SerId(4)
   private int ld;

   public acf(INativeDecompilerContext var1, INativeClassItem var2) {
      super(var1);
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.E = var2;
         this.sY = var1.getConverter();
         this.A = DecompilationStatus.IN_PROCESS;
      }
   }

   @Override
   public void pC() {
      if (this.ys != null) {
         this.ys.reset();
      }

      this.A = DecompilationStatus.IN_PROCESS;
   }

   public INativeClassItem kS() {
      return this.E;
   }

   @Override
   public INativeClassItem getClassItem() {
      return this.E;
   }

   public ICClass wS() {
      return this.getClassAST();
   }

   @Override
   public ICClass getClassAST() {
      return this.ys;
   }

   public void pC(ICClass var1) {
      this.ys = var1;
   }

   @Override
   public IErrorDescription getErrorDescription() {
      return null;
   }

   @Override
   public String toString() {
      return Strings.ff("DECOMP:%s,stage=%d,%s", this.E, this.ld, super.toString());
   }
}
