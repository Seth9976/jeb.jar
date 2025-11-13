package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.List;

public class boj extends bop {
   private IJavaType wS;
   private IJavaIdentifier UT;
   private IJavaDefinition E;
   private List sY;

   public boj(IJavaType var1, IJavaIdentifier var2, IJavaDefinition var3, List var4) {
      super(bop.Av.ys, bop.Sv.A);
      this.wS = var1;
      this.UT = var2;
      this.E = var3;
      this.sY = var4;
   }

   public IJavaType A() {
      return this.wS;
   }

   public IJavaIdentifier kS() {
      return this.UT;
   }

   public IJavaDefinition wS() {
      return this.E;
   }

   public List UT() {
      return this.sY;
   }

   @Override
   public String toString() {
      return "try-catch: " + this.UT + " " + this.wS + (this.sY == null ? "" : this.sY.toString());
   }
}
