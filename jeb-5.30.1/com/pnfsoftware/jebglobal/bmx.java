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
public class bmx extends bml implements IJavaDefinition {
   private static final ILogger gO = GlobalLog.getLogger(bmx.class);
   @SerId(1)
   private int nf;
   @SerId(2)
   private IJavaType gP;
   @SerId(3)
   private bnc za;
   @SerId(4)
   IdentifierCoordinates oW;

   bmx(int var1, IJavaType var2, int var3, IdentifierCoordinates var4, String var5, String var6) {
      if (var2 != null && var5 != null) {
         this.gP = var2;
         this.nf = var1;
         this.oW = var4;
         this.za = new bnc(var2, var5, var6, var3);
         this.za.Dw = this;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public int Dw() {
      return this.nf;
   }

   @Override
   public IJavaType getType() {
      return this.gP;
   }

   @Override
   public IJavaIdentifier getIdentifier() {
      return this.za;
   }

   @Override
   public IdentifierCoordinates getCoordinates() {
      return this.oW;
   }

   public void q(int var1) {
      this.nf = var1;
   }

   public void q(IJavaType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gP = var1;
      }
   }

   public void q(bnc var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.za = var1;
      }
   }

   @Override
   public List getSubElements() {
      return blo.q(this.za);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.za == var1) {
         if (!(var2 instanceof bnc)) {
            return false;
         } else {
            this.za = (bnc)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      blk.q(var1, this.gP);
      var1.append(" ");
      this.za.generate(var1, true);
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Declaration;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.nf;
      var1 = 31 * var1 + (this.za == null ? 0 : this.za.hashCode());
      return 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
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
         bmx var2 = (bmx)var1;
         if (this.nf != var2.nf) {
            return false;
         } else {
            if (this.za == null) {
               if (var2.za != null) {
                  return false;
               }
            } else if (!this.za.equals(var2.za)) {
               return false;
            }

            if (this.gP == null) {
               if (var2.gP != null) {
                  return false;
               }
            } else if (!this.gP.equals(var2.gP)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return this.gP + " " + this.za;
   }

   public bmx Uv() {
      return this;
   }
}
