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
public class afz extends afh implements ICDecl {
   @SerId(1)
   ICIdentifier RF;

   afz(ICIdentifier var1) {
      this.RF = var1;
   }

   @Override
   public boolean isParameterDeclaration() {
      return this.RF.isParameter();
   }

   @Override
   public ICIdentifier getIdentifier() {
      return this.RF;
   }

   @Override
   public ICType getType() {
      return this.RF.getType();
   }

   @Override
   public String getName() {
      return this.RF.getName();
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
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

      this.RF.generate(var1, true);
      this.RF(var1);
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.RF);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (var1 == this.RF) {
         if (!(var2 instanceof ICIdentifier)) {
            return false;
         } else {
            this.RF = (ICIdentifier)var2;
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
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         afz var2 = (afz)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         return true;
      }
   }

   public afz RF() {
      afz var1 = new afz(this.RF);
      super.q(var1);
      return var1;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      return null;
   }

   @Override
   public String toString() {
      return this.RF.getType() + " " + this.RF.getName();
   }
}
