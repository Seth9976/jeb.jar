package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.List;

public class bsk extends bsq {
   private IJavaType oW;
   private IJavaIdentifier gO;
   private IJavaDefinition nf;
   private List gP;

   public bsk(IJavaType var1, IJavaIdentifier var2, IJavaDefinition var3, List var4) {
      super(bsq.eo.nf, bsq.CU.RF);
      this.oW = var1;
      this.gO = var2;
      this.nf = var3;
      this.gP = var4;
   }

   public IJavaType Dw() {
      return this.oW;
   }

   public IJavaIdentifier Uv() {
      return this.gO;
   }

   public IJavaDefinition oW() {
      return this.nf;
   }

   public List gO() {
      return this.gP;
   }

   @Override
   public String toString() {
      return "try-catch: " + this.gO + " " + this.oW + (this.gP == null ? "" : this.gP.toString());
   }
}
