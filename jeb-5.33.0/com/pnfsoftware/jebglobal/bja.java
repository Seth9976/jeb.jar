package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.IdentifierCoordinates;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bja extends bio implements IJavaDefinition {
   private static final ILogger UT = GlobalLog.getLogger(bja.class);
   @SerId(1)
   private int E;
   @SerId(2)
   private IJavaType sY;
   @SerId(3)
   private bjf ys;
   @SerId(4)
   IdentifierCoordinates wS;

   bja(int var1, IJavaType var2, int var3, IdentifierCoordinates var4, String var5, String var6) {
      if (var2 != null && var5 != null) {
         this.sY = var2;
         this.E = var1;
         this.wS = var4;
         this.ys = new bjf(var2, var5, var6, var3);
         this.ys.A = this;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaType getType() {
      return this.sY;
   }

   @Override
   public IJavaIdentifier getIdentifier() {
      return this.ys;
   }

   @Override
   public IdentifierCoordinates getCoordinates() {
      return this.wS;
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.ys);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.ys == var1) {
         if (!(var2 instanceof bjf)) {
            return false;
         } else {
            this.ys = (bjf)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      bhn.pC(var1, this.sY);
      var1.append(" ");
      this.ys.generate(var1, true);
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Declaration;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.E;
      var1 = 31 * var1 + (this.ys == null ? 0 : this.ys.hashCode());
      return 31 * var1 + (this.sY == null ? 0 : this.sY.hashCode());
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
         bja var2 = (bja)var1;
         if (this.E != var2.E) {
            return false;
         } else {
            if (this.ys == null) {
               if (var2.ys != null) {
                  return false;
               }
            } else if (!this.ys.equals(var2.ys)) {
               return false;
            }

            if (this.sY == null) {
               if (var2.sY != null) {
                  return false;
               }
            } else if (!this.sY.equals(var2.sY)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return this.sY + " " + this.ys;
   }

   public bja A() {
      return this;
   }
}
