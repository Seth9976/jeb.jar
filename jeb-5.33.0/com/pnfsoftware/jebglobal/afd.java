package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CSimulationException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class afd extends ado implements ICReturn {
   @SerId(1)
   ICExpression A;

   afd(ICExpression var1) {
      this.A = var1;
   }

   public afd A() {
      afd var1 = new afd(this.A != null ? this.A.duplicate() : null);
      super.pC(var1);
      return var1;
   }

   @Override
   public ICExpression getExpression() {
      return this.A;
   }

   @Override
   public void setExpression(ICExpression var1) {
      this.A = var1;
   }

   @Override
   public boolean returnsVoid() {
      return this.A == null;
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.A);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.A == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
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
      var1.appendKeyword(CKeyword.RETURN);
      if (this.A != null) {
         var1.append(" ");
         this.A.generate(var1);
      }

      this.A(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Return;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.A != null) {
         Long var3 = ((adl)this.A).evaluate(var1, var2);
         if (var3 == null) {
            throw new CSimulationException(Strings.ff("return value evaluation (%s)", this));
         }

         var1.setReturnValue(var3);
      }

      var1.setControlWord(CMethodState.ControlWord.GOTO_END_OF_METHOD);
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
         afd var2 = (afd)var1;
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

   @Override
   public String toString() {
      return "return" + (this.A == null ? "" : " " + this.A.toString());
   }
}
