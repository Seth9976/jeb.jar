package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class aeg extends ado implements ICDecl {
   @SerId(1)
   ICIdentifier A;

   aeg(ICIdentifier var1) {
      this.A = var1;
   }

   @Override
   public boolean isParameterDeclaration() {
      return this.A.isParameter();
   }

   @Override
   public ICIdentifier getIdentifier() {
      return this.A;
   }

   @Override
   public ICType getType() {
      return this.A.getType();
   }

   @Override
   public String getName() {
      return this.A.getName();
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      if (!var1.omitTypeForNextDefinitionPrinting()) {
         if (this.getType().isFunctionPointer() && !this.getType().isAlias()) {
            var1.append("FUNCPTR");
         } else {
            this.getType().generate(var1);
         }

         var1.space();
      } else {
         var1.setOmitTypeForNextDefinitionPrinting(false);
      }

      this.A.generate(var1, true);
      this.A(var1);
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.A);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (var1 == this.A) {
         if (!(var2 instanceof ICIdentifier)) {
            return false;
         } else {
            this.A = (ICIdentifier)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Declaration;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         aeg var2 = (aeg)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return true;
      }
   }

   public aeg A() {
      aeg var1 = new aeg(this.A);
      super.pC(var1);
      return var1;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      return null;
   }

   @Override
   public String toString() {
      return this.A.getType() + " " + this.A.getName();
   }
}
