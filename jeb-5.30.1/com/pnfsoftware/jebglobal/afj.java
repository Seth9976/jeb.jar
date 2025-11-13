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
public class afj extends afh implements ICAssignment {
   @SerId(1)
   private ICLeftExpression RF;
   @SerId(2)
   private ICExpression xK;
   @SerId(3)
   private ICOperator Dw;
   @SerId(4)
   private boolean Uv;
   @SerId(5)
   private boolean oW;

   afj(ICLeftExpression var1, ICExpression var2) {
      if (var1 != null && var2 != null) {
         this.RF = var1;
         this.xK = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   private afj() {
   }

   public afj RF() {
      return this.q(false);
   }

   public afj q(boolean var1) {
      afj var2 = new afj();
      super.q(var2);
      var2.RF = this.RF.duplicate();
      var2.xK = this.xK == null ? null : this.xK.duplicate();
      var2.Uv = this.Uv;
      var2.oW = this.oW;
      var2.Dw = this.Dw;
      if (var1 && this.RF instanceof afz) {
         var2.RF = ((afz)this.RF).getIdentifier();
      }

      return var2;
   }

   @Override
   public boolean isSimpleAssignment() {
      return this.xK != null && this.Dw == null;
   }

   @Override
   public boolean isCombinedOperatorAssignment() {
      return this.xK != null && this.Dw != null;
   }

   @Override
   public ICOperator getCombinedOperator() {
      return this.Dw;
   }

   @Override
   public boolean isUnaryOperatorAssignment() {
      return this.xK == null;
   }

   @Override
   public void getUnaryOperator(boolean[] var1) {
      if (!this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.length >= 2) {
         var1[0] = this.Uv;
         var1[1] = this.oW;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public ICLeftExpression getLeft() {
      return this.RF;
   }

   @Override
   public void setLeft(ICLeftExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
      }
   }

   @Override
   public ICExpression getRight() {
      return this.xK;
   }

   @Override
   public void setRight(ICExpression var1) {
      if (this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.xK = var1;
      }
   }

   @Override
   public void setCombinedOperatorAssignment(ICOperator var1, ICExpression var2) {
      if (this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isValidForCombinedAssignment()) {
         this.Dw = var1;
         if (var2 != null) {
            this.xK = var2;
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
      this.xK = null;
      this.Dw = null;
      this.Uv = var1;
      this.oW = var2;
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.RF, this.xK);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.RF == var1) {
         if (!(var2 instanceof ICLeftExpression)) {
            return false;
         } else {
            this.RF = (ICLeftExpression)var2;
            return true;
         }
      } else if (this.xK == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.xK = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      if (this.xK == null) {
         if (this.oW) {
            var1.append(this.Uv ? "++" : "--");
         }

         this.RF.generate(var1);
         if (!this.oW) {
            var1.append(this.Uv ? "++" : "--");
         }
      } else {
         this.RF.generate(var1);
         if (this.Dw != null) {
            var1.append(" " + this.Dw + "= ");
         } else {
            var1.append(" = ");
         }

         this.xK.generate(var1);
      }

      this.RF(var1);
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      Long var3 = null;
      if (this.isSimpleAssignment()) {
         var3 = ((afe)this.xK).evaluate(var1, var2);
         if (var3 == null) {
            throw new CSimulationException(Strings.ff("right value evaluation (%s)", this));
         }

         Long var4 = CMethodSimulatorUtils.getDereferencedAddress(this.RF, var1, var2);
         if (var4 != null) {
            var2.writeMemory(var4, var3);
         } else {
            var1.setValue(this.RF, var3, var2);
         }
      } else if (this.isCombinedOperatorAssignment()) {
         var3 = new agq(this.Dw, this.RF, this.xK).evaluate(var1, var2);
         if (var3 == null) {
            throw new CSimulationException(Strings.ff("combined operator assign evaluation (%s)", this));
         }

         var1.setValue(this.RF, var3, var2);
      } else if (this.isUnaryOperatorAssignment()) {
         var3 = ((afe)this.RF).evaluate(var1, var2);
         if (this.Uv) {
            if (this.oW) {
               var3 = var3 + 1L;
               var1.setValue(this.RF, var3, var2);
            } else {
               var1.setValue(this.RF, var3 + 1L, var2);
            }
         } else if (this.oW) {
            var3 = var3 - 1L;
            var1.setValue(this.RF, var3, var2);
         } else {
            var1.setValue(this.RF, var3 - 1L, var2);
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
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.Uv ? 1231 : 1237);
      return 31 * var1 + (this.oW ? 1231 : 1237);
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
         afj var2 = (afj)var1;
         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         return this.Uv != var2.Uv ? false : this.oW == var2.oW;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.xK == null) {
         if (this.oW) {
            var1.append(this.Uv ? "++" : "--");
         }

         var1.append(this.RF);
         if (!this.oW) {
            var1.append(this.Uv ? "++" : "--");
         }
      } else {
         var1.append(this.RF);
         if (this.Dw != null) {
            var1.append(" ").append(this.Dw).append("= ");
         } else {
            var1.append(" = ");
         }

         var1.append(this.xK);
      }

      return var1.toString();
   }
}
