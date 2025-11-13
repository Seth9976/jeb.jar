package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class aet extends ado implements ICJumpFar {
   @SerId(1)
   ICExpression A;

   aet(ICExpression var1) {
      this.A = var1;
   }

   @Override
   public ICExpression getJumpsite() {
      return this.A;
   }

   @Override
   public void setJumpsite(ICExpression var1) {
      this.A = var1;
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.A);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.A == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.A = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      var1.appendAndRecord("jump", ItemClassIdentifiers.KEYWORD);
      var1.space();
      if (this.A instanceof ICConstantInteger) {
         ((ICConstantInteger)this.A).getFormatter().setBase(NumberFormatter.NumberBase.HEXADECIMAL);
      }

      this.A.generate(var1);
      this.A(var1);
   }

   @Override
   public CElementType getElementType() {
      return null;
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
         aet var2 = (aet)var1;
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

   public aet A() {
      aet var1 = new aet(this.A.duplicate());
      super.pC(var1);
      return var1;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      var1.setControlWord(CMethodState.ControlWord.GOTO_END_OF_METHOD);
      return null;
   }

   @Override
   public String toString() {
      return "jump " + this.getJumpsite();
   }
}
