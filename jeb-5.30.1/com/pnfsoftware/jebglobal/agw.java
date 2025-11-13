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
public class agw extends afh implements ICReturn {
   @SerId(1)
   ICExpression RF;

   agw(ICExpression var1) {
      this.RF = var1;
   }

   public agw RF() {
      agw var1 = new agw(this.RF != null ? this.RF.duplicate() : null);
      super.q(var1);
      return var1;
   }

   @Override
   public ICExpression getExpression() {
      return this.RF;
   }

   @Override
   public void setExpression(ICExpression var1) {
      this.RF = var1;
   }

   @Override
   public boolean returnsVoid() {
      return this.RF == null;
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.RF);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.RF == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.RF = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      var1.appendKeyword(CKeyword.RETURN);
      if (this.RF != null) {
         var1.append(" ");
         this.RF.generate(var1);
      }

      this.RF(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Return;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.RF != null) {
         Long var3 = ((afe)this.RF).evaluate(var1, var2);
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
         agw var2 = (agw)var1;
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

   @Override
   public String toString() {
      return "return" + (this.RF == null ? "" : " " + this.RF.toString());
   }
}
