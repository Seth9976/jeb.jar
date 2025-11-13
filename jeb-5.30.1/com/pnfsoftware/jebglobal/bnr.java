package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMonitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bnr extends bml implements IJavaMonitor {
   @SerId(1)
   private boolean oW;
   @SerId(2)
   private IJavaExpression gO;

   public bnr(boolean var1, IJavaExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Lock expression cannot be null");
      } else {
         this.oW = var1;
         this.gO = var2;
      }
   }

   @Override
   public boolean isEnter() {
      return this.oW;
   }

   @Override
   public boolean isExit() {
      return !this.oW;
   }

   @Override
   public IJavaExpression getLock() {
      return this.gO;
   }

   @Override
   public void setEnter(boolean var1) {
      this.oW = var1;
   }

   @Override
   public void setLock(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gO = var1;
      }
   }

   @Override
   public List getSubElements() {
      return blo.q(this.gO);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.gO == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.gO = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      var1.append(Strings.ff("__monitor_%s", this.oW ? "enter" : "exit"));
      var1.paren();
      this.gO.generate(var1);
      var1.parenClose();
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Monitor;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.oW ? 1231 : 1237);
      return 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
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
         bnr var2 = (bnr)var1;
         if (this.oW != var2.oW) {
            return false;
         } else {
            if (this.gO == null) {
               if (var2.gO != null) {
                  return false;
               }
            } else if (!this.gO.equals(var2.gO)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return "__monitor_" + (this.oW ? "enter" : "exit") + "(" + this.gO + ")";
   }

   public bnr Dw() {
      bnr var1 = new bnr(this.oW, this.gO.duplicate());
      this.q(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
