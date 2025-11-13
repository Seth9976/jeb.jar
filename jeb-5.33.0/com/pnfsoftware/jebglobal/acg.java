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
public class acg extends acc implements IDecompiledField {
   private static final StructuredLogger UT = aco.pC(acg.class);
   @SerId(1)
   private INativeFieldItem E;
   @SerId(2)
   private IEConverter sY;
   @SerId(3)
   private ICField ys;
   @SerId(4)
   private int ld;

   public acg(INativeDecompilerContext var1, INativeFieldItem var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException();
      } else {
         this.E = var2;
         this.pC = var1;
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

   public INativeFieldItem kS() {
      return this.E;
   }

   @Override
   public INativeFieldItem getFieldItem() {
      return this.E;
   }

   public ICField wS() {
      return this.getFieldAST();
   }

   @Override
   public ICField getFieldAST() {
      return this.ys;
   }

   public void pC(ICField var1) {
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
