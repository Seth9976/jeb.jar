package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodSimulatorUtils;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CSimulationException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class adq extends ado implements ICAssignment {
   @SerId(1)
   private ICLeftExpression A;
   @SerId(2)
   private ICExpression kS;
   @SerId(3)
   private ICOperator wS;
   @SerId(4)
   private boolean UT;
   @SerId(5)
   private boolean E;

   adq(ICLeftExpression var1, ICExpression var2) {
      if (var1 != null && var2 != null) {
         this.A = var1;
         this.kS = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   private adq() {
   }

   public adq A() {
      return this.pC(false);
   }

   public adq pC(boolean var1) {
      adq var2 = new adq();
      super.pC(var2);
      var2.A = this.A.duplicate();
      var2.kS = this.kS == null ? null : this.kS.duplicate();
      var2.UT = this.UT;
      var2.E = this.E;
      var2.wS = this.wS;
      if (var1 && this.A instanceof aeg) {
         var2.A = ((aeg)this.A).getIdentifier();
      }

      return var2;
   }

   @Override
   public boolean isSimpleAssignment() {
      return this.kS != null && this.wS == null;
   }

   @Override
   public boolean isCombinedOperatorAssignment() {
      return this.kS != null && this.wS != null;
   }

   @Override
   public ICOperator getCombinedOperator() {
      return this.wS;
   }

   @Override
   public boolean isUnaryOperatorAssignment() {
      return this.kS == null;
   }

   @Override
   public void getUnaryOperator(boolean[] var1) {
      if (!this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.length >= 2) {
         var1[0] = this.UT;
         var1[1] = this.E;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public ICLeftExpression getLeft() {
      return this.A;
   }

   @Override
   public void setLeft(ICLeftExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public ICExpression getRight() {
      return this.kS;
   }

   @Override
   public void setRight(ICExpression var1) {
      if (this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
      }
   }

   @Override
   public void setCombinedOperatorAssignment(ICOperator var1, ICExpression var2) {
      if (this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isValidForCombinedAssignment()) {
         this.wS = var1;
         if (var2 != null) {
            this.kS = var2;
         }
      } else {
         throw new IllegalArgumentException("Invalid operator for combined assignment");
      }
   }

   @Override
   public void setCombinedOperator(ICOperator var1) {
      this.setCombinedOperatorAssignment(var1, null);
   }

   @Override
   public void setUnaryOperator(boolean var1, boolean var2) {
      this.kS = null;
      this.wS = null;
      this.UT = var1;
      this.E = var2;
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.A, this.kS);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.A == var1) {
         if (!(var2 instanceof ICLeftExpression)) {
            return false;
         } else {
            this.A = (ICLeftExpression)var2;
            return true;
         }
      } else if (this.kS == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.kS = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      if (this.kS == null) {
         if (this.E) {
            var1.append(this.UT ? "++" : "--");
         }

         this.A.generate(var1);
         if (!this.E) {
            var1.append(this.UT ? "++" : "--");
         }
      } else {
         this.A.generate(var1);
         if (this.wS != null) {
            var1.append(" " + this.wS + "= ");
         } else {
            var1.append(" = ");
         }

         this.kS.generate(var1);
      }

      this.A(var1);
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      Long var3 = null;
      if (this.isSimpleAssignment()) {
         var3 = ((adl)this.kS).evaluate(var1, var2);
         if (var3 == null) {
            throw new CSimulationException(Strings.ff("right value evaluation (%s)", this));
         }

         Long var4 = CMethodSimulatorUtils.getDereferencedAddress(this.A, var1, var2);
         if (var4 != null) {
            var2.writeMemory(var4, var3);
         } else {
            var1.setValue(this.A, var3, var2);
         }
      } else if (this.isCombinedOperatorAssignment()) {
         var3 = new aex(this.wS, this.A, this.kS).evaluate(var1, var2);
         if (var3 == null) {
            throw new CSimulationException(Strings.ff("combined operator assign evaluation (%s)", this));
         }

         var1.setValue(this.A, var3, var2);
      } else if (this.isUnaryOperatorAssignment()) {
         var3 = ((adl)this.A).evaluate(var1, var2);
         if (this.UT) {
            if (this.E) {
               var3 = var3 + 1L;
               var1.setValue(this.A, var3, var2);
            } else {
               var1.setValue(this.A, var3 + 1L, var2);
            }
         } else if (this.E) {
            var3 = var3 - 1L;
            var1.setValue(this.A, var3, var2);
         } else {
            var1.setValue(this.A, var3 - 1L, var2);
         }
      } else {
         super.evaluate(var1, var2);
      }

      var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      return var3;
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Assignment;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.UT ? 1231 : 1237);
      return 31 * var1 + (this.E ? 1231 : 1237);
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
         adq var2 = (adq)var1;
         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         return this.UT != var2.UT ? false : this.E == var2.E;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.kS == null) {
         if (this.E) {
            var1.append(this.UT ? "++" : "--");
         }

         var1.append(this.A);
         if (!this.E) {
            var1.append(this.UT ? "++" : "--");
         }
      } else {
         var1.append(this.A);
         if (this.wS != null) {
            var1.append(" ").append(this.wS).append("= ");
         } else {
            var1.append(" = ");
         }

         var1.append(this.kS);
      }

      return var1.toString();
   }
}
