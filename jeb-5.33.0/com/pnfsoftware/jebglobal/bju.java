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
public class bju extends bio implements IJavaMonitor {
   @SerId(1)
   private boolean wS;
   @SerId(2)
   private IJavaExpression UT;

   public bju(boolean var1, IJavaExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Lock expression cannot be null");
      } else {
         this.wS = var1;
         this.UT = var2;
      }
   }

   @Override
   public boolean isEnter() {
      return this.wS;
   }

   @Override
   public boolean isExit() {
      return !this.wS;
   }

   @Override
   public IJavaExpression getLock() {
      return this.UT;
   }

   @Override
   public void setEnter(boolean var1) {
      this.wS = var1;
   }

   @Override
   public void setLock(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.UT = var1;
      }
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.UT);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.UT == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.UT = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      var1.append(Strings.ff("__monitor_%s", this.wS ? "enter" : "exit"));
      var1.paren();
      this.UT.generate(var1);
      var1.parenClose();
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Monitor;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.wS ? 1231 : 1237);
      return 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
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
         bju var2 = (bju)var1;
         if (this.wS != var2.wS) {
            return false;
         } else {
            if (this.UT == null) {
               if (var2.UT != null) {
                  return false;
               }
            } else if (!this.UT.equals(var2.UT)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return "__monitor_" + (this.wS ? "enter" : "exit") + "(" + this.UT + ")";
   }

   public bju A() {
      bju var1 = new bju(this.wS, this.UT.duplicate());
      this.pC(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
